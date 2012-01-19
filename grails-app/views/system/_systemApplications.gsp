<h2 class="sectionHeader">Applications</h2>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <div class="list" style="margin: 2em 0;">
            <table style="width: 100%;">
                <thead>
                <tr>
                    <th>
                        ${entry.key.encodeAsHTML()}
                    </th>
                    <g:each var="environment" in="${systemInstance.environments}">
                        <th>${environment.name.encodeAsHTML()}</th>
                    </g:each>
                </tr>
                </thead>
                <tbody>
                <g:each in="${entry.value}" var="application">
                    <tr>
                        <td>
                            <g:link controller="application" action="show" id="${application.id}">
                                ${application?.encodeAsHTML()}
                            </g:link>
                        </td>
                        <g:each var="environment" in="${systemInstance.environments}">
                            <td>
                                &nbsp;
                            </td>
                        </g:each>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="systemDoesNotHaveAnyApplications.message"
                   default="This system does not have any applications."/>
    </p>
</g:else>