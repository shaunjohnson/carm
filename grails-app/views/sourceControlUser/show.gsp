<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'Source Control User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlUserInstance}"/>

<g:render template="/common/messages"/>

<table id="sourceControlUserDetails" class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlUser.name.label" default="Name"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: sourceControlUserInstance, field: "name")}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlUser.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <carm:plainText value="${sourceControlUserInstance?.description}"/>
            </div>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlUser.server.label" default="Source Control Server"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="sourceControlServer" action="show"
                    id="${sourceControlUserInstance?.server?.id}">
                ${sourceControlUserInstance?.server?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlUser.user.label" default="User"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="user" action="show" id="${sourceControlUserInstance?.user?.id}">
                ${sourceControlUserInstance?.user?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlUser.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${sourceControlUserInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlUser.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${sourceControlUserInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
