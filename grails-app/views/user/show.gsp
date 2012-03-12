<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${userInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="user.username.label" default="Username"/>
                </td>
                <td valign="top" class="value">
                    ${userInstance?.username?.encodeAsHTML()}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
