<%@ page import="carm.ModuleType; carm.SystemComponent; carm.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleInstance}" pageName="${message(code: 'default.create.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleInstance}">
        <div class="errors">
            <g:renderErrors bean="${moduleInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="application.id">
                            <g:message code="module.application.label" default="Application"/>
                        </label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'application', 'errors')}">
                        ${moduleInstance?.application?.encodeAsHTML()}
                        <g:hiddenField name="application.id" value="${moduleInstance?.application?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name">
                            <g:message code="module.name.label" default="Name"/>
                        </label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${moduleInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="type.id">
                            <g:message code="module.type.label" default="Type"/>
                        </label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'type', 'errors')}">
                        <g:select name="type.id" from="${ModuleType.list().sort { it.name }}" optionKey="id"
                                  value="${moduleInstance?.type?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description">
                            <g:message code="module.description.label" default="Description"/>
                        </label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${moduleInstance?.description}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="systemComponents">
                            <g:message code="module.systemComponents.label" default="System Components"/>
                        </label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: moduleInstance, field: 'systemComponents', 'errors')}">
                        <g:select name="systemComponents" multiple="yes" optionKey="id" size="5"
                                  from="${moduleInstance.application.system.components.sort { it.name }}"
                                  value="${moduleInstance?.systemComponents*.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="deployInstructions">
                            <g:message code="module.deployInstructions.label" default="Deploy Instructions"/>
                        </label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: moduleInstance, field: 'deployInstructions', 'errors')}">
                        <richui:richTextEditor name="deployInstructions" value="${moduleInstance?.deployInstructions}"
                                               height="${grailsApplication.config.ui.richTextEditor.height}"
                                               width="${grailsApplication.config.ui.richTextEditor.width}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button">
                <g:link class="show" controller="application" action="show" id="${moduleInstance.application.id}">
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
