<%@ page import="carm.Module" %>
<h2 class="sectionHeader">
    <g:message code="modules.label" default="Modules"/>
</h2>

<g:if test="${applicationDeploymentInstance?.moduleDeployments?.size()}">
    <ul>
        <g:each in="${applicationDeploymentInstance.moduleDeployments.sort { it.moduleRelease.module }}"
                var="moduleDeploymentInstance" status="i">
            <li>
                <g:link controller="module" action="show" id="${moduleDeploymentInstance.moduleRelease.module.id}">
                    ${moduleDeploymentInstance.moduleRelease.module.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>