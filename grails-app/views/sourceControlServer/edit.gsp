<%@ page import="carm.sourcecontrol.SourceControlServerType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${sourceControlServerInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${sourceControlServerInstance}">
        <div class="errors">
            <g:renderErrors bean="${sourceControlServerInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${sourceControlServerInstance?.id}"/>
        <g:hiddenField name="version" value="${sourceControlServerInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="sourceControlServer.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${sourceControlServerInstance?.name}"
                                     required="required"
                                     title="${message(code: 'sourceControlServer.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="sourceControlServer.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${sourceControlServerInstance?.description}"
                                    title="${message(code: 'sourceControlServer.description.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="type" required="true">
                            <g:message code="sourceControlServer.type.label" default="Type"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'type', 'errors')}">
                        <g:select name="type" from="${SourceControlServerType?.values()}"
                                  keys="${SourceControlServerType?.values()*.name()}"
                                  value="${sourceControlServerInstance?.type?.name()}"
                                  required="required"
                                  title="${message(code: 'sourceControlServer.type.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="url" required="true">
                            <g:message code="sourceControlServer.url.label" default="URL"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: sourceControlServerInstance, field: 'url', 'errors')}">
                        <g:textField name="url" maxlength="200" size="50" required="required"
                                     value="${sourceControlServerInstance?.url}"
                                     title="${message(code: 'sourceControlServer.url.help')}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${sourceControlServerInstance.id}">
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
