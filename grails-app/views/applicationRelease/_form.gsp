<carm:formSection legend="${message(code: 'releaseDetails.section')}">
    <div class="control-group ${hasErrors(bean: applicationReleaseInstance, field: 'application', 'error')}">
        <carm:label class="control-label" for="application.id">
            <g:message code="applicationRelease.application.label" default="Application"/>
        </carm:label>
        <div class="controls">
            <g:link controller="application" action="show" id="${applicationReleaseInstance?.application?.id}">
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
            <g:textField name="releaseNumber" maxlength="20" class="span2"
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
            <g:textField name="buildPath" maxlength="20" class="span2"
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