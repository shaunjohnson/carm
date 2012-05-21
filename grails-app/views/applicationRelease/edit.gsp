<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<carm:header domain="${applicationReleaseInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationReleaseInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationRelease.error.update"/></h4>
        <g:renderErrors bean="${applicationReleaseInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="update" method="post" class="offset1 span8">
    <g:hiddenField name="id" value="${applicationReleaseInstance?.id}"/>
    <g:hiddenField name="version" value="${applicationReleaseInstance?.version}"/>

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
                <richui:richTextEditor name="changeLog" value="${applicationReleaseInstance?.changeLog}"
                                       height="${grailsApplication.config.ui.richTextEditor.height}"
                                       width="${grailsApplication.config.ui.richTextEditor.width}"/>
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
                <richui:richTextEditor name="buildInstructions"
                                       value="${applicationReleaseInstance?.buildInstructions}"
                                       height="${grailsApplication.config.ui.richTextEditor.height}"
                                       width="${grailsApplication.config.ui.richTextEditor.width}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="save"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:link class="btn" action="show" id="${applicationReleaseInstance.id}"><g:message
                code="default.button.cancel.label" default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>
</body>
</html>
