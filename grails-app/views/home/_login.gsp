<form action="${loginPostUrl}" method="POST" id="loginForm" class="well" xmlns="http://www.w3.org/1999/html">
    <p>
        <em><g:message code="notLoggedIn.message" default="You are currently not logged in."/></em>
    </p>

    <p>
        <label for="username"><g:message code="springSecurity.login.username.label"/></label>
        <input type="text" class="span4" name="j_username" id="username"/>
    </p>

    <p>
        <label for="password"><g:message code="springSecurity.login.password.label"/></label>
        <input type="password" class="span4" name="j_password" id="password"/>
    </p>

    <p>
        <label for="remember_me" class="checkbox">
            <input type="checkbox" name="${rememberMeParameter}" id="remember_me"
                   <g:if test="${hasCookie}">checked="checked"</g:if>/>
            <g:message code="springSecurity.login.remember.me.label"/>
        </label>
    </p>

    <p>
        <input class="btn btn-primary" type="submit" id="submit"
               value="${message(code: "springSecurity.login.button")}"/>
    </p>
</form>