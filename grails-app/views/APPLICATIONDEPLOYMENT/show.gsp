<%@ page import="carm.ApplicationDeployment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationDeploymentInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="applicationDeploymentDetails" class="details">
            <tbody>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.applicationRelease.label" default="Application Release" /></td>
                    <td valign="top" class="value"><g:link controller="applicationRelease" action="show" id="${applicationDeploymentInstance?.applicationRelease?.id}">${applicationDeploymentInstance?.applicationRelease?.encodeAsHTML()}</g:link></td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.environment.label" default="System Environment" /></td>
                    <td valign="top" class="value"><g:link controller="systemEnvironment" action="show" id="${applicationDeploymentInstance?.environment?.id}">${applicationDeploymentInstance?.environment?.encodeAsHTML()}</g:link></td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.deploymentInstructions.label" default="Deployment Instructions" /></td>
                    <td valign="top" class="value">${fieldValue(bean: applicationDeploymentInstance, field: "deploymentInstructions").decodeHTML()}</td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.requestedDeploymentDate.label" default="Requested Deployment Date" /></td>
                    <td valign="top" class="value"><g:formatDate date="${applicationDeploymentInstance?.requestedDeploymentDate}" /></td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.completedDeploymentDate.label" default="Completed Deployment Date" /></td>
                    <td valign="top" class="value"><g:formatDate date="${applicationDeploymentInstance?.completedDeploymentDate}" /></td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.deploymentState.label" default="Deployment State" /></td>
                    <td valign="top" class="value">${fieldValue(bean: applicationDeploymentInstance, field: "deploymentState")}</td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.dateCreated.label" default="Date Created" /></td>
                    <td valign="top" class="value"><g:formatDate date="${applicationDeploymentInstance?.dateCreated}" /></td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.lastUpdated.label" default="Last Updated" /></td>
                    <td valign="top" class="value"><g:formatDate date="${applicationDeploymentInstance?.lastUpdated}" /></td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="applicationDeployment.moduleDeployments.label" default="Module Deployments" /></td>
                    <td valign="top" style="text-align: left;" class="value">
                        <ul>
                        <g:each in="${applicationDeploymentInstance.moduleDeployments}" var="m">
                            <li><g:link controller="moduleDeployment" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
                        </g:each>
                        </ul>
                    </td>
                </tr>
            </tbody>
            <tfoot class="detailProp">
            <tr>
                <td colspan="2">
                    <div class="buttons">
                        <span class="button">
                            <g:link class="edit" action="edit" id="${applicationDeploymentInstance?.id}">
                                <g:message code="default.button.edit.label" default="Edit"/>
                            </g:link>
                        </span>
                        <span class="button">
                            <g:link class="delete" action="delete" id="${applicationDeploymentInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
        <g:showHideDetails sectionId="applicationDeploymentDetails" entityName=" ${entityName}"/>
    </div>
</div>
</body>
</html>
