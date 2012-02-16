<%@ page import="carm.Module" %>
<div class="sectionHeader">
    <div class="text">
        <g:message code="modules.label" default="Modules"/>
    </div>
</div>

<g:if test="${applicationDeploymentInstance?.moduleDeployments?.size()}">
    <ul>
        <g:each in="${applicationDeploymentInstance.moduleDeployments.sort { it.moduleRelease.module }}"
                var="moduleDeploymentInstance" status="i">
            <li>
                <g:link controller="moduleDeployment" action="show" id="${moduleDeploymentInstance.id}">
                    ${moduleDeploymentInstance.moduleRelease.module.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>