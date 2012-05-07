<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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
            jQuery("button.button").button();
            jQuery(':input:visible').tipsy({trigger:'focus', gravity:'w'});

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

            // Delete confirmation
            jQuery("a.delete").click(function () {
                jQuery('#delete-dialog').data('link', this).dialog('open');
                return false;
            });

            // Quick search
            jQuery("#quicksearch").autocomplete({
                source:"${createLink(controller: "home", action: "ajaxQuickSearch")}",
                minLength:2,
                select:function (event, ui) {
                    if (ui.item) {
                        window.location = "${createLink(controller: "application", action: "show")}/" + ui.item.id
                    }
                }
            });

            // Favorites
            var userInfo = jQuery("#userInfo"),
                userInfoButton = jQuery("#userInfoButton"),
                addToFavorites = jQuery("#addToFavorites"),
                removeFromFavorites = jQuery("#removeFromFavorites");

            addToFavorites.click(function() {
                jQuery.get('${createLink(controller: "favorite", action: "ajaxAddToFavorites", id: params.id)}');
                addToFavorites.hide();
                removeFromFavorites.show();
            });

            removeFromFavorites.click(function() {
                jQuery.get('${createLink(controller: "favorite", action: "ajaxRemoveFromFavorites", id: params.id)}');
                addToFavorites.show();
                removeFromFavorites.hide();
            });

            jQuery('.body :input:visible:first').focus();

            function closeUserInfo() {
                userInfo.hide();
                jQuery("body").off("click", closeUserInfo);
            }

            userInfoButton.click(function(event) {
                if (userInfo.is(":visible")) {
                    closeUserInfo();
                }
                else {
                    var pos = userInfoButton.position();

                    userInfo.css({
                        position: "absolute",
                        top: (pos.top + userInfoButton.outerHeight()) + "px",
                        left: (pos.left - userInfo.outerWidth() + userInfoButton.outerWidth()) + "px"
                    }).show();

                    jQuery("body").on("click", closeUserInfo);
                    event.stopPropagation(); // To prevent triggering the closeUserInfo handler
                }
            });

            jQuery("#userInfo .ui-menu-item a")
                    .mouseover(function() { jQuery(this).addClass("ui-state-hover") })
                    .mouseout(function() { jQuery(this).removeClass("ui-state-hover") });
        });
    </script>
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