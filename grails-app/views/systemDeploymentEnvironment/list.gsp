<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemDeploymentEnvironmentInstanceList}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'systemDeploymentEnvironment.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description"
                                  title="${message(code: 'systemDeploymentEnvironment.description.label', default: 'Description')}"/>
                <th><g:message code="systemDeploymentEnvironment.system.label" default="System"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemDeploymentEnvironmentInstanceList}" status="i"
                    var="systemDeploymentEnvironmentInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${systemDeploymentEnvironmentInstance.id}">
                            ${fieldValue(bean: systemDeploymentEnvironmentInstance, field: "name")}
                        </g:link>
                    </td>
                    <td>
                        <div class="expander">
                            <carm:plainText value="${systemDeploymentEnvironmentInstance.description}"/>
                        </div>
                    </td>
                    <td>
                        <g:link controller="systemEnvironment" action="show"
                                id="${systemDeploymentEnvironmentInstance.sysEnvironment.id}">
                            ${fieldValue(bean: systemDeploymentEnvironmentInstance, field: "sysEnvironment")}
                        </g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${systemDeploymentEnvironmentInstanceTotal}"
                    max="${grailsApplication.config.ui.systemDeploymentEnvironment.listMax}"/>
    </div>
</div>
</body>
</html>
