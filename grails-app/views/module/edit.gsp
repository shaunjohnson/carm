<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${moduleInstance}" pageName="${message(code: 'default.edit.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${moduleInstance}">
    <div class="alert alert-error">
        <h4><g:message code="module.error.update"/></h4>
        <g:renderErrors bean="${moduleInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${moduleInstance?.id}"/>
    <g:hiddenField name="version" value="${moduleInstance?.version}"/>
    <g:hiddenField name="application.id" value="${moduleInstance?.application?.id}"/>

    <g:render template="form" model="[moduleInstance: moduleInstance, moduleTypeList: moduleTypeList]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save" value="${message(code: 'default.button.update.label')}"/>
        <g:link class="btn" action="show" id="${moduleInstance.id}">
            <g:message code="default.button.cancel.label"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
