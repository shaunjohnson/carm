<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${applicationReleaseInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationReleaseInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationRelease.error.create"/></h4>
        <g:renderErrors bean="${applicationReleaseInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <g:render template="form" model="[applicationReleaseInstance: applicationReleaseInstance]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create" value="${message(code: 'default.button.create.label')}"/>
        <g:link class="btn" controller="application" action="show" id="${applicationReleaseInstance?.application?.id}">
            <g:message code="default.button.cancel.label"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
