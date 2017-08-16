<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:text var="sendButtonText" name="addUserGroup.sendButtonText" />

<main class="main">
    <s:i18n name="controller.struts.action.common.pages.admin.package">
        <ol class="breadcrumb">
            <s:url var="adminMainPageUrl" namespace="/admin" action="index">
                <s:param name="locale" value="%{#request.locale}"/>
            </s:url>
            <s:url var="userGroupsManagementUrl" namespace="/admin/users" action="usergroups-management">
                <s:param name="locale" value="%{#request.locale}"/>
            </s:url>
            <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
            <li class="breadcrumb-item"><s:a href="%{#userGroupsManagementUrl}"><s:text name="userGroupsManagement"/></s:a></li>
            <li class="breadcrumb-item"><s:text name="addUserGroup"/></li>
        </ol>
    </s:i18n>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6"> 
                    <s:form action="add-usergroup" theme="bootstrap" name="addUserGroupForm" id="addUserGroupForm">
                        <div class="card">   
                            <div class="card-header">
                                <strong><s:text name="addUserGroup.body.title"/></strong>
                            </div>
                            <div class="card-block">
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="userGroup.name" id="name" key="userGroupName" />
                                    </div>
                                </div>
                                <s:hidden name="locale" value="%{#request.locale}"/>
                                <!--/row-->  
                            </div>
                            <div class="card-footer">
                                <s:submit value="%{sendButtonText}" class="btn btn-sm btn-primary"/>
                            </div>
                        </div>
                    </s:form> 
                </div>
                <!--/col-->
            </div>
            <!--/.row-->
        </div>

    </div>
    <!-- /.conainer-fluid -->
</main>
