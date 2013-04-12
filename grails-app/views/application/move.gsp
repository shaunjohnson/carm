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
<carm:header domain="${applicationInstance}" pageName="${message(code: 'default.move.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationInstance}">
    <div class="alert alert-error">
        <h4><g:message code="application.error.move"/></h4>
        <g:renderErrors bean="${applicationInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="saveMove" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${applicationInstance?.id}"/>
    <g:hiddenField name="version" value="${applicationInstance?.version}"/>

    <carm:formSection legend="${message(code: 'applicationDetails.section')}">
        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'type', 'error')}">
            <carm:label class="control-label" for="project.id" required="true">
                <g:message code="application.project.label" default="Project"/>
            </carm:label>
            <div class="controls">
                <g:select name="project.id" from="${projectList}" optionKey="id"
                          value="${applicationInstance?.project?.id}"
                          required="required"
                          title="${message(code: 'application.project.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.move.label', default: 'Move')}"/>
        <g:link class="btn" action="show" id="${applicationInstance.id}"><g:message
                code="default.button.cancel.label" default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
