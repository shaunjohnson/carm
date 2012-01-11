<g:set var="selector" value="${sectionId ? ('#' + sectionId) : ''} .detailProp"/>

<a class="showHideDetails" href="#" onclick="jQuery('${selector}').toggle();">
    <g:message code="default.showHideDetails.label" args="[entityName]"/>
</a>
<script type="text/javascript">
    jQuery(function() { jQuery('${selector}').hide(); });
</script>