<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'userGroup.label', default: 'User Group')}"/>
    <title><g:message code="default.addUser.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${userGroupInstance}"
             pageName="${message(code: 'default.addUser.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${userGroupInstance}">
    <div class="alert alert-error">
        <h4><g:message code="userGroup.error.addUser"/></h4>
        <g:renderErrors bean="${userGroupInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="addUserSave" class="offset1 span8">
    <g:hiddenField name="id" value="${userGroupInstance?.id}"/>

    <div class="control-group ${hasErrors(bean: userGroupInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="userGroup.user.label" default="User"/>
        </carm:label>
        <div class="controls">
        <g:select name="userId" from="${userList}" optionKey="id"
                  value="${user?.id}" noSelection="['null': '']"
                  title="${message(code: 'userGroup.user.help')}"/>
        </div>
    </div>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" action="list"><g:message code="default.button.cancel.label" default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
