<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${projectInstance}">
    <div class="alert alert-error">
        <h4><g:message code="project.error.create"/></h4>
        <g:renderErrors bean="${projectInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <g:render template="form" model="[projectInstance: projectInstance, projectCategoryList: projectCategoryList]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label')}"/>
        <g:link class="btn" action="list"><g:message code="default.button.cancel.label"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
