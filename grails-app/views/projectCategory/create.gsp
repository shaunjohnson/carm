<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectCategoryInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${projectCategoryInstance}">
    <div class="alert alert-error">
        <h4><g:message code="projectCategory.error.create"/></h4>
        <g:renderErrors bean="${projectCategoryInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <div class="control-group ${hasErrors(bean: projectCategoryInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="projectCategory.name.label" default="Name"/>
        </carm:label>
        <div class="controls">
            <g:textField name="name" maxlength="50" size="50"
                         value="${projectCategoryInstance?.name}"
                         required="required"
                         title="${message(code: 'projectCategory.name.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: projectCategoryInstance, field: 'description', 'error')}">
        <carm:label class="control-label" for="description">
            <g:message code="projectCategory.description.label" default="Description"/>
        </carm:label>
        <div class="controls">
            <g:textArea name="description"
                        cols="${grailsApplication.config.ui.textarea.cols}"
                        rows="${grailsApplication.config.ui.textarea.rows}"
                        value="${projectCategoryInstance?.description}"
                        title="${message(code: 'projectCategory.description.help')}"/>
        </div>
    </div>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" action="list">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
