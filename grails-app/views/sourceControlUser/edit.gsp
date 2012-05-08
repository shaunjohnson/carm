<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'Source Control User')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${sourceControlUserInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${sourceControlUserInstance}">
        <div class="errors">
            <g:renderErrors bean="${sourceControlUserInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${sourceControlUserInstance?.id}"/>
        <g:hiddenField name="version" value="${sourceControlUserInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="server.id">
                            <g:message code="sourceControlUser.server.label" default="Source Control Server"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlUserInstance, field: 'server', 'errors')}">
                        <g:link controller="sourceControlServer" action="show"
                                id="${sourceControlUserInstance?.server?.id}">
                            ${sourceControlUserInstance?.server?.name?.encodeAsHTML()}
                        </g:link>
                        <g:hiddenField name="server.id" value="${sourceControlUserInstance?.server?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="sourceControlUser.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlUserInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     required="required"
                                     value="${sourceControlUserInstance?.name}"
                                     title="${message(code: 'sourceControlUser.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="sourceControlUser.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlUserInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${sourceControlUserInstance?.description}"
                                    title="${message(code: 'sourceControlUser.description.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="user.id">
                            <g:message code="sourceControlUser.user.label" default="User"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlUserInstance, field: 'user', 'errors')}">
                        <g:select name="user.id" from="${userList}" optionKey="id"
                                  value="${sourceControlUserInstance?.user?.id}" noSelection="['null': '']"
                                  title="${message(code: 'sourceControlUser.user.help')}"/>
                    </td>
                </tr>
                </tbody>

                <carm:formFooter>
                    <div class="buttons">
                        <g:link action="show" id="${sourceControlUserInstance.id}">
                            <g:message code="default.button.cancel.label" default="Cancel"/>
                        </g:link>
                        <g:submitButton name="save"
                                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
                    </div>
                </carm:formFooter>
            </table>
        </div>
    </g:form>
</div>
</body>
</html>
