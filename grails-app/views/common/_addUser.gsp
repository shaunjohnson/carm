<div id="add-user-dialog" class="modal" style="display: none;">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">×</button>

        <h3><g:message code="addUser.label" default="Add User"/></h3>
    </div>

    <g:form>
        <div class="modal-body">
            <div class="control-group">
                <carm:label class="control-label" for="addUserId" required="true">
                    <g:message code="user.label" default="User"/>
                </carm:label>
                <div class="controls">
                    <g:select name="addUserId" from="${userList}" optionKey="id" class="span5"
                              noSelection="['null': '']"/>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a href="#" class="btn"
               onclick="jQuery('#add-user-dialog').modal('hide');">
                <g:message code="default.button.cancel.label" default="Cancel"/>
            </a>

            <a href="#" class="btn btn-primary"
               onclick="${callback}.call(this, jQuery('#addUserId').val()); jQuery('#add-user-dialog').modal('hide');">
                <g:message code="default.button.addUser.label" default="Add User"/>
            </a>
        </div>
    </g:form>
</div>

<r:script>
    function displayAddUserDialog() {
        var dialog = jQuery("#add-user-dialog");
        dialog.find('form').each(function() { this.reset(); });
        dialog.modal('show');
        return false;
    }
</r:script>