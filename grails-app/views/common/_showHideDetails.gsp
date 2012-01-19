<g:set var="selector" value="${sectionId ? ('#' + sectionId) : ''} .detailProp"/>

<a class="showHideDetails" href="#" onclick="jQuery('${selector}').toggle();">
    <g:message code="default.showHideDetails.label" default="Show/Hide Details"
               args="[entityName]"/>
</a>