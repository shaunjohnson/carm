<%@ page import="carm.enums.SourceControlServerType; carm.SourceControlServer" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <g:header domain="${sourceControlServerInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${sourceControlServerInstance}">
        <div class="errors">
            <g:renderErrors bean="${sourceControlServerInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name">
                            <g:message code="sourceControlServer.name.label" default="Name"/>
                        </label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${sourceControlServerInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description">
                            <g:message code="sourceControlServer.description.label" default="Description"/>
                        </label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5"
                                    value="${sourceControlServerInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="type">
                            <g:message code="sourceControlServer.type.label" default="Type"/>
                        </label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'type', 'errors')}">
                        <g:select name="type" from="${SourceControlServerType?.values()}"
                                  keys="${SourceControlServerType?.values()*.name()}"
                                  value="${sourceControlServerInstance?.type?.name()}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="url">
                            <g:message code="sourceControlServer.url.label" default="URL"/>
                        </label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'url', 'errors')}">
                        <g:textField name="url" maxlength="200" value="${sourceControlServerInstance?.url}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button">
                <g:link class="list" action="list"><g:message code="default.button.cancel.label" default="Cancel"/>
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
