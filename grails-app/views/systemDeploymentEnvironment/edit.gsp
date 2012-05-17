<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemDeploymentEnvironmentInstance}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${systemDeploymentEnvironmentInstance}">
    <div class="alert alert-error">
        <h4><g:message code="systemDeploymentEnvironment.error.update"/></h4>
        <g:renderErrors bean="${systemDeploymentEnvironmentInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${systemDeploymentEnvironmentInstance?.id}"/>
    <g:hiddenField name="version" value="${systemDeploymentEnvironmentInstance?.version}"/>

    <div class="control-group ${hasErrors(bean: systemDeploymentEnvironmentInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="systemDeploymentEnvironment.name.label" default="Name"/>
        </carm:label>
        <div class="controls">
            <g:textField name="name" maxlength="50" size="50"
                         value="${systemDeploymentEnvironmentInstance?.name}"
                         required="required"
                         title="${message(code: 'systemDeploymentEnvironment.name.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: systemDeploymentEnvironmentInstance, field: 'description', 'error')}">
        <carm:label class="control-label" for="description">
            <g:message code="systemDeploymentEnvironment.description.label" default="Description"/>
        </carm:label>
        <div class="controls">
            <g:textArea name="description"
                        cols="${grailsApplication.config.ui.textarea.cols}"
                        rows="${grailsApplication.config.ui.textarea.rows}"
                        value="${systemDeploymentEnvironmentInstance?.description}"
                        title="${message(code: 'systemDeploymentEnvironment.description.help')}"/>
        </div>
    </div>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${systemDeploymentEnvironmentInstance.id}">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
