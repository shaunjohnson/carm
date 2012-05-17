<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationTypeInstance}"
             pageName="${message(code: 'default.create.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${applicationTypeInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationType.error.create"/></h4>
        <g:renderErrors bean="${applicationTypeInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <div class="control-group ${hasErrors(bean: applicationTypeInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="applicationType.name.label" default="Name"/>
        </carm:label>
        <div class="controls">
            <g:textField name="name" maxlength="50" size="50"
                         value="${applicationTypeInstance?.name}"
                         required="required"
                         title="${message(code: 'applicationType.name.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: applicationTypeInstance, field: 'description', 'error')}">
        <carm:label class="control-label" for="description" required="true">
            <g:message code="applicationType.description.label" default="Description"/>
        </carm:label>
        <div class="controls">
            <g:textArea name="description"
                        cols="${grailsApplication.config.ui.textarea.cols}"
                        rows="${grailsApplication.config.ui.textarea.rows}"
                        value="${applicationTypeInstance?.description}"
                        title="${message(code: 'applicationType.description.help')}"/>
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
