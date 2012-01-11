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
    <g:header domain="${systemInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="systemDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.components.label" default="Components"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${systemInstance.components.sort { it.name }}" var="c">
                            <li><g:link controller="systemComponent" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <div class="nav">
                            <span class="menuButton"><g:link class="create" controller="systemComponent" action="create" params="['system.id': systemInstance?.id]">Add Component</g:link></span>
                        </div>
                    </sec:ifAllGranted>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.environments.label" default="Environments"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <table class="ol">
                        <tbody>
                        <g:each in="${systemInstance.environments}" var="e" status="eindex">
                            <tr>
                                <td class="olIndex">${eindex + 1}.</td>
                                <td class="olContent">
                                    <g:link controller="systemEnvironment" action="show" id="${e?.id}">${e?.encodeAsHTML()}</g:link>
                                </td>
                                <td>
                                    <sec:ifAllGranted roles="ROLE_ADMIN">
                                        <g:if test="${eindex > 0}">
                                            <g:moveUp controller="system" action="moveEnvUp" id="${e.id}"
                                                    params="[systemId: systemInstance.id, index: eindex]" />
                                        </g:if>
                                    </sec:ifAllGranted>
                                </td>
                                <td>
                                    <sec:ifAllGranted roles="ROLE_ADMIN">
                                        <g:if test="${eindex + 1 < systemInstance.environments.size()}">
                                            <g:moveDown controller="system" action="moveEnvDown" id="${e.id}"
                                                    params="[systemId: systemInstance.id, index: eindex]"/>
                                        </g:if>
                                    </sec:ifAllGranted>
                                </td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <div class="nav">
                            <span class="menuButton">
                                <g:link class="create" controller="systemEnvironment" action="create" params="['system.id': systemInstance?.id]">
                                    Add Environment
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
                <td valign="top" class="name"><g:message code="system.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name"><g:message code="system.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <tfoot>
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${systemInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <g:ifNotInUse domain="${systemInstance}">
                                <span class="button">
                                    <g:link class="delete" action="delete" id="${systemInstance?.id}"
                                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                        <g:message code="default.button.delete.label" default="Delete"/>
                                    </g:link>
                                </span>
                            </g:ifNotInUse>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </sec:ifAllGranted>
        </table>
        <g:showHideDetails sectionId="systemDetails" entityName="$entityName"/>
    </div>

    <g:set var="applications" value="${Application.findAllBySystem(systemInstance).sort { it.name } }"/>
    <g:if test="${applications.size()}">
        <h2>Applications</h2>
        <div class="dialog">
            <ul>
                <g:each in="${applications}" var="application">
                    <li><g:link controller="application" action="show" id="${application.id}">${application?.encodeAsHTML()}</g:link></li>
                </g:each>
            </ul>
        </div>
    </g:if>
</div>
</body>
</html>
