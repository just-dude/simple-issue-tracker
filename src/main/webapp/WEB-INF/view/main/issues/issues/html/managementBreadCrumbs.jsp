<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="sendButtonText"/>


<s:i18n name="controller.struts.action.common.pages.main.package">
    <ol class="breadcrumb">
    <s:url var="mainMainPageUrl" namespace="/main" action="index">
        <s:param name="locale" value="%{#request.locale}"/>
    </s:url>
    <s:url var="issuesManagementUrl" namespace="/main/issues" action="issues-management-input">
        <s:param name="locale" value="%{#request.locale}"/>
    </s:url>
    <li class="breadcrumb-item"><s:a href="%{#mainMainPageUrl}"><s:text name="mainPage"/></s:a></li>
    <li class="breadcrumb-item"><s:text name="issuesManagement"/></li>
</s:i18n>
</ol>
