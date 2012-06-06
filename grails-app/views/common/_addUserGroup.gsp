<div id="add-user-group-dialog" class="modal" style="display: none;">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">×</button>

        <h3><g:message code="addGroup.label" default="Add Group"/></h3>
    </div>

    <g:form>
        <div class="modal-body">
            <div class="control-group">
                <carm:label class="control-label" for="addUserGroupId" required="true">
                    <g:message code="group.label" default="Group"/>
                </carm:label>
                <div class="controls">
                    <g:select name="addUserGroupId" from="${userGroupList}" optionKey="id" class="span5"
                              noSelection="['null': '']"/>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a href="#" class="btn"
               onclick="jQuery('#add-user-group-dialog').modal('hide');">
                <g:message code="default.button.cancel.label" default="Cancel"/>
            </a>

            <a href="#" class="btn btn-primary" onclick="return addUserGroupSubmit();">
                <g:message code="default.button.addGroup.label" default="Add Group"/>
            </a>
        </div>
    </g:form>
</div>

<r:script>
    function displayAddUserGroupDialog(callback) {
        var dialog = jQuery("#add-user-group-dialog");
        dialog.data('callback', callback);
        dialog.find('form').each(function () {
            this.reset();
        });
        dialog.modal('show');
        return false;
    }

    function addUserGroupSubmit() {
        var dialog = jQuery("#add-user-group-dialog");
        dialog.data('callback').call(this, jQuery('#addUserGroupId').val());
        dialog.modal('hide');
        return false;
    }
</r:script>