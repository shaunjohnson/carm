<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<carm:header domain="${applicationReleaseInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationReleaseInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationRelease.error.create"/></h4>
        <g:renderErrors bean="${applicationReleaseInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <carm:formSection legend="${message(code: 'releaseDetails.section')}">
        <div class="control-group ${hasErrors(bean: applicationReleaseInstance, field: 'application', 'error')}">
            <carm:label class="control-label" for="application.id">
                <g:message code="applicationRelease.application.label" default="Application"/>
            </carm:label>
            <div class="controls">
                <g:link controller="application" action="show"
                        id="${applicationReleaseInstance?.application?.id}">
                    ${applicationReleaseInstance?.application?.encodeAsHTML()}
                </g:link>
                <g:hiddenField name="application.id" value="${applicationReleaseInstance?.application?.id}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationReleaseInstance, field: 'releaseNumber', 'error')}">
            <carm:label class="control-label" for="releaseNumber" required="true">
                <g:message code="applicationRelease.releaseNumber.label" default="Release Number"/>
            </carm:label>
            <div class="controls">
                <g:textField name="releaseNumber" maxlength="20" size="20"
                             value="${applicationReleaseInstance?.releaseNumber}"
                             required="required"
                             title="${message(code: 'applicationRelease.releaseNumber.help')}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationReleaseInstance, field: 'changeLog', 'error')}">
            <carm:label class="control-label" for="changeLog">
                <g:message code="applicationRelease.changeLog.label" default="Change Log"/>
            </carm:label>
            <div class="controls">
                <ckeditor:editor name="changeLog"
                                 height="${grailsApplication.config.ui.richTextEditor.height}"
                                 width="${grailsApplication.config.ui.richTextEditor.width}">
                        ${applicationReleaseInstance?.changeLog}
                </ckeditor:editor>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'buildDetails.section')}">
        <div class="control-group ${hasErrors(bean: applicationReleaseInstance, field: 'buildPath', 'error')}">
            <carm:label class="control-label" for="buildPath">
                <g:message code="applicationRelease.buildPath.label" default="Build Path"/>
            </carm:label>
            <div class="controls">
                <g:textField name="buildPath" maxlength="20" size="20"
                             value="${applicationReleaseInstance?.buildPath}"
                             title="${message(code: 'applicationRelease.buildPath.help')}"/>
                <carm:formatSourceControl application="${applicationReleaseInstance.application}"/>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationReleaseInstance, field: 'buildInstructions', 'error')}">
            <carm:label class="control-label" for="buildInstructions">
                <g:message code="applicationRelease.buildInstructions.label" default="Build Instructions"/>
            </carm:label>
            <div class="controls">
                <ckeditor:editor name="buildInstructions"
                                 height="${grailsApplication.config.ui.richTextEditor.height}"
                                 width="${grailsApplication.config.ui.richTextEditor.width}">
                    ${applicationReleaseInstance?.buildInstructions}
                </ckeditor:editor>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" controller="application" action="show"
                id="${applicationReleaseInstance?.application?.id}"><g:message
                code="default.button.cancel.label" default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
