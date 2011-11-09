<%@ page import="carm.ApplicationRelease" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'ApplicationRelease')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${applicationReleaseInstance}">
            <div class="errors">
                <g:renderErrors bean="${applicationReleaseInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${applicationReleaseInstance?.id}" />
                <g:hiddenField name="version" value="${applicationReleaseInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="application"><g:message code="applicationRelease.application.label" default="Application" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: applicationReleaseInstance, field: 'application', 'errors')}">
                                    <g:select name="application.id" from="${carm.Application.list()}" optionKey="id" value="${applicationReleaseInstance?.application?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="releaseNumber"><g:message code="applicationRelease.releaseNumber.label" default="Release Number"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: applicationReleaseInstance, field: 'releaseNumber', 'errors')}">
                                    <g:textField name="releaseNumber" maxlength="20" value="${applicationReleaseInstance?.releaseNumber}"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="applicationRelease.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: applicationReleaseInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" cols="40" rows="5" value="${applicationReleaseInstance?.description}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
