<button id="${clientId}" class="btn ${mini ? "btn-mini" : " btn-block"}" type="button">
    <span id="${clientId}_Text" class="muted">
        <g:if test="${mini}">
            <g:message code="showMoreMini.label" default="More"/>
        </g:if>
        <g:else>
            <g:message code="showMore.label" default="Show More"/>
        </g:else>
    </span>
    <img id="${clientId}_Spinner" style="display: none;"
         src="${resource(dir: 'images', file: 'spinner.gif')}"
         alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
</button>

<script type="text/javascript">
    jQuery(function () {
        var step = ${step}, offset = ${offset} - ${step},
                showMoreButton = jQuery("#${clientId}"),
                showMoreText = jQuery("#${clientId}_Text"),
                showMoreSpinner = jQuery("#${clientId}_Spinner"),
                appendTo = jQuery("#${appendId}");

        showMoreButton.click(function () {
            showMoreText.hide();
            showMoreSpinner.show();

            offset += step;

            jQuery.ajax({
                url:'${createLink(controller: "${controller}", action: "${action}", id: id)}',
                data:{ offset:offset, max:step },
                dataType:'html',
                success:function (data, textStatus, jqXHR) {
                    appendTo.append(data);
                    showMoreSpinner.hide();
                    showMoreText.show();

                    if (offset + step >= ${max}) {
                        showMoreButton.hide();
                    }
                },
                error:function (jqXHR, textStatus, errorThrown) {

                }
            });
        });
    });
</script>