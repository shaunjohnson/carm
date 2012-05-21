<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<carm:header domain="${applicationInstance}"
             pageName="${message(code: 'default.create.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationInstance}">
    <div class="alert alert-error">
        <h4><g:message code="application.error.create"/></h4>
        <g:renderErrors bean="${applicationInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <g:hiddenField name="project.id" value="${applicationInstance?.project?.id}"/>

    <carm:formSection legend="${message(code: 'applicationDetails.section')}">
        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'name', 'error')}">
            <carm:label class="control-label" for="name" required="true">
                <g:message code="application.name.label" default="Name"/>
            </carm:label>
            <div class="controls">
                <g:textField name="name" maxlength="50" size="50"
                             value="${applicationInstance?.name}"
                             required="required"
                             title="${message(code: 'application.name.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'type', 'error')}">
            <carm:label class="control-label" for="type.id" required="true">
                <g:message code="application.type.label" default="Type"/>
            </carm:label>
            <div class="controls">
                <g:select name="type.id" from="${applicationTypeList}" optionKey="id"
                          noSelection="['null': '']" value="${applicationInstance?.type?.id}"
                          required="required"
                          title="${message(code: 'application.type.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'description', 'error')}">
            <carm:label class="control-label" for="description">
                <g:message code="application.description.label" default="Description"/>
            </carm:label>
            <div class="controls">
                <g:textArea name="description" value="${applicationInstance?.description}"
                            cols="${grailsApplication.config.ui.textarea.cols}"
                            rows="${grailsApplication.config.ui.textarea.rows}"
                            title="${message(code: 'application.description.help')}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'sourceControl.section')}">
        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'sourceControlRepository', 'error')}">
            <carm:label class="control-label" for="sourceControlRepository.id" required="true">
                <g:message code="application.sourceControlRepository.label"
                           default="Source Control Repository"/>
            </carm:label>
            <div class="controls">
                <g:select name="sourceControlRepository.id" from="${sourceControlRepositoryList}"
                          optionKey="id" value="${applicationInstance?.sourceControlRepository?.id}"
                          noSelection="['null': '']" required="required"
                          title="${message(code: 'application.sourceControlRepository.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'sourceControlPath', 'error')}">
            <carm:label class="control-label" for="sourceControlPath">
                <g:message code="application.sourceControlPath.label" default="Source Control Path"/>
            </carm:label>
            <div class="controls">
                <g:textField name="sourceControlPath" maxlength="200" size="50"
                             value="${applicationInstance?.sourceControlPath}"
                             title="${message(code: 'application.sourceControlPath.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'binariesPath', 'error')}">
            <carm:label class="control-label" for="binariesPath">
                <g:message code="application.binariesPath.label" default="Binaries Path"/>
            </carm:label>
            <div class="controls">
                <g:textField name="binariesPath" maxlength="200" size="50"
                             value="${applicationInstance?.binariesPath}"
                             title="${message(code: 'application.binariesPath.help')}"/>
                <span class="help-inline">
                    <g:message code="application.binariesPath.description"/>
                </span>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'buildInstructions', 'error')}">
            <carm:label class="control-label" for="buildInstructions">
                <g:message code="application.buildInstructions.label" default="Build Instructions"/>
            </carm:label>
            <div class="controls">
                <richui:richTextEditor name="buildInstructions"
                                       value="${applicationInstance?.buildInstructions}"
                                       height="${grailsApplication.config.ui.richTextEditor.height}"
                                       width="${grailsApplication.config.ui.richTextEditor.width}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'deployment.section')}">
        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'sysEnvironment', 'error')}">
            <carm:label class="control-label" for="sysEnvironment.id">
                <g:message code="application.sysEnvironment.label" default="Environment"/>
            </carm:label>
            <div class="controls">
                <g:select name="sysEnvironment.id" from="${systemEnvironmentList}" optionKey="id"
                          value="${applicationInstance?.sysEnvironment?.id}" noSelection="['null': '']"
                          title="${message(code: 'application.sysEnvironment.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationInstance, field: 'deployInstructions', 'error')}">
            <carm:label class="control-label" for="deployInstructions">
                <g:message code="application.deployInstructions.label" default="Deploy Instructions"/>
            </carm:label>
            <div class="controls">
                <richui:richTextEditor name="deployInstructions"
                                       value="${applicationInstance?.deployInstructions}"
                                       height="150"
                                       width="690"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" controller="project" action="show"
                id="${applicationInstance?.project?.id}"><g:message code="default.button.cancel.label"
                                                                    default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
