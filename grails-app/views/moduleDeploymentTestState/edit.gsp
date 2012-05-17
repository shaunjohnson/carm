<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'moduleDeploymentTestState.label', default: 'Module Deployment Test State')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${moduleDeploymentTestStateInstance}"
             pageName="${message(code: 'default.edit.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${moduleDeploymentTestStateInstance}">
    <div class="alert alert-error">
        <h4><g:message code="moduleDeploymentTestState.error.create"/></h4>
        <g:renderErrors bean="${moduleDeploymentTestStateInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${moduleDeploymentTestStateInstance?.id}"/>
    <g:hiddenField name="version" value="${moduleDeploymentTestStateInstance?.version}"/>


    <div class="control-group ${hasErrors(bean: moduleDeploymentTestStateInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="moduleDeploymentTestState.name.label" default="Name"/>
        </carm:label>
        <div class="controls">
            <g:textField name="name" maxlength="50" size="50"
                         value="${moduleDeploymentTestStateInstance?.name}"
                         required="required"
                         title="${message(code: 'moduleDeploymentTestState.name.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: moduleDeploymentTestStateInstance, field: 'description', 'error')}">
        <carm:label class="control-label" for="description">
            <g:message code="moduleDeploymentTestState.description.label" default="Description"/>
        </carm:label>
        <div class="controls">
            <g:textArea name="description"
                        cols="${grailsApplication.config.ui.textarea.cols}"
                        rows="${grailsApplication.config.ui.textarea.rows}"
                        value="${moduleDeploymentTestStateInstance?.description}"
                        title="${message(code: 'moduleDeploymentTestState.description.help')}"/>
        </div>
    </div>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${moduleDeploymentTestStateInstance.id}"><g:message
                code="default.button.cancel.label" default="Cancel"/></g:link>
    </carm:formButtons>
    </table>
</g:form>
</body>
</html>
