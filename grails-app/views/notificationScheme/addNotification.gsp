<%@ page import="carm.notification.NotificationRecipientType; carm.notification.NotificationEvent" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'notificationScheme.label', default: 'Notification Scheme')}"/>
    <title><g:message code="default.addNotification.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${notificationSchemeInstance}"
             pageName="${message(code: 'default.create.label', args: [entityName])}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${notificationInstance}">
    <div class="alert alert-error">
        <h4><g:message code="notificationScheme.error.addNotification"/></h4>
        <g:renderErrors bean="${notificationInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="saveNotification" class="offset1 span8">
    <g:hiddenField name="notificationScheme.id" value="${notificationSchemeInstance.id}"/>

    <div class="control-group">
        <carm:label class="control-label" for="notificationEvent" required="true">
            <g:message code="notificationEvent.label" default="Notification Event"/>
        </carm:label>
        <div class="controls">
            <g:select class="span4" name="notificationEvent" optionKey="key" optionValue="name"
                      from="${notificationEventList}"
                      value="${params.notificationEvent}"
                      title="${message(code: 'notificationScheme.notificationEvents.help')}"/>
        </div>
    </div>

    <div class="control-group">
        <carm:label class="control-label">
            <g:message code="destination.label" default="Destination"/>
        </carm:label>
        <div class="controls">
            <carm:label class="radio">
                <g:if test="${recipientType == NotificationRecipientType.CURRENT_USER}">
                    <g:radio name="recipientType" value="${NotificationRecipientType.CURRENT_USER}" checked="${true}"/>
                </g:if>
                <g:else>
                    <g:radio name="recipientType" value="${NotificationRecipientType.CURRENT_USER}"/>
                </g:else>
                <g:message
                        code="carm.notification.NotificationRecipientType.${NotificationRecipientType.CURRENT_USER}"/>
            </carm:label>

            <carm:label class="radio">
                <g:if test="${recipientType == NotificationRecipientType.PROJECT_ADMINISTRATORS}">
                    <g:radio name="recipientType" value="${NotificationRecipientType.PROJECT_ADMINISTRATORS}"
                             checked="${true}"/>
                </g:if>
                <g:else>
                    <g:radio name="recipientType" value="${NotificationRecipientType.PROJECT_ADMINISTRATORS}"/>
                </g:else>
                <g:message
                        code="carm.notification.NotificationRecipientType.${NotificationRecipientType.PROJECT_ADMINISTRATORS}"/>
            </carm:label>

            <div class="row">
                <div class="span2">
                    <carm:label class="radio">
                        <g:if test="${recipientType == NotificationRecipientType.GROUP}">
                            <g:radio name="recipientType" value="${NotificationRecipientType.GROUP}" checked="${true}"/>
                        </g:if>
                        <g:else>
                            <g:radio name="recipientType" value="${NotificationRecipientType.GROUP}"/>
                        </g:else>
                        <g:message
                                code="carm.notification.NotificationRecipientType.${NotificationRecipientType.GROUP}"/>
                    </carm:label>
                </div>

                <div class="span6">
                    <g:select name="group.id" from="${groupList}" optionKey="id" value="${group?.id}"
                              noSelection="['null': '']"/>
                </div>
            </div>

            <div class="row">
                <div class="span2">
                    <carm:label class="radio">
                        <g:if test="${recipientType == NotificationRecipientType.USER}">
                            <g:radio name="recipientType" value="${NotificationRecipientType.USER}" checked="${true}"/>
                        </g:if>
                        <g:else>
                            <g:radio name="recipientType" value="${NotificationRecipientType.USER}"/>
                        </g:else>
                        <g:message
                                code="carm.notification.NotificationRecipientType.${NotificationRecipientType.USER}"/>
                    </carm:label>
                </div>

                <div class="span6">
                    <g:select name="user.id" from="${userList}" optionKey="id" value="${user?.id}"
                              noSelection="['null': '']"/>
                </div>
            </div>

            <carm:label class="radio">
                <g:if test="${recipientType == NotificationRecipientType.PROJECT_WATCHERS}">
                    <g:radio name="recipientType" value="${NotificationRecipientType.PROJECT_WATCHERS}"
                             checked="${true}"/>
                </g:if>
                <g:else>
                    <g:radio name="recipientType" value="${NotificationRecipientType.PROJECT_WATCHERS}"/>
                </g:else>
                <g:message
                        code="carm.notification.NotificationRecipientType.${NotificationRecipientType.PROJECT_WATCHERS}"/>
            </carm:label>

            <carm:label class="radio">
                <g:if test="${recipientType == NotificationRecipientType.APPLICATION_WATCHERS}">
                    <g:radio name="recipientType" value="${NotificationRecipientType.APPLICATION_WATCHERS}"
                             checked="${true}"/>
                </g:if>
                <g:else>
                    <g:radio name="recipientType" value="${NotificationRecipientType.APPLICATION_WATCHERS}"/>
                </g:else>
                <g:message
                        code="carm.notification.NotificationRecipientType.${NotificationRecipientType.APPLICATION_WATCHERS}"/>
            </carm:label>

            <div class="row">
                <div class="span2">
                    <carm:label class="radio">
                        <g:if test="${recipientType == NotificationRecipientType.EMAIL_ADDRESS}">
                            <g:radio name="recipientType" value="${NotificationRecipientType.EMAIL_ADDRESS}"
                                     checked="${true}"/>
                        </g:if>
                        <g:else>
                            <g:radio name="recipientType" value="${NotificationRecipientType.EMAIL_ADDRESS}"/>
                        </g:else>
                        <g:message
                                code="carm.notification.NotificationRecipientType.${NotificationRecipientType.EMAIL_ADDRESS}"/>
                    </carm:label>
                </div>

                <div class="span6">
                    <g:textField name="emailAddress" maxlength="255" value="${emailAddress}"/>
                </div>
            </div>

        </div>
    </div>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" action="show" id="${notificationSchemeInstance.id}"><g:message
                code="default.button.cancel.label" default="Cancel"/></g:link>
    </carm:formButtons>
</g:form>

<r:script>
    jQuery(function() {
        function recipientTypeChanged() {
            var selectedValue = jQuery('input[name=recipientType]:checked').val()

            if(selectedValue == "${NotificationRecipientType.GROUP}") {
                jQuery("#group\\.id").removeAttr("disabled");
            }
            else {
                jQuery("#group\\.id").attr("disabled", "disabled").val("");
            }

            if(selectedValue == "${NotificationRecipientType.USER}") {
                jQuery("#user\\.id").removeAttr("disabled");
            }
            else {
                jQuery("#user\\.id").attr("disabled", "disabled").val("");
            }

            if(selectedValue == "${NotificationRecipientType.EMAIL_ADDRESS}") {
                jQuery("#emailAddress").removeAttr("disabled");
            }
            else {
                jQuery("#emailAddress").attr("disabled", "disabled").val("");
            }
        }

        jQuery("input[name=recipientType]").change(recipientTypeChanged);

        recipientTypeChanged();
    });
</r:script>
</body>
</html>
