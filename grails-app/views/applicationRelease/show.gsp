<%@ page import="carm.ApplicationRelease" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'ApplicationRelease')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <sec:ifLoggedIn>
            <div class="nav">
                <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
                <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            </div>
        </sec:ifLoggedIn>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="application.project.label" default="Project" /></td>
                            <td valign="top" class="value"><g:link controller="home" action="showProject" id="${applicationReleaseInstance?.application?.project?.id}">${applicationReleaseInstance?.application?.project?.encodeAsHTML()}</g:link></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="applicationRelease.application.label" default="Application" /></td>
                            <td valign="top" class="value"><g:link controller="home" action="showApplication" id="${applicationReleaseInstance?.application?.id}">${applicationReleaseInstance?.application?.encodeAsHTML()}</g:link></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="applicationRelease.releaseNumber.label" default="Release Number" /></td>
                            <td valign="top" class="value">${fieldValue(bean: applicationReleaseInstance, field: "releaseNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="applicationRelease.description.label" default="Description" /></td>
                            <td valign="top" class="value">${fieldValue(bean: applicationReleaseInstance, field: "description")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="applicationRelease.dateCreated.label" default="Date Created" /></td>
                            <td valign="top" class="value"><g:formatDate date="${applicationReleaseInstance?.dateCreated}" /></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="applicationRelease.lastUpdated.label" default="Last Updated" /></td>
                            <td valign="top" class="value"><g:formatDate date="${applicationReleaseInstance?.lastUpdated}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <sec:ifLoggedIn>
                <div class="buttons">
                    <g:form>
                        <g:hiddenField name="id" value="${applicationReleaseInstance?.id}" />
                        <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                        <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                    </g:form>
                </div>
            </sec:ifLoggedIn>
        </div>
    </body>
</html>