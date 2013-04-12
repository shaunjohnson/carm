<carm:formSection legend="${message(code: 'projectDetails.section')}">
    <div class="control-group ${hasErrors(bean: projectInstance, field: 'name', 'error')}">
        <carm:label class="control-label" for="name" required="true">
            <g:message code="project.name.label" default="Name"/>
        </carm:label>
        <div class="controls">
            <g:textField name="name" maxlength="50" class="span4"
                         value="${projectInstance?.name}"
                         required="required"
                         title="${message(code: 'project.name.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: projectInstance, field: 'category', 'error')}">
        <carm:label class="control-label" for="category.id" required="true">
            <g:message code="project.category.label" default="Category"/>
        </carm:label>
        <div class="controls">
            <g:select name="category.id" from="${projectCategoryList}" class="span4"
                      optionKey="id" value="${projectInstance?.category?.id}"
                      required="required"
                      title="${message(code: 'project.category.help')}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: projectInstance, field: 'description', 'error')}">
        <carm:label class="control-label" for="description">
            <g:message code="project.description.label" default="Description"/>
        </carm:label>
        <div class="controls">
            <g:textArea name="description" class="span6"
                        cols="${grailsApplication.config.ui.textarea.cols}"
                        rows="${grailsApplication.config.ui.textarea.rows}"
                        value="${projectInstance?.description}"
                        title="${message(code: 'project.description.help')}"/>
        </div>
    </div>
</carm:formSection>