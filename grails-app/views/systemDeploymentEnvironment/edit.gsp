<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemDeploymentEnvironmentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${systemDeploymentEnvironmentInstance}">
        <div class="errors">
            <g:renderErrors bean="${systemDeploymentEnvironmentInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${systemDeploymentEnvironmentInstance?.id}"/>
        <g:hiddenField name="version" value="${systemDeploymentEnvironmentInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="system.id">
                            <g:message code="systemServer.sysEnvironment.label" default="System"/>
                        </carm:label>
                    </td>
                    <td valign="top" class="value">
                        <g:link controller="systemEnvironment" action="show" id="${systemDeploymentEnvironmentInstance?.sysEnvironment?.id}">
                            ${systemDeploymentEnvironmentInstance?.sysEnvironment?.encodeAsHTML()}
                        </g:link>
                        <g:hiddenField name="sysEnvironment.id" value="${systemDeploymentEnvironmentInstance?.sysEnvironment?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="name" required="true">
                            <g:message code="systemDeploymentEnvironment.name.label" default="Name"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: systemDeploymentEnvironmentInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" size="50"
                                     value="${systemDeploymentEnvironmentInstance?.name}"
                                     required="required"
                                     title="${message(code: 'systemDeploymentEnvironment.name.help')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="description">
                            <g:message code="systemDeploymentEnvironment.description.label" default="Description"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: systemDeploymentEnvironmentInstance, field: 'description', 'errors')}">
                        <g:textArea name="description"
                                    cols="${grailsApplication.config.ui.textarea.cols}"
                                    rows="${grailsApplication.config.ui.textarea.rows}"
                                    value="${systemDeploymentEnvironmentInstance?.description}"
                                    title="${message(code: 'systemDeploymentEnvironment.description.help')}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <carm:requiredLabelMessage/>

        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${systemDeploymentEnvironmentInstance.id}">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="save" class="save"
                                value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
