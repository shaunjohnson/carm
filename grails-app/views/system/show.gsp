<%@ page import="carm.Application; carm.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="systemDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="system.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <carm:plainText value="${systemInstance?.description}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="system.components.label" default="Components"/>
                </td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${systemInstance.components.size()}">
                        <ul>
                            <g:each in="${systemInstance.components.sort { it.name }}" var="component">
                                <li>
                                    <g:link controller="systemComponent" action="show" id="${component.id}">
                                        ${component?.encodeAsHTML()}
                                    </g:link>
                                </li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        <p class="emphasis">
                            <g:message code="systemDoesNotHaveAnyComponents.message"
                                       default="This system does not have any components."/>
                        </p>
                    </g:else>

                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <div class="nav">
                            <span class="menuButton">
                                <g:link class="create" controller="systemComponent" action="create"
                                        params="['system.id': systemInstance?.id]">
                                    <g:message code="addComponent.label" default="Add Component"/>
                                </g:link>
                            </span>
                        </div>
                    </sec:ifAllGranted>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="system.environments.label" default="Environments"/>
                </td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${systemInstance.environments.size()}">
                        <table class="ol">
                            <tbody>
                            <g:each in="${systemInstance.environments}" var="environment" status="eindex">
                                <tr>
                                    <td class="olIndex">
                                        ${eindex + 1}.
                                    </td>
                                    <td class="olContent">
                                        <g:link controller="systemEnvironment" action="show" id="${environment?.id}">
                                            ${environment?.encodeAsHTML()}
                                        </g:link>
                                    </td>
                                    <td>
                                        <sec:ifAllGranted roles="ROLE_ADMIN">
                                            <g:if test="${eindex > 0}">
                                                <carm:moveUp controller="system" action="moveEnvUp"
                                                             id="${environment.id}"
                                                             params="[systemId: systemInstance.id, index: eindex]"/>
                                            </g:if>
                                        </sec:ifAllGranted>
                                    </td>
                                    <td>
                                        <sec:ifAllGranted roles="ROLE_ADMIN">
                                            <g:if test="${eindex + 1 < systemInstance.environments.size()}">
                                                <carm:moveDown controller="system" action="moveEnvDown"
                                                               id="${environment.id}"
                                                               params="[systemId: systemInstance.id, index: eindex]"/>
                                            </g:if>
                                        </sec:ifAllGranted>
                                    </td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </g:if>
                    <g:else>
                        <p class="emphasis">
                            <g:message code="systemDoesNotHaveAnyEnvironments.message"
                                       default="This system does not have any environments."/>
                        </p>
                    </g:else>

                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <div class="nav">
                            <span class="menuButton">
                                <g:link class="create" controller="systemEnvironment" action="create"
                                        params="['system.id': systemInstance?.id]">
                                    <g:message code="addEnvironment.label" default="Add Environment"/>
                                </g:link>
                            </span>
                        </div>
                    </sec:ifAllGranted>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="system.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="system.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="systemDetails" entityName="${entityName}">
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${systemInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <carm:ifNotInUse domain="${systemInstance}">
                        <span class="button">
                            <g:link class="delete" action="delete" id="${systemInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </carm:ifNotInUse>
                </div>
            </sec:ifAllGranted>
        </carm:showHideDetails>
    </div>

    <g:render template="systemApplications"
              model="['systemInstance': systemInstance, 'applicationsGrouped': applicationsGrouped, 'latestDeployments': latestDeployments]"/>
</div>
</body>
</html>
