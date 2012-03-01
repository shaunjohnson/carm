<%@ page import="carm.system.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'System Environment')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemEnvironmentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${systemEnvironmentInstance}">
        <div class="errors">
            <g:renderErrors bean="${systemEnvironmentInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="system.id">
                            <g:message code="systemEnvironment.system.label" default="System"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value">
                        <g:link controller="system" action="show" id="${systemEnvironmentInstance?.system?.id}">
                            ${systemEnvironmentInstance?.system?.encodeAsHTML()}
                        </g:link>
                        <g:hiddenField name="system.id" value="${systemEnvironmentInstance?.system?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="systemEnvironment.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: systemEnvironmentInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${systemEnvironmentInstance?.name}"
                                     required="required"
                                     title="${message(code: 'systemEnvironment.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="systemEnvironment.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: systemEnvironmentInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${systemEnvironmentInstance?.description}"
                                    title="${message(code: 'systemEnvironment.description.help')}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="show" controller="system" action="show" id="${systemEnvironmentInstance.system.id}">
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
