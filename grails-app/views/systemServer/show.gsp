<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'systemServer.label', default: 'SystemEnvironment Server')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemServerInstance}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<table id="systemServerDetails" class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemServer.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <carm:plainText value="${systemServerInstance?.description}"/>
            </div>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemServer.sysEnvironment.label" default="System"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="systemEnvironment" action="show" id="${systemServerInstance?.sysEnvironment?.id}">
                ${systemServerInstance?.sysEnvironment?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemServer.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${systemServerInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemServer.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${systemServerInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>

<g:render template="/common/activity"
          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: systemServerInstance.id]"/>
</body>
</html>
