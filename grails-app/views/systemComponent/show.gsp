<%@ page import="carm.SystemComponent" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'SystemComponent')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${systemComponentInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemComponentInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.system.label" default="System"/></td>
                <td valign="top" class="value">
                    <g:link controller="system" action="show" id="${systemComponentInstance?.system?.id}">
                        ${systemComponentInstance?.system?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>

            <tr class="prop">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemComponentInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemComponentInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <tfoot>
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${systemComponentInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <g:ifNotInUse domain="${systemComponentInstance}">
                                <span class="button">
                                    <g:link class="delete" action="delete" id="${systemComponentInstance?.id}"
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
    </div>
</div>
</body>
</html>
