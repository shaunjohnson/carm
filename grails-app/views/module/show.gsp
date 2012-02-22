<%@ page import="carm.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="moduleDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="module.application.label" default="Application"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="application" action="show" id="${moduleInstance?.application?.id}">
                        ${moduleInstance?.application?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="module.type.label" default="Type"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="moduleType" action="show" id="${moduleInstance?.type?.id}">
                        ${fieldValue(bean: moduleInstance, field: "type")}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="module.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <carm:plainText value="${moduleInstance?.description}"/>
                </td>
            </tr>

            <tr class="prop">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="module.systemComponents.label" default="System Components"/>
                </td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${moduleInstance.systemComponents.sort { it.name }}" var="systemComponent">
                            <li>
                                <g:link controller="systemComponent" action="show" id="${systemComponent?.id}">
                                    ${systemComponent?.encodeAsHTML()}
                                </g:link>
                            </li>
                        </g:each>
                    </ul>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="module.deployInstructions.label" default="Deploy Instructions"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: moduleInstance, field: "deployInstructions").decodeHTML()}
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="module.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${moduleInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="module.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${moduleInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>

            <carm:isProjectOwner module="${moduleInstance}">
                <tfoot class="detailProp">
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${moduleInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <span class="button">
                                <g:link class="delete" action="delete" id="${moduleInstance?.id}">
                                    <g:message code="default.button.delete.label" default="Delete"/>
                                </g:link>
                            </span>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </carm:isProjectOwner>
        </table>
        <carm:showHideDetails sectionId="moduleDetails" entityName="${entityName}"/>
    </div>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                &nbsp;
            </td>
            <td class="layoutColumnLast">
                <g:render template="/common/activity"
                          model="[activityList: activityList, listActivityAction: 'listActivity', domainId: moduleInstance.id]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
