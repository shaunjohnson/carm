<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${applicationInstance}" pageName="${message(code: 'default.edit.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationInstance}">
    <div class="alert alert-error">
        <h4><g:message code="application.error.create"/></h4>
        <g:renderErrors bean="${applicationInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${applicationInstance?.id}"/>
    <g:hiddenField name="version" value="${applicationInstance?.version}"/>

    <g:render template="form"
              model="[applicationInstance: applicationInstance, applicationTypeList: applicationTypeList,
                      notificationSchemeList: notificationSchemeList, systemEnvironmentList: systemEnvironmentList]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save" value="${message(code: 'default.button.update.label')}"/>
        <g:link class="btn" action="show" id="${applicationInstance.id}">
            <g:message code="default.button.cancel.label"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
