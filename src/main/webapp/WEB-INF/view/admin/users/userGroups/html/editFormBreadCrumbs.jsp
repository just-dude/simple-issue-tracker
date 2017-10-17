<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="sendButtonText"/>


<s:i18n name="controller.struts.action.common.pages.admin.package">
    <ol class="breadcrumb">
    <s:url var="adminMainPageUrl" namespace="/admin" action="index">
        <s:param name="locale" value="%{#request.locale}"/>
    </s:url>
    <s:url var="userGroupsManagementUrl" namespace="/admin/users" action="usergroups-management-input">
        <s:param name="locale" value="%{#request.locale}"/>
    </s:url>
    <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
    <li class="breadcrumb-item"><s:a href="%{#userGroupsManagementUrl}"><s:text name="userGroupsManagement"/></s:a></li>
    <li class="breadcrumb-item"><s:text name="editUserGroup"/></li>
</s:i18n>
</ol>