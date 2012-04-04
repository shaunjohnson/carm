<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${projectInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${projectInstance}">
        <div class="errors">
            <g:renderErrors bean="${projectInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${projectInstance?.id}"/>
        <g:hiddenField name="version" value="${projectInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="project.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${projectInstance?.name}"
                                     required="required"
                                     title="${message(code: 'project.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="category.id" required="true">
                            <g:message code="project.category.label" default="Category"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'category', 'errors')}">
                        <g:select name="category.id" from="${projectCategoryList}"
                                  optionKey="id" value="${projectInstance?.category?.id}"
                                  required="required"
                                  title="${message(code: 'project.category.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="project.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${projectInstance?.description}"
                                    title="${message(code: 'project.description.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="projectOwners" required="true">
                            <g:message code="project.projectOwners.label" default="Project Owners"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value">
                        <g:select name="projectOwners" from="${projectOwnerList}"
                                  optionKey="username" optionValue="fullName"
                                  value="${projectOwners}" multiple="true"
                                  required="required"
                                  title="${message(code: 'project.projectOwners.help')}"/>
                    </td>
                </tr>
                </tbody>
            </tbody>
            </table>
        </div>

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${projectInstance.id}">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="save" class="save"
                                value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
