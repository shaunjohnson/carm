<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlRoleInstance}"/>

<g:render template="/common/messages"/>

<table id="sourceControlRoleDetails" class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlRole.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <carm:plainText value="${sourceControlRoleInstance?.description}"/>
            </div>
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlRole.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value"><g:formatDate date="${sourceControlRoleInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="sourceControlRole.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${sourceControlRoleInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
