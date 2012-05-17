<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlRoleInstance}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${sourceControlRoleInstance}">
    <div class="alert alert-error">
        <h4><g:message code="sourceControlRole.error.update"/></h4>
        <g:renderErrors bean="${sourceControlRoleInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${sourceControlRoleInstance?.id}"/>
    <g:hiddenField name="version" value="${sourceControlRoleInstance?.version}"/>

    <div class="control-group ${hasErrors(bean: sourceControlRoleInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="sourceControlRole.name.label" default="Name"/>
        </carm:label>
        <div class="controls">
            <g:textField name="name" maxlength="50" size="50"
                         value="${sourceControlRoleInstance?.name}"
                         required="required"
                         title="${message(code: 'sourceControlRole.name.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: sourceControlRoleInstance, field: 'description', 'error')}">
        <carm:label class="control-label" for="description">
            <g:message code="sourceControlRole.description.label" default="Description"/>
        </carm:label>
        <div class="controls">
            <g:textArea name="description"
                        cols="${grailsApplication.config.ui.textarea.cols}"
                        rows="${grailsApplication.config.ui.textarea.rows}"
                        value="${sourceControlRoleInstance?.description}"
                        title="${message(code: 'sourceControlRole.description.help')}"/>
        </div>
    </div>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${sourceControlRoleInstance.id}">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
