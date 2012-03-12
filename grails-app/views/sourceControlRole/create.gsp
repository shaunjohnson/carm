<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${sourceControlRoleInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${sourceControlRoleInstance}">
        <div class="errors">
            <g:renderErrors bean="${sourceControlRoleInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="sourceControlRole.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlRoleInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${sourceControlRoleInstance?.name}"
                                     required="required"
                                     title="${message(code: 'sourceControlRole.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="sourceControlRole.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlRoleInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${sourceControlRoleInstance?.description}"
                                    title="${message(code: 'sourceControlRole.description.help')}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="list" action="list">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="create" class="save"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
