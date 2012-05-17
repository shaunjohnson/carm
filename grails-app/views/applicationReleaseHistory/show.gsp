<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseHistory.label', default: 'Application Release History')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationReleaseHistoryInstance}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<table id="applicationReleaseHistoryDetails" class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationReleaseHistory.applicationRelease.label" default="Application Release"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="application" action="show"
                    id="${applicationReleaseHistoryInstance.applicationRelease.application.id}"
                    title="${message(code: 'showApplication.label', default: 'Show Application')}">
                ${applicationReleaseHistoryInstance.applicationRelease.application.encodeAsHTML()}
            </g:link>
            (<g:link controller="applicationRelease" action="show"
                     id="${applicationReleaseHistoryInstance.applicationRelease.id}"
                     title="${message(code: 'showApplicationRelease.label', default: 'Show Application Release')}">${applicationReleaseHistoryInstance.applicationRelease.releaseNumber.encodeAsHTML()}</g:link>)
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationReleaseHistory.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${applicationReleaseHistoryInstance.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationReleaseHistory.username.label" default="Username"/>
        </td>
        <td valign="top" class="value">
            ${applicationReleaseHistoryInstance.username.encodeAsHTML()}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationReleaseHistory.summary.label" default="Summary"/>
        </td>
        <td valign="top" class="value">
            ${applicationReleaseHistoryInstance.summary.encodeAsHTML()}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationReleaseHistory.comments.label" default="Comments"/>
        </td>
        <td valign="top" class="value">
            ${(applicationReleaseHistoryInstance.comments ?: "").decodeHTML()}
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
