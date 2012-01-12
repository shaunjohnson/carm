<%@ page import="carm.ModuleType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'ModuleType')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="body">
    <g:header domain="${moduleTypeInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="moduleType.description.label" default="Description" /></td>
                <td valign="top" class="value">${fieldValue(bean: moduleTypeInstance, field: "description")}</td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="moduleType.dateCreated.label" default="Date Created" /></td>
                <td valign="top" class="value"><g:formatDate date="${moduleTypeInstance?.dateCreated}" /></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="moduleType.lastUpdated.label" default="Last Updated" /></td>
                <td valign="top" class="value"><g:formatDate date="${moduleTypeInstance?.lastUpdated}" /></td>
            </tr>
            </tbody>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <tfoot>
                <tr>
                <td colspan="2">
                    <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${moduleTypeInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <g:ifNotInUse domain="${moduleTypeInstance}">
                                <span class="button">
                                    <g:link class="delete" action="delete" id="${moduleTypeInstance?.id}"
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
