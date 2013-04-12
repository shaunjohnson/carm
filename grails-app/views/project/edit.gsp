<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${projectInstance}">
    <div class="alert alert-error">
        <h4><g:message code="project.error.update"/></h4>
        <g:renderErrors bean="${projectInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${projectInstance?.id}"/>
    <g:hiddenField name="version" value="${projectInstance?.version}"/>

    <g:render template="form" model="[projectInstance: projectInstance, projectCategoryList: projectCategoryList]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save" value="${message(code: 'default.button.update.label')}"/>
        <g:link class="btn" action="show" id="${projectInstance.id}"><g:message
                code="default.button.cancel.label"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
