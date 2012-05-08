<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleTypeInstance}"
                 pageName="${message(code: 'default.create.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleTypeInstance}">
        <div class="errors">
            <g:renderErrors bean="${moduleTypeInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="moduleType.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleTypeInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${moduleTypeInstance?.name}"
                                     required="required"
                                     title="${message(code: 'moduleType.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="moduleType.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: moduleTypeInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${moduleTypeInstance?.description}"
                                    title="${message(code: 'moduleType.description.help')}"/>
                    </td>
                </tr>
                </tbody>

                <carm:formFooter>
                    <div class="buttons">
                        <g:link action="list"><g:message code="default.button.cancel.label" default="Cancel"/></g:link>
                        <g:submitButton name="create"
                                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                    </div>
                </carm:formFooter>
            </table>
        </div>
    </g:form>
</div>
</body>
</html>
