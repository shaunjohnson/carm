<%@ page import="carm.sourcecontrol.SourceControlServerType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'sourceControlRepository.label', default: 'Source Control Repository')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlRepositoryInstance}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${sourceControlRepositoryInstance}">
    <div class="alert alert-error">
        <h4><g:message code="sourceControlRepository.error.update"/></h4>
        <g:renderErrors bean="${sourceControlRepositoryInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${sourceControlRepositoryInstance?.id}"/>
    <g:hiddenField name="version" value="${sourceControlRepositoryInstance?.version}"/>
    <g:hiddenField name="server.id" value="${sourceControlRepositoryInstance?.server?.id}"/>

    <carm:formSection legend="${message(code: 'sourceControlRepositoryDetails.section')}">
        <div class="control-group ${hasErrors(bean: sourceControlRepositoryInstance, field: 'name', 'error')}">
            <carm:label class="control-label" for="name" required="true">
                <g:message code="sourceControlRepository.name.label" default="Name"/>
            </carm:label>
            <div class="controls">
                <g:textField name="name" maxlength="50" size="50"
                             value="${sourceControlRepositoryInstance?.name}"
                             required="required"
                             title="${message(code: 'sourceControlRepository.name.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: sourceControlRepositoryInstance, field: 'description', 'error')}">
            <carm:label class="control-label" for="description">
                <g:message code="sourceControlRepository.description.label" default="Description"/>
            </carm:label>
            <div class="controls">
                <g:textArea name="description"
                            cols="${grailsApplication.config.ui.textarea.cols}"
                            rows="${grailsApplication.config.ui.textarea.rows}"
                            value="${sourceControlRepositoryInstance?.description}"
                            title="${message(code: 'sourceControlRepository.description.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'connectionDetails.section')}">
        <div class="control-group ${hasErrors(bean: sourceControlRepositoryInstance, field: 'path', 'error')}">
            <carm:label class="control-label" for="path" required="true">
                <g:message code="sourceControlRepository.path.label" default="Path"/>
            </carm:label>
            <div class="controls">
                <g:textField name="path" maxlength="200" size="50"
                             value="${sourceControlRepositoryInstance?.path}"
                             required="required"
                             title="${message(code: 'sourceControlRepository.path.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: sourceControlRepositoryInstance, field: 'description', 'error')}">
            <carm:label class="control-label">
                <g:message code="sourceControlRepository.fullPath.label" default="Full Path"/>
            </carm:label>
            <div id="fullPath" class="controls">
                <g:if test="${sourceControlRepositoryInstance.server.type == SourceControlServerType.Subversion}">
                    <a href="${sourceControlRepositoryInstance.server.url}"
                       target="_blank">${sourceControlRepositoryInstance.server.url}</a>
                </g:if>
                <g:else>
                    ${sourceControlRepositoryInstance.server.url}
                </g:else>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${sourceControlRepositoryInstance.id}">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>

<script type="text/javascript">
    jQuery(function () {
        function updateFullPath() {
            var fullUrl = "${sourceControlRepositoryInstance.server.url}" + jQuery("#path").val();

        <g:if test="${sourceControlRepositoryInstance.server.type == SourceControlServerType.Subversion}">
            jQuery("#fullPath").html('<a href="' + fullUrl + '" target="_blank">' + fullUrl + '</a>');
        </g:if>
        <g:else>
            jQuery("#fullPath").text(fullUrl);
        </g:else>
        }

        updateFullPath();

        jQuery("#path").keydown(updateFullPath).keyup(updateFullPath);
    });
</script>

</body>
</html>
