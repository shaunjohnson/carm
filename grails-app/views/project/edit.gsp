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

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${projectInstance}">
    <div class="alert alert-error">
        <h4><g:message code="project.error.update"/></h4>
        <g:renderErrors bean="${projectInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <carm:formSection legend="${message(code: 'projectDetails.section')}">
        <div class="control-group ${hasErrors(bean: projectInstance, field: 'name', 'error')}">
            <carm:label class="control-label" for="name" required="true">
                <g:message code="project.name.label" default="Name"/>
            </carm:label>
            <div class="controls">
                <g:textField name="name" maxlength="50" size="50"
                             value="${projectInstance?.name}"
                             required="required"
                             title="${message(code: 'project.name.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: projectInstance, field: 'category', 'error')}">
            <carm:label class="control-label" for="category.id" required="true">
                <g:message code="project.category.label" default="Category"/>
            </carm:label>
            <div class="controls">
                <g:select name="category.id" from="${projectCategoryList}"
                          optionKey="id" value="${projectInstance?.category?.id}"
                          required="required"
                          title="${message(code: 'project.category.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: projectInstance, field: 'description', 'error')}">
            <carm:label class="control-label" for="description">
                <g:message code="project.description.label" default="Description"/>
            </carm:label>
            <div class="controls">
                <g:textArea name="description"
                            cols="${grailsApplication.config.ui.textarea.cols}"
                            rows="${grailsApplication.config.ui.textarea.rows}"
                            value="${projectInstance?.description}"
                            title="${message(code: 'project.description.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'permissions.section')}">
        <div class="control-group">
            <carm:label class="control-label" for="projectOwners" required="true">
                <g:message code="project.projectOwners.label" default="Project Owners"/>
            </carm:label>
            <div class="controls">
                <g:select name="projectOwners" from="${projectOwnerList}"
                          optionKey="username" optionValue="fullName"
                          value="${projectOwners}" multiple="true"
                          required="required"
                          title="${message(code: 'project.projectOwners.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${projectInstance.id}"><g:message code="default.button.cancel.label"
                                                                                default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
