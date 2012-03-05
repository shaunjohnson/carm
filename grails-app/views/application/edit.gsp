<%@ page import="carm.sourcecontrol.SourceControlRepository; carm.application.ApplicationType; carm.application.Application; carm.system.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationInstance}" pageName="${message(code: 'default.edit.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${applicationInstance?.id}"/>
        <g:hiddenField name="version" value="${applicationInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="project.id">
                            <g:message code="application.project.label" default="Project"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'project', 'errors')}">
                        <g:link controller="project" action="show" id="${applicationInstance.project.id}">
                            ${applicationInstance?.project?.encodeAsHTML()}
                        </g:link>
                        <g:hiddenField name="project.id" value="${applicationInstance?.project?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="application.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${applicationInstance?.name}"
                                     required="required"
                                     title="${message(code: 'application.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="type.id" required="true">
                            <g:message code="application.type.label" default="Type"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'type', 'errors')}">
                        <g:select name="type.id" from="${ApplicationType.list().sort { it.name }}" optionKey="id"
                                  value="${applicationInstance?.type?.id}" required="required"
                                  title="${message(code: 'application.type.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="application.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" value="${applicationInstance?.description}"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    title="${message(code: 'application.description.help')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="sourceControlRepository.id" required="true">
                            <g:message code="application.sourceControlRepository.label"
                                       default="Source Control Repository"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationInstance, field: 'sourceControlRepository', 'errors')}">
                        <g:select name="sourceControlRepository.id" from="${SourceControlRepository.list()}"
                                  optionKey="id"
                                  value="${applicationInstance?.sourceControlRepository?.id}" noSelection="['null': '']"
                                  required="required"
                                  title="${message(code: 'application.sourceControlRepository.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="sourceControlPath">
                            <g:message code="application.sourceControlPath.label" default="Source Control Path"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationInstance, field: 'sourceControlPath', 'errors')}">
                        <g:textField name="sourceControlPath" maxlength="200" size="50"
                                     value="${applicationInstance?.sourceControlPath}"
                                     title="${message(code: 'application.sourceControlPath.help')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="system.id">
                            <g:message code="application.system.label" default="System"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'system', 'errors')}">
                        <g:select name="system.id" from="${System.list().sort { it.name }}" optionKey="id"
                                  value="${applicationInstance?.system?.id}" noSelection="['null': '']"
                                  title="${message(code: 'application.system.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="deployInstructions">
                            <g:message code="application.deployInstructions.label" default="Deploy Instructions"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationInstance, field: 'deployInstructions', 'errors')}">
                        <richui:richTextEditor name="deployInstructions"
                                               value="${applicationInstance?.deployInstructions}"
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
                <g:link class="show" action="show" id="${applicationInstance.id}">
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
