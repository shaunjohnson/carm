<%@ page import="carm.deployment.ModuleDeploymentState" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${applicationDeploymentInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationDeploymentInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationDeployment.error.update"/></h4>
        <g:renderErrors bean="${applicationDeploymentInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${applicationDeploymentInstance?.id}"/>
    <g:hiddenField name="version" value="${applicationDeploymentInstance?.version}"/>

    <g:render template="form" model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save" value="${message(code: 'default.button.update.label')}"/>
        <g:link class="btn" controller="application" action="show"
                id="${applicationDeploymentInstance.applicationRelease.application.id}">
            <g:message code="default.button.cancel.label"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
