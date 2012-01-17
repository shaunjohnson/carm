<%@ page import="carm.ApplicationDeployment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'ApplicationDeployment')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationDeploymentInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationDeploymentInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationDeploymentInstance}" as="list" />
        </div>
    </g:hasErrors>

    <g:form method="post" >
        <g:hiddenField name="id" value="${applicationDeploymentInstance?.id}" />
        <g:hiddenField name="version" value="${applicationDeploymentInstance?.version}" />
        <div class="dialog">
            <table>
                <tbody>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="applicationRelease"><g:message code="applicationDeployment.applicationRelease.label" default="Application Release" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'applicationRelease', 'errors')}">
                            <g:select name="applicationRelease.id" from="${carm.ApplicationRelease.list()}" optionKey="id" value="${applicationDeploymentInstance?.applicationRelease?.id}"  />
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="systemEnvironment"><g:message code="applicationDeployment.systemEnvironment.label" default="System Environment" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'systemEnvironment', 'errors')}">
                            <g:select name="systemEnvironment.id" from="${carm.SystemEnvironment.list()}" optionKey="id" value="${applicationDeploymentInstance?.systemEnvironment?.id}"  />
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="deploymentInstructions"><g:message code="applicationDeployment.deploymentInstructions.label" default="Deployment Instructions" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentInstructions', 'errors')}">
                            <g:textField name="deploymentInstructions" value="${applicationDeploymentInstance?.deploymentInstructions}" />
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="requestedDeploymentDate"><g:message code="applicationDeployment.requestedDeploymentDate.label" default="Requested Deployment Date" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'requestedDeploymentDate', 'errors')}">
                            <g:datePicker name="requestedDeploymentDate" precision="day" value="${applicationDeploymentInstance?.requestedDeploymentDate}"  />
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="completedDeploymentDate"><g:message code="applicationDeployment.completedDeploymentDate.label" default="Completed Deployment Date" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'completedDeploymentDate', 'errors')}">
                            <g:datePicker name="completedDeploymentDate" precision="day" value="${applicationDeploymentInstance?.completedDeploymentDate}" default="none" noSelection="['': '']" />
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="deploymentState"><g:message code="applicationDeployment.deploymentState.label" default="Deployment State" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentState', 'errors')}">
                            <g:textField name="deploymentState" maxlength="50" value="${applicationDeploymentInstance?.deploymentState}" />
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                          <label for="moduleDeployments"><g:message code="applicationDeployment.moduleDeployments.label" default="Module Deployments" /></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'moduleDeployments', 'errors')}">
                            <ul>
                                <g:each in="${applicationDeploymentInstance?.moduleDeployments?}" var="m">
                                    <li><g:link controller="moduleDeployment" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller="moduleDeployment" action="create" params="['applicationDeployment.id': applicationDeploymentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'moduleDeployment.label', default: 'ModuleDeployment')])}</g:link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
