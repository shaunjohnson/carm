<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="applicationDetails" class="details">
            <tbody>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.project.label" default="Project"/></td>
                <td valign="top" class="value">
                    <g:link controller="project" action="show" id="${applicationInstance?.project?.id}">
                        ${applicationInstance?.project?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.type.label" default="Type"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="applicationType" action="show" id="${applicationInstance?.type?.id}">
                        ${applicationInstance?.type?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="application.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <div class="expander">
                            <carm:plainText value="${applicationInstance.description}"/>
                        </div>
                    </div>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.sourceControlRepository.label" default="Source Control Repository"/>
                </td>
                <td valign="top" class="value">
                    <carm:formatSourceControl repository="${applicationInstance?.sourceControlRepository}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="application.sourceControlPath.label" default="Source Control Path"/>
                </td>
                <td valign="top" class="value">
                    <carm:formatSourceControl application="${applicationInstance}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.buildInstructions.label" default="Build Instructions"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: applicationInstance, field: "buildInstructions")?.decodeHTML()}
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="application.sysEnvironment.label" default="Environment"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="systemEnvironment" action="show" id="${applicationInstance?.sysEnvironment?.id}">
                        ${applicationInstance?.sysEnvironment?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.deployInstructions.label" default="Deploy Instructions"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: applicationInstance, field: "deployInstructions")?.decodeHTML()}
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.applicationRoles.label" default="Application Roles"/>
                </td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${applicationInstance.applicationRoles}">
                        <ul>
                            <g:each in="${applicationInstance.applicationRoles.sort { it.sourceControlUser.name }}"
                                    var="applicationRole">
                                <li>
                                    <g:link controller="applicationRole" action="show" id="${applicationRole.id}">
                                        ${applicationRole?.encodeAsHTML()}
                                    </g:link>
                                </li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        <p class="emphasis">
                            <g:message code="applicationDoesNotHaveAnyAssignedUsers.message"
                                       default="This application does not have any assigned users."/>
                        </p>
                    </g:else>

                    <carmsec:isProjectOwner application="${applicationInstance}">
                        <div class="nav">
                            <span class="menuButton">
                                <g:link class="create" controller="applicationRole" action="create"
                                        params="['application.id': applicationInstance?.id]">
                                    <g:message code="addApplicationRole.label" default="Add Application Role"/>
                                </g:link>
                            </span>
                        </div>
                    </carmsec:isProjectOwner>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="application.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="applicationDetails" entityName="${entityName}">
            <carmsec:isProjectOwner application="${applicationInstance}">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${applicationInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <span class="button">
                        <g:link class="delete" action="delete" id="${applicationInstance?.id}">
                            <g:message code="default.button.delete.label" default="Delete"/>
                        </g:link>
                    </span>
                </div>
            </carmsec:isProjectOwner>
        </carm:showHideDetails>
    </div>

    <g:render template="pendingTasks" model="[pendingTasks: pendingTasks]"/>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="applicationEnvironments" model="[applicationInstance: applicationInstance]"/>

                <div>&nbsp;</div>

                <g:render template="/common/activity"
                          model="[activityList: activityList, listActivityAction: 'listActivity', domainId: applicationInstance.id]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="applicationReleases" model="[applicationInstance: applicationInstance]"/>

                <div>&nbsp;</div>

                <g:render template="applicationModules" model="[applicationInstance: applicationInstance]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
