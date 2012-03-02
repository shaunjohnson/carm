<%@ page import="carm.module.ModuleDeploymentTestState" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'moduleDeploymentTestState.label', default: 'Module Deployment Test State')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleDeploymentTestStateInstance}"
              pageName="${message(code: 'default.edit.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleDeploymentTestStateInstance}">
        <div class="errors">
            <g:renderErrors bean="${moduleDeploymentTestStateInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${moduleDeploymentTestStateInstance?.id}"/>
        <g:hiddenField name="version" value="${moduleDeploymentTestStateInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="moduleDeploymentTestState.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: moduleDeploymentTestStateInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${moduleDeploymentTestStateInstance?.name}"
                                     required="required"
                                     title="${message(code: 'moduleDeploymentTestState.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="moduleDeploymentTestState.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: moduleDeploymentTestStateInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${moduleDeploymentTestStateInstance?.description}"
                                    title="${message(code: 'moduleDeploymentTestState.description.help')}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${moduleDeploymentTestStateInstance.id}">
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
