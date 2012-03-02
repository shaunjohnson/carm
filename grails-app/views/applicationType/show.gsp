<%@ page import="carm.application.ApplicationType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationTypeInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="applicationTypeDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationType.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <carm:plainText value="${applicationTypeInstance?.description}"/>
                    </div>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="applicationType.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationTypeInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="applicationType.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationTypeInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="applicationTypeDetails" entityName="${entityName}">
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${applicationTypeInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <carm:ifNotInUse domain="${applicationTypeInstance}">
                        <span class="button">
                            <g:link class="delete" action="delete" id="${applicationTypeInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </carm:ifNotInUse>
                </div>
            </sec:ifAllGranted>
        </carm:showHideDetails>
    </div>
</div>
</body>
</html>
