<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<carm:header domain="${moduleInstance}" pageName="${message(code: 'default.create.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>
<g:hasErrors bean="${moduleInstance}">
    <div class="alert alert-error">
        <h4><g:message code="module.error.create"/></h4>
        <g:renderErrors bean="${moduleInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <carm:formSection legend="${message(code: 'moduleDetails.section')}">
        <div class="control-group ${hasErrors(bean: moduleInstance, field: 'name', 'errors')}">
            <carm:label class="control-label" for="name" required="true">
                <g:message code="module.name.label" default="Name"/>
            </carm:label>
            <div class="controls">
                <g:textField name="name" maxlength="50" size="50"
                             value="${moduleInstance?.name}"
                             required="required"
                             title="${message(code: 'module.name.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: moduleInstance, field: 'type', 'errors')}">
            <carm:label class="control-label" for="type.id" required="true">
                <g:message code="module.type.label" default="Type"/>
            </carm:label>
            <div class="controls">
                <g:select name="type.id" from="${moduleTypeList}" optionKey="id"
                          noSelection="['null': '']" value="${moduleInstance?.type?.id}"
                          required="required"
                          title="${message(code: 'module.type.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: moduleInstance, field: 'description', 'errors')}">
            <carm:label class="control-label" for="description">
                <g:message code="module.description.label" default="Description"/>
            </carm:label>
            <div class="controls">
                <g:textArea name="description"
                            cols="${grailsApplication.config.ui.textarea.cols}"
                            rows="${grailsApplication.config.ui.textarea.rows}"
                            value="${moduleInstance?.description}"
                            title="${message(code: 'module.description.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'deployment.section')}">
        <div class="control-group ${hasErrors(bean: moduleInstance, field: 'systemServers', 'errors')}">
            <carm:label class="control-label" for="systemServers">
                <g:message code="module.systemServers.label" default="System Servers"/>
            </carm:label>
            <div class="controls">
                <g:if test="${moduleInstance.application.sysEnvironment}">
                    <g:select class="span6" name="systemServers" multiple="yes" optionKey="id"
                              size="${moduleInstance.application.sysEnvironment.servers.size()}"
                              from="${moduleInstance.application.sysEnvironment.servers.sort { it.name }}"
                              value="${moduleInstance?.systemServers*.id}"
                              title="${message(code: 'module.systemServers.help')}"/>
                </g:if>
                <g:else>
                    <em><g:message code="moduleCannotBeDeployed.message"
                                   default="This module cannot be deployed to any server since the application is not associated with an environment."/></em>
                    <g:hiddenField name="systemServers" value="${null}"/>
                </g:else>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: moduleInstance, field: 'deployInstructions', 'errors')}">
            <carm:label class="control-label" for="deployInstructions">
                <g:message code="module.deployInstructions.label" default="Deploy Instructions"/>
            </carm:label>
            <div class="controls">
                <richui:richTextEditor name="deployInstructions" value="${moduleInstance?.deployInstructions}"
                                       height="${grailsApplication.config.ui.richTextEditor.height}"
                                       width="${grailsApplication.config.ui.richTextEditor.width}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" controller="application" action="show" id="${moduleInstance.application.id}">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
