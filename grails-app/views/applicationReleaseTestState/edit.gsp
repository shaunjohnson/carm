<%@ page import="carm.ApplicationReleaseTestState" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationReleaseTestStateInstance}" pageName="${message(code: 'default.edit.label', args: [entityName])}" />
    
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationReleaseTestStateInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationReleaseTestStateInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${applicationReleaseTestStateInstance?.id}"/>
        <g:hiddenField name="version" value="${applicationReleaseTestStateInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="applicationReleaseTestState.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationReleaseTestStateInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${applicationReleaseTestStateInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="applicationReleaseTestState.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationReleaseTestStateInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${applicationReleaseTestStateInstance?.description}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${applicationReleaseTestStateInstance.id}">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="save" class="save" value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
