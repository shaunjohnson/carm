<div id="login">
    <form action="${loginPostUrl}" method="POST" id="loginForm" autocomplete="off">
        <p class="emphasis">
            <g:message code="notLoggedIn.message" default="You are currently not logged in."/>
        </p>

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
                    <div style="float: right;" class="buttons">
                        <input type="submit" id="submit" value="${message(code: "springSecurity.login.button")}"/>
                    </div>
                    <div style="float: left;">
                        <input type="checkbox" class="chk" name="${rememberMeParameter}" id="remember_me"
                               <g:if test="${hasCookie}">checked="checked"</g:if>/>
                        <label for="remember_me"><g:message code="springSecurity.login.remember.me.label"/></label>
                    </div>
                    <div class="clearing"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>