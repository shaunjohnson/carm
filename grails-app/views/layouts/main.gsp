<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
        <g:javascript library="jquery" plugin="jquery" />
        <jqui:resources  themeCss="/carm/jquery-ui/themes/south-street/jquery-ui-1.8.15.custom.css"/>
        <script type="text/javascript" src="${resource(dir:'js',file:'jquery.multi-ddm.min.js')}" ></script>
        <script type='text/javascript'>
            jQuery(document).ready(function(){
                jQuery('#multi-ddm').dropDownMenu({timer: 500, parentMO: 'parent-hover', childMO: 'child-hover1'});

                jQuery("#delete-dialog").dialog({
                    autoOpen: false,
                    resizable: false,
                    height: 140,
                    modal: true,
                    buttons: {
                        Cancel: function() {
                            jQuery(this).dialog("close");
                        },
                        "Delete": function() {
                            window.location = jQuery(this).dialog('close').data('link');
                        }
                    }
                });

                // Delete confirmation
                jQuery("a.delete").click(function() {
                    jQuery('#delete-dialog').data('link', this).dialog('open');
                    return false;
                });
            });
        </script>
    </head>
    <body>
        <g:render template="/common/menu" />
        <g:layoutBody />
        <div class="clearing"></div>
        <div class="footer">
            <hr style="margin: 0 1em;" />
            Change And Release Management 2011
        </div>

        <div id="delete-dialog" title="Delete this item?">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>This item will be permanently deleted and cannot be recovered. Are you sure?</p>
        </div>
    </body>
</html>