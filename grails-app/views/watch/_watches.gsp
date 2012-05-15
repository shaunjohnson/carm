<div id="add${type}ToWatches" class="header-action add-to-watches"
     style="${isWatched ? 'display: none;' : 'display: block;'}"
     title="${message(code: "addToWatches.message")}">
    <a href="#"><g:message code="watch.label"/></a>
</div>

<div id="remove${type}FromWatches" class="header-action remove-from-watches"
     style="${isWatched ? 'display: block;' : 'display: none;'}"
     title="${message(code: "removeFromWatches.message")}">
    <a href="#"><g:message code="watching.label"/></a>
</div>