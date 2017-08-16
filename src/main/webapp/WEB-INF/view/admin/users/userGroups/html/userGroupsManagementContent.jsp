<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<main class="main">
    <ol class="breadcrumb">
        <s:i18n name="controller.struts.action.common.pages.admin.package">
            <s:url var="adminMainPageUrl" namespace="/admin" action="index">
                <s:param name="locale" value="%{#request.locale}"/>
            </s:url>
            <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
            <li class="breadcrumb-item"><s:text name="userGroupsManagement"/></li>
        </s:i18n>
    </ol>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-align-justify"></i> <s:text name="userGroupsList.body.title"/>

                            <s:i18n name="controller.struts.action.common.pages.admin.package">
                                <s:url var="addUserGroupUrl" namespace="/admin/users" action="add-usergroup-input">
                                    <s:param name="locale" value="%{#request.locale}"/>
                                </s:url>
                                <button class="btn btn-primary" onclick="location.href='${addUserGroupUrl}'"><s:text name="button.add"/></button>
                            </s:i18n>

                        </div>
                        <div class="card-block">
                            <div class="table-responsive">
                                <table class="table table-hover table-condensed">
                                    <thead>
                                        <tr>
                                            <th><s:text name="userGroupName"/></th>

                                                <th></th>
                                                <th></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="allUserGroupsPages" var="item">
                                            <s:url var="editUserGroupUrl" namespace="/admin/users" action="edit-usergroup-input">
                                                <s:param name="userGroupId" value="%{#item.id}"/>
                                                <s:param name="locale" value="%{#request.locale}"/>
                                            </s:url>

                                            <tr>
                                                <td><s:property value="name"/></td>

                                                    <s:i18n name="controller.struts.action.common.pages.admin.package">
                                                        <td>
                                                            <div class="control-group">
                                                                <div class="controls">
                                                                    <button class="btn btn-warning" onclick="location.href='${editUserGroupUrl}'">
                                                                        <s:text name="button.edit"/>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="control-group">
                                                                <div class="controls">
                                                                    <button id="removeUser${id}" class="btn btn-danger" dataMap-usergroup-id="${id}">
                                                                        <s:text name="button.remove"/>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </s:i18n>

                                            </tr>
                                        </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                                            
                            <s:url var="pagginationBaseUrl" namespace="/admin/users" action="users-management">
                                <s:param name="locale" value="%{#request.locale}"/>
                            </s:url>
                            <s:include value="/WEB-INF/view/common/html/paggination.jsp"/>
                                                
                        </div>
                    </div>
                    <div>
                        
                    </div>
                </div>
                <!--/col-->
            </div>
            <!--/.row-->
        </div>

    </div>
    <!-- /.conainer-fluid -->
</main>
                       

