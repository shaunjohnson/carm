<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${applicationInstance}"
             pageName="${message(code: 'default.create.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationInstance}">
    <div class="alert alert-error">
        <h4><g:message code="application.error.create"/></h4>
        <g:renderErrors bean="${applicationInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <g:hiddenField name="project.id" value="${applicationInstance?.project?.id}"/>

    <g:render template="form"
              model="[applicationInstance: applicationInstance, applicationTypeList: applicationTypeList,
                      notificationSchemeList: notificationSchemeList, systemEnvironmentList: systemEnvironmentList]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create" value="${message(code: 'default.button.create.label')}"/>
        <g:link class="btn" controller="project" action="show" id="${applicationInstance?.project?.id}">
            <g:message code="default.button.cancel.label"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
