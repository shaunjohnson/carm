<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemEnvironmentInstanceList}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<g:if test="${systemEnvironmentInstanceTotal}">
    <g:each in="${systemEnvironmentInstanceList}" var="systemEnvironment" status="systemIndex">
        <div style="margin: 0.5em 0;">
            <carm:formatSystemEnvironment systemEnvironment="${systemEnvironment}"/>
        </div>

        <g:if test="${(systemIndex + 1) < systemEnvironmentInstanceList.size()}">
            <hr/>
        </g:if>
    </g:each>

    <g:paginate total="${systemEnvironmentInstanceTotal}"
                max="${grailsApplication.config.ui.systemEnvironment.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoEnvironments.message" default="There are no environments."/></em>
    </p>
</g:else>
</body>
</html>
