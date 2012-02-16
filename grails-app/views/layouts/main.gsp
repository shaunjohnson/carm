<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'carm.ico')}" type="image/x-icon"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
    <g:javascript library="jquery" plugin="jquery"/>
    <jqui:resources themeCss="/carm/jquery-ui/themes/smoothness/jquery-ui-1.8.15.custom.css"/>

    <script type='text/javascript' src='${resource(dir: 'js', file: 'jquery.tipsy.js')}'></script>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'tipsy.css')}" type="text/css" />

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#delete-dialog").dialog({
                autoOpen:false,
                resizable:false,
                height:140,
                modal:true,
                buttons:{
                    "${message(code: 'default.button.cancel.label', default: 'Cancel')}":function () {
                        jQuery(this).dialog("close");
                    },
                    "${message(code: 'default.button.delete.label', default: 'Delete')}":function () {
                        window.location = jQuery(this).dialog('close').data('link');
                    }
                }
            });

            // Delete confirmation
            jQuery("a.delete").click(function () {
                jQuery('#delete-dialog').data('link', this).dialog('open');
                return false;
            });

            jQuery("button.button").button();
            jQuery(':input:visible').tipsy({trigger: 'focus', gravity: 'w'});
            jQuery(':input:visible:first').focus();
        });
    </script>
</head>

<body>
<g:render template="/common/menu"/>
<g:layoutBody/>
<div class="clearing"></div>

<div class="footer">
    <hr class="divider" style="margin: 0 1em;"/>
    <g:message code="carm.copyright.label" default="Change And Release Management 2011"/>
</div>

<div id="delete-dialog" title="${message(code: 'deleteThisItem.message', default: 'Delete this item?')}">
    <p>
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
        <g:message code="deleteThisItemExplanation.message"
            default="This item will be permanently deleted and cannot be recovered. Are you sure?"/>
    </p>
</div>
</body>
</html>