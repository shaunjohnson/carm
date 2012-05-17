<%@ page import="carm.sourcecontrol.SourceControlServerType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlServerInstance}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${sourceControlServerInstance}">
    <div class="alert alert-error">
        <h4><g:message code="sourceControlServer.error.update"/></h4>
        <g:renderErrors bean="${sourceControlServerInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${sourceControlServerInstance?.id}"/>
    <g:hiddenField name="version" value="${sourceControlServerInstance?.version}"/>

    <carm:formSection legend="${message(code: 'sourceControlServerDetails.section')}">
        <div class="control-group ${hasErrors(bean: sourceControlServerInstance, field: 'name', 'error')}">
            <carm:label class="control-label" for="name" required="true">
                <g:message code="sourceControlServer.name.label" default="Name"/>
            </carm:label>
            <div class="controls">
                <g:textField name="name" maxlength="50" size="50"
                             value="${sourceControlServerInstance?.name}"
                             required="required"
                             title="${message(code: 'sourceControlServer.name.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: sourceControlServerInstance, field: 'description', 'error')}">
            <carm:label class="control-label" for="description">
                <g:message code="sourceControlServer.description.label" default="Description"/>
            </carm:label>
            <div class="controls">
                <g:textArea name="description"
                            cols="${grailsApplication.config.ui.textarea.cols}"
                            rows="${grailsApplication.config.ui.textarea.rows}"
                            value="${sourceControlServerInstance?.description}"
                            title="${message(code: 'sourceControlServer.description.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'connectionDetails.section')}">
        <div class="control-group ${hasErrors(bean: sourceControlServerInstance, field: 'type', 'error')}">
            <carm:label class="control-label" for="type" required="true">
                <g:message code="sourceControlServer.type.label" default="Type"/>
            </carm:label>
            <div class="controls">
                <g:select name="type" from="${SourceControlServerType?.values()}"
                          keys="${SourceControlServerType?.values()*.name()}"
                          value="${sourceControlServerInstance?.type?.name()}"
                          required="required"
                          title="${message(code: 'sourceControlServer.type.help')}"/>
            </div>
        </div>

        <div class="control-group value ${hasErrors(bean: sourceControlServerInstance, field: 'url', 'error')}">
            <carm:label class="control-label" for="url" required="true">
                <g:message code="sourceControlServer.url.label" default="URL"/>
            </carm:label>
            <div class="controls">
                <g:textField name="url" maxlength="200" size="75" required="required"
                             value="${sourceControlServerInstance?.url}"
                             title="${message(code: 'sourceControlServer.url.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${sourceControlServerInstance.id}">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
