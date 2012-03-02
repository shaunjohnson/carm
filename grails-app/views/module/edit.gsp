<%@ page import="carm.module.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleInstance}" pageName="${message(code: 'default.edit.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleInstance}">
        <div class="errors">
            <g:renderErrors bean="${moduleInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${moduleInstance?.id}"/>
        <g:hiddenField name="version" value="${moduleInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="application.id">
                            <g:message code="module.application.label" default="Application"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'application', 'errors')}">
                        <g:link controller="application" action="show" id="${moduleInstance?.application?.id}">
                            ${moduleInstance?.application?.encodeAsHTML()}
                        </g:link>
                        <g:hiddenField name="application.id" value="${moduleInstance?.application?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="module.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${moduleInstance?.name}"
                                     required="required"
                                     title="${message(code: 'module.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="type.id" required="true">
                            <g:message code="module.type.label" default="Type"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'type', 'errors')}">
                        <g:select name="type.id" from="${carm.ModuleType.list().sort { it.name }}" optionKey="id"
                                  noSelection="['null': '']" value="${moduleInstance?.type?.id}"
                                  required="required"
                                  title="${message(code: 'module.type.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="module.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${moduleInstance?.description}"
                                    title="${message(code: 'module.description.help')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="systemComponents">
                            <g:message code="module.systemComponents.label" default="System Components"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: moduleInstance, field: 'systemComponents', 'errors')}">
                        <g:if test="${moduleInstance.application.system}">
                            <g:select name="systemComponents" multiple="yes" optionKey="id" size="5"
                                      from="${moduleInstance.application.system.components.sort { it.name }}"
                                      value="${moduleInstance?.systemComponents*.id}"
                                      title="${message(code: 'module.systemComponents.help')}"/>
                        </g:if>
                        <g:else>
                            <p class="emphasis">
                                <g:message code="moduleCannotBeDeployed.message"
                                           default="This module cannot be deployed to any component since the application is not associated with a system."/>
                            </p>
                            <g:hiddenField name="systemComponents" value="${null}"/>
                        </g:else>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="deployInstructions">
                            <g:message code="module.deployInstructions.label" default="Deploy Instructions"/>
                        </carm:label>
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

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${moduleInstance.id}">
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
