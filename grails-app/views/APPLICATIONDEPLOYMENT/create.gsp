<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${applicationDeploymentInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationDeploymentInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationDeployment.error.create"/></h4>
        <g:renderErrors bean="${applicationDeploymentInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <g:render template="form" model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create" value="${message(code: 'default.button.create.label')}"/>
        <g:link class="btn" controller="application" action="show"
                id="${applicationDeploymentInstance?.applicationRelease?.application?.id}">
            <g:message code="default.button.cancel.label"/>
        </g:link>
    </carm:formButtons>
</g:form>

<g:set var="existingEnvironments" value="${existingDeployments*.deploymentEnvironment?.unique()}"/>
<g:set var="alreadyDeployedTo" value="${existingEnvironments.collect { it.name }.join(", ")}"/>
<g:set var="alreadyDeployedToIds" value="${existingEnvironments.collect { it.id }.join(", ")}"/>

<r:script>
    jQuery(function () {
        var alreadyDeployedTo = [${alreadyDeployedToIds}],
            deploymentEnvironment = jQuery("#deploymentEnvironment\\.id");

        function deploymentEnvironmentChanged() {
            var selectedEnvironmentId = deploymentEnvironment.val();

            if (selectedEnvironmentId && jQuery.inArray(parseInt(selectedEnvironmentId), alreadyDeployedTo) === -1) {
                jQuery("#deploymentEnvironmentMessage:visible").hide('blind');
                jQuery("#deploymentEnvironmentMessage_message").html("");
            }
            else {
                jQuery("#deploymentEnvironmentMessage_message").html("${message(code: 'applicationReleaseAlreadyDeployedTo.message', args: [alreadyDeployedTo])}");
                jQuery("#deploymentEnvironmentMessage:hidden").show('blind');
            }
        }

        deploymentEnvironment.change(deploymentEnvironmentChanged);
        deploymentEnvironmentChanged();
    });
</r:script>

</body>
</html>
