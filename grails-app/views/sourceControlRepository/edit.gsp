<%@ page import="carm.SourceControlRepository" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRepository.label', default: 'SourceControlRepository')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${sourceControlRepositoryInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${sourceControlRepositoryInstance}">
        <div class="errors">
            <g:renderErrors bean="${sourceControlRepositoryInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${sourceControlRepositoryInstance?.id}"/>
        <g:hiddenField name="version" value="${sourceControlRepositoryInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="server"><g:message code="sourceControlRepositoryInstance.server.label" default="Source Control Server"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlRepositoryInstance, field: 'server', 'errors')}">
                        ${sourceControlRepositoryInstance?.server?.name?.encodeAsHTML()}
                        <g:hiddenField name="server.id" value="${sourceControlRepositoryInstance?.server?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="sourceControlRepository.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlRepositoryInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${sourceControlRepositoryInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="sourceControlRepository.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlRepositoryInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${sourceControlRepositoryInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="path"><g:message code="sourceControlRepository.path.label" default="Path"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlRepositoryInstance, field: 'path', 'errors')}">
                        <g:textField name="path" value="${sourceControlRepositoryInstance?.path}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${sourceControlRepositoryInstance.id}">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="save" class="save" value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
