<p id="${clientId}" class="showMoreButton">
    <span id="${clientId}_Text">
        <g:message code="showMore.label" default="Show More"/>
    </span>
    <img id="${clientId}_Spinner" style="display: none;"
         src="${resource(dir: 'images', file: 'spinner.gif')}"
         alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
</p>

<script type="text/javascript">
    jQuery(function () {
        var step = ${step}, offset = 0,
                showMoreButton = jQuery("#${clientId}"),
                showMoreText = jQuery("#${clientId}_Text"),
                showMoreSpinner = jQuery("#${clientId}_Spinner"),
                appendTo = jQuery("#${appendId}");

        showMoreButton.click(function () {
            showMoreText.hide();
            showMoreSpinner.show();

            offset += step;

            if (offset >= ${activityCount}) {
                showMoreButton.hide();
            }

            jQuery.ajax({
                url:'${createLink(controller: "${controller}", action: "${action}", id: id)}',
                data:{ offset:offset, max:step },
                dataType:'html',
                success:function (data, textStatus, jqXHR) {
                    appendTo.append(data);
                    showMoreSpinner.hide();
                    showMoreText.show();
                },
                error:function (jqXHR, textStatus, errorThrown) {

                }
            });
        });
    });
</script>