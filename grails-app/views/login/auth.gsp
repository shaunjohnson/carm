<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<div class="body">
    <carm:header pageName="${message(code: "login.label", default: "Login")}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <form action="${postUrl}" method="POST" id="loginForm" class="cssform" autocomplete="off">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="username"><g:message code="springSecurity.login.username.label"/></label>
                    </td>
                    <td valign="top" class="value">
                        <input type="text" class="text_" name="j_username" id="username"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="password"><g:message code="springSecurity.login.password.label"/></label>
                    </td>
                    <td valign="top" class="value">
                        <input type="password" class="text_" name="j_password" id="password"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        &nbsp;
                    </td>
                    <td valign="top" class="value">
                        <input type="checkbox" class="chk" name="${rememberMeParameter}" id="remember_me"
                               <g:if test="${hasCookie}">checked="checked"</g:if>/>
                        <label for="remember_me"><g:message code="springSecurity.login.remember.me.label"/></label>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button">
                <g:link class="list" uri="/">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <input type="submit" id="submit" value="${message(code: "springSecurity.login.button")}"/>
            </span>
        </div>
    </form>

    <script type="text/javascript">
        <!--
        (function () {
            document.forms["loginForm"].elements["j_username"].focus();
        })();
        // -->
    </script>
</div>
</body>
</html>
