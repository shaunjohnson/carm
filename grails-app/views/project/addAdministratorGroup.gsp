<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'projectAdministrator.label', default: 'Project Administrator')}"/>
    <title><g:message code="default.addAdministratorGroup.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectInstance}"
             pageName="${message(code: 'default.addAdministratorGroup.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${projectInstance}">
    <div class="alert alert-error">
        <h4><g:message code="project.error.addAdministrator"/></h4>
        <g:renderErrors bean="${projectInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="addAdministratorGroupSave" class="offset1 span8">
    <g:hiddenField name="id" value="${projectInstance?.id}"/>

    <div class="control-group">
        <carm:label class="control-label" for="groupId" required="true">
            <g:message code="project.administrator.group.label" default="Group"/>
        </carm:label>
        <div class="controls">
            <g:select name="groupId" from="${userGroupList}" optionKey="id"
                      value="${userGroup?.id}" noSelection="['null': '']"
                      title="${message(code: 'project.administrator.group.help')}"/>
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
