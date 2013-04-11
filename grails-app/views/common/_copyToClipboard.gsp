<img id="copyToClipboard_${targetId}" class="clickable" data-clipboard-target="${targetId}"
     src='${fam.icon(name: 'page_white_copy')}' alt="${message(code: 'copyToClipboard')}"
     title="${message(code: 'copyToClipboard')}"/>

<r:script>
    jQuery(function () {
        var copyTrigger = jQuery("#copyToClipboard_${targetId}"),
            clip = new ZeroClipboard(copyTrigger, { moviePath: "${request.contextPath}/js/ZeroClipboard.swf" });
        clip.on( 'complete', function (client, args) {
            copyTrigger
                    .attr('title', "${message(code: 'copiedToClipboard')}")
                    .tooltip({placement: 'right', trigger: 'click'})
                    .tooltip('show');
            setTimeout(function() { copyTrigger.tooltip('hide'); }, 800);
        });
    });
</r:script>