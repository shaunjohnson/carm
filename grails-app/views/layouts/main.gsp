<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="pragma" content="NO-CACHE">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'carm.ico')}" type="image/x-icon"/>
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:layoutHead/>

    <g:javascript library="application"/>
    <g:javascript library="jquery" plugin="jquery"/>
    <jqui:resources themeCss="/carm/jquery-ui/themes/smoothness/jquery-ui-1.8.15.custom.css"/>

    <script type='text/javascript' src='${resource(dir: 'js', file: 'jquery.cookie.js')}'></script>
    <script type='text/javascript' src='${resource(dir: 'js', file: 'jquery.ba-hashchange.min.js')}'></script>
    <script type='text/javascript' src='${resource(dir: 'js', file: 'jquery.easytabs.min.js')}'></script>
    <script type='text/javascript' src='${resource(dir: 'js', file: 'jquery.expander.min.js')}'></script>
    <script type='text/javascript' src='${resource(dir: 'js', file: 'jquery.tipsy.js')}'></script>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'tipsy.css')}" type="text/css"/>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery('.expander').expander({
                expandText:'<g:message code="showMore.label"/>',
                userCollapseText:'<g:message code="showLess.label"/>'
            });

            jQuery("button.button, .buttons input").button();
            jQuery(':input:visible').tipsy({trigger:'focus', gravity:'w'});
            jQuery('.body :input:visible:first').focus();

            /* Delete confirmation */
            jQuery("#delete-dialog").dialog({
                autoOpen:false,
                resizable:false,
                height:160,
                width:360,
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

            jQuery("a.delete").click(function () {
                jQuery('#delete-dialog').data('link', this).dialog('open');
                return false;
            });

            /* Quick search */
            jQuery("#quicksearch").autocomplete({
                source:"${createLink(controller: "home", action: "ajaxQuickSearch")}",
                minLength:2,
                select:function (event, ui) {
                    if (ui.item) {
                        if (ui.item.type == "application") {
                            window.location = "${createLink(controller: "application", action: "show")}/" + ui.item.id
                        }
                        else if (ui.item.type == "module") {
                            window.location = "${createLink(controller: "module", action: "show")}/" + ui.item.id
                        }
                        else if (ui.item.type == "project") {
                            window.location = "${createLink(controller: "project", action: "show")}/" + ui.item.id
                        }
                    }
                }
            }).data("autocomplete")._renderItem = function (ul, item) {
                return jQuery('<li class="ui-menu-item-with-icon"></li>')
                        .data("item.autocomplete", item)
                        .append('<a><span class="' + item.type + '-item-icon"></span>' + item.label + '</a>')
                        .appendTo(ul);
            };

            /* Preload imates */
            new Image().src = "${resource(dir: 'images', file: 'unstarred.png')}";
            new Image().src = "${resource(dir: 'images', file: 'starred.png')}";
            new Image().src = "${fam.icon(name: 'email')}";
            new Image().src = "${fam.icon(name: 'email_go')}";
            new Image().src = "${fam.icon(name: 'email_add')}";
            new Image().src = "${fam.icon(name: 'email_delete')}";

            /* Favorites */
            var userInfo = jQuery("#userInfo"),
                userInfoButton = jQuery("#userInfoButton"),
                addApplicationToFavorites = jQuery("#addApplicationToFavorites"),
                addProjectToFavorites = jQuery("#addProjectToFavorites"),
                removeApplicationFromFavorites = jQuery("#removeApplicationFromFavorites"),
                removeProjectFromFavorites = jQuery("#removeProjectFromFavorites");

            addApplicationToFavorites.click(function () {
                jQuery.ajax({ cache: false, url: '${createLink(controller: "favorite", action: "ajaxAddApplicationToFavorites", id: params.id)}' });
                addApplicationToFavorites.hide(); removeApplicationFromFavorites.show();
            });

            addProjectToFavorites.click(function () {
                jQuery.ajax({ cache: false, url: '${createLink(controller: "favorite", action: "ajaxAddProjectToFavorites", id: params.id)}' });
                addProjectToFavorites.hide(); removeProjectFromFavorites.show();
            });

            removeApplicationFromFavorites.click(function () {
                jQuery.ajax({ cache: false, url: '${createLink(controller: "favorite", action: "ajaxRemoveApplicationFromFavorites", id: params.id)}' });
                addApplicationToFavorites.show(); removeApplicationFromFavorites.hide();
            });

            removeProjectFromFavorites.click(function () {
                jQuery.ajax({ cache: false, url: '${createLink(controller: "favorite", action: "ajaxRemoveProjectFromFavorites", id: params.id)}' });
                addProjectToFavorites.show(); removeProjectFromFavorites.hide();
            });

            /* Watches */
            var addApplicationToWatches = jQuery("#addApplicationToWatches"),
                addProjectToWatches = jQuery("#addProjectToWatches"),
                removeApplicationFromWatches = jQuery("#removeApplicationFromWatches"),
                removeProjectFromWatches = jQuery("#removeProjectFromWatches");

            addApplicationToWatches
                .click(function () {
                    jQuery.ajax({ cache: false, url: '${createLink(controller: "watch", action: "ajaxAddApplicationToWatches", id: params.id)}' });
                    addApplicationToWatches.hide(); removeApplicationFromWatches.show();
                })
                .hover( function() { jQuery(this).toggleClass("add-to-watches add-to-watches-hover"); });

            addProjectToWatches
                .click(function () {
                    jQuery.ajax({ cache: false, url: '${createLink(controller: "watch", action: "ajaxAddProjectToWatches", id: params.id)}' });
                    addProjectToWatches.hide(); removeProjectFromWatches.show();
                })
                .hover( function() { jQuery(this).toggleClass("add-to-watches add-to-watches-hover"); });

            removeApplicationFromWatches
                .click(function () {
                    jQuery.ajax({ cache: false, url: '${createLink(controller: "watch", action: "ajaxRemoveApplicationFromWatches", id: params.id)}' });
                    addApplicationToWatches.show(); removeApplicationFromWatches.hide();
                })
                .hover( function() { jQuery(this).toggleClass("remove-from-watches remove-from-watches-hover"); });

            removeProjectFromWatches
                .click(function () {
                    jQuery.ajax({ cache: false, url: '${createLink(controller: "watch", action: "ajaxRemoveProjectFromWatches", id: params.id)}' });
                    addProjectToWatches.show(); removeProjectFromWatches.hide();
                })
                .hover( function() { jQuery(this).toggleClass("remove-from-watches remove-from-watches-hover"); });

            /* User Information */
            function closeUserInfo() { userInfo.hide(); jQuery("body").off("click", closeUserInfo); }

            userInfoButton.click(function (event) {
                if (userInfo.is(":visible")) {
                    closeUserInfo();
                }
                else {
                    var pos = userInfoButton.position();

                    userInfo.css({
                        position:"absolute",
                        top:(pos.top + userInfoButton.outerHeight()) + "px",
                        left:(pos.left - userInfo.outerWidth() + userInfoButton.outerWidth()) + "px"
                    }).show();

                    jQuery("body").on("click", closeUserInfo);
                    event.stopPropagation(); // To prevent triggering the closeUserInfo handler
                }
            });

            jQuery("#userInfo .ui-menu-item-with-icon a").hover(function () { jQuery(this).toggleClass("ui-state-hover"); });
        });
    </script>

    <style>
        .page-header-action {
            cursor: pointer;
            float: right;
            margin-left: 1.5em;
            padding-left: 1.5em;
        }

        .page-header-action, .page-header-action * {
            font-size: 1em;
            font-weight: normal;
        }

        /* Favorites and Watches */
        .add-to-favorites {          background: url("${resource(dir: 'images', file: 'unstarred.png')}") no-repeat left; }
        .remove-from-favorites {     background: url("${resource(dir: 'images', file: 'starred.png')}") no-repeat left; }
        .add-to-watches {            background: url("${fam.icon(name: 'email')}") no-repeat left; }
        .add-to-watches-hover {      background: url("${fam.icon(name: 'email_add')}") no-repeat left; }
        .remove-from-watches {       background: url("${fam.icon(name: 'email_go')}") no-repeat left; }
        .remove-from-watches-hover { background: url("${fam.icon(name: 'email_delete')}") no-repeat left; }

        .application-favorite, .application-watch {
            background: url("${fam.icon(name: 'application')}") no-repeat left;
            padding-left: 1.5em
        }

        .project-favorite, .project-watch {
            background: url("${fam.icon(name: 'building')}") no-repeat left;
            padding-left: 1.5em
        }

        /* Actions */
        .delete-application-action {           background: url("${fam.icon(name: 'application_delete')}") no-repeat left; }
        .delete-module-action {                background: url("${fam.icon(name: 'brick_delete')}") no-repeat left; }
        .delete-project-action {               background: url("${fam.icon(name: 'building_delete')}") no-repeat left; }
        .delete-source-control-server-action { background: url("${fam.icon(name: 'server_delete')}") no-repeat left; }
        .edit-application-action {             background: url("${fam.icon(name: 'application_edit')}") no-repeat left; }
        .edit-module-action {                  background: url("${fam.icon(name: 'brick_edit')}") no-repeat left; }
        .edit-project-action {                 background: url("${fam.icon(name: 'building_edit')}") no-repeat left; }
        .edit-source-control-server-action {   background: url("${fam.icon(name: 'server_edit')}") no-repeat left; }
        .new-project-action {                  background: url("${fam.icon(name: 'building_add')}") no-repeat left; }
        .new-source-control-server-action {    background: url("${fam.icon(name: 'server_add')}") no-repeat left; }

        /*
         * Autocomplete
         */
        .ui-menu .ui-menu-item-with-icon a { padding-left: 20px; }

        span.application-item-icon, span.module-item-icon, span.project-item-icon {
            display: inline-block;
            height: 20px;
            width: 20px;
            margin-left: -16px;
        }

        span.application-item-icon { background: url("${fam.icon(name: 'application')}") no-repeat left 6px; }
        span.module-item-icon {      background: url("${fam.icon(name: 'brick')}") no-repeat left 6px; }
        span.project-item-icon {     background: url("${fam.icon(name: 'building')}") no-repeat left 6px; }
    </style>
</head>

<body>
<g:render template="/common/menu"/>
<g:layoutBody/>
<div class="clearing"></div>

<div class="footer">
    <hr class="divider" style="margin: 0 1em;"/>
    <g:message code="carm.copyright.label" default="Change And Release Management 2011"
               args="[meta(name: 'app.version')]"/>
</div>

<div id="delete-dialog" title="${message(code: 'deleteThisItem.message', default: 'Delete this item?')}"
     style="display: none;">
    <p>
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
        <g:message code="deleteThisItemExplanation.message"
                   default="This item will be permanently deleted and cannot be recovered. Are you sure?"/>
    </p>
</div>

<carm:userInfoDropdown/>

</body>
</html>