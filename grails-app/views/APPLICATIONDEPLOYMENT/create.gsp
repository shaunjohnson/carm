<%@ page import="carm.ApplicationDeployment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationDeploymentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationDeploymentInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationDeploymentInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="applicationRelease.id">
                            <g:message code="applicationDeployment.applicationRelease.label"
                                       default="Application Release"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'applicationRelease', 'errors')}">
                        ${applicationDeploymentInstance?.applicationRelease?.application?.encodeAsHTML()} :
                        ${applicationDeploymentInstance?.applicationRelease?.releaseNumber?.encodeAsHTML()}
                        <g:hiddenField name="applicationRelease.id"
                                       value="${applicationDeploymentInstance?.applicationRelease?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="sysEnvironment.id" required="true">
                            <g:message code="applicationDeployment.sysEnvironment.label" default="Environment"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'environment', 'errors')}">
                        <g:select name="sysEnvironment.id" noSelection="['null': '']" required="required"
                                  from="${applicationDeploymentInstance?.applicationRelease?.application?.system?.environments}"
                                  optionKey="id" value="${applicationDeploymentInstance?.sysEnvironment?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="requestedDeploymentDate">
                            <g:message code="applicationDeployment.requestedDeploymentDate.label"
                                       default="Requested Deployment Date"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'requestedDeploymentDate', 'errors')}">
                        <g:datePicker name="requestedDeploymentDate" precision="day"
                                      value="${applicationDeploymentInstance?.requestedDeploymentDate}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="deploymentInstructions">
                            <g:message code="applicationDeployment.deploymentInstructions.label"
                                       default="Deployment Instructions"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentInstructions', 'errors')}">
                        <richui:richTextEditor name="deploymentInstructions"
                                               value="${applicationDeploymentInstance?.deploymentInstructions}"
                                               height="${grailsApplication.config.ui.richTextEditor.height}"
                                               width="${grailsApplication.config.ui.richTextEditor.width}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button">
                <g:link class="show" controller="application" action="show"
                        id="${applicationDeploymentInstance?.applicationRelease?.application?.id}">
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
