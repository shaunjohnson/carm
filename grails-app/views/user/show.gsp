<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${userInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div id="projectTabs" class="tab-container">
        <ul class="tabs">
            <li>
                <a href="#summaryTab">
                    <g:message code="details.label" default="Details"/>
                </a>
            </li>
            <li>
                <a href="#favoritesAndWatchesTab">
                    <g:message code="favoritesAndWatches.label" default="Favorites and Watches"/>
                </a>
            </li>
            <li>
                <a href="#activityTab">
                    <g:message code="activity.label" default="Activity"/>
                </a>
            </li>
        </ul>

        <div class="panel-container">
            <div id="summaryTab">
                <table>
                    <tbody>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <g:message code="user.fullName.label" default="Full Name"/>
                        </td>
                        <td valign="top" class="value">
                            ${userInstance.fullName.encodeAsHTML()}
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <g:message code="user.username.label" default="Username"/>
                        </td>
                        <td valign="top" class="value">
                            ${userInstance.username.encodeAsHTML()}
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <g:message code="user.email.label" default="Email"/>
                        </td>
                        <td valign="top" class="value">
                            <a href="mailto:${userInstance.email}">
                                ${userInstance.email.encodeAsHTML()}
                            </a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div id="favoritesAndWatchesTab">
                <table class="twoColumnLayout">
                    <tbody>
                    <tr>
                        <td class="layoutColumnFirst">
                            <g:render template="favorites"
                                      model="[favorites: favorites, isCurrentUser: isCurrentUser, userInstance: userInstance]"/>
                        </td>
                        <td class="layoutColumnLast">
                            <g:render template="watches"
                                      model="[watches: watches, isCurrentUser: isCurrentUser, userInstance: userInstance]"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div id="activityTab">
                <g:render template="/common/activity"
                          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: userInstance.id]"/>
            </div>
        </div>
    </div>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#projectTabs").easytabs({ animationSpeed:'fast' });
        });
    </script>

</div>
</body>
</html>
