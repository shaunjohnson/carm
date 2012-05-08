<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${projectCategoryInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${projectCategoryInstance}">
        <div class="errors">
            <g:renderErrors bean="${projectCategoryInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="projectCategory.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: projectCategoryInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${projectCategoryInstance?.name}"
                                     required="required"
                                     title="${message(code: 'projectCategory.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="projectCategory.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: projectCategoryInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${projectCategoryInstance?.description}"
                                    title="${message(code: 'projectCategory.description.help')}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <carm:formFooter>
            <div class="buttons">
                <g:link action="list"><g:message code="default.button.cancel.label" default="Cancel"/></g:link>
                <g:submitButton name="create"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </div>
        </carm:formFooter>
    </g:form>
</div>
</body>
</html>
