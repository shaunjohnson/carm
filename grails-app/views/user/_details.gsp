<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
</div>

<table class="details">
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
