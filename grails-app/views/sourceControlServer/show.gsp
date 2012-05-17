<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlServerInstance}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<table id="sourceControlServerDetails" class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlServer.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <carm:plainText value="${sourceControlServerInstance?.description}"/>
            </div>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlServer.type.label" default="Type"/>
        </td>
        <td valign="top" class="value">
            ${sourceControlServerInstance?.type?.encodeAsHTML()}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlServer.url.label" default="URL"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: sourceControlServerInstance, field: "url")}
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlServer.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${sourceControlServerInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlServer.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${sourceControlServerInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>

<div class="row">
    <div class="span6">
        <g:render template="sourceControlRepositories"
                  model="[sourceControlServerInstance: sourceControlServerInstance]"/>
    </div>

    <div class="span6">
        <g:render template="sourceControlUsers"
                  model="[sourceControlServerInstance: sourceControlServerInstance]"/>
    </div>
</div>
</body>
</html>
