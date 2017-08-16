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
            <li class="breadcrumb-item"><s:text name="userGroupAndUserRoleRelationshipManagement"/></li>
        </s:i18n>
    </ol>
    
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-align-justify"></i> <s:text name="userRolesAndUserGroupsAssociations"/>
                        </div>
                        <div class="card-block">
                            <s:form action="edit-usergroup-and-userrole-r10p" theme="bootstrap" name="editUserGroupAndUserRoleR10psForm" id="editUserGroupAndUserRoleR10psForm">
                                <div class="table-responsive">
                                    <table class="table table-hover table-condensed">
                                        <thead>
                                            <tr>
                                                <th><s:text name="userGroup"/></th>
                                                <th><s:text name="userRole"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <s:iterator value="userGroupAndUserRoleR10psWithResolvedRef" var="item" status="rowstatus" >
                                                <tr>
                                                    <td><s:property value="%{#item.userGroup.name}"/></td>
                                                    <s:hidden name="userGroupAndUserRoleR10ps[%{#rowstatus.index}].userGroupId" value="%{#item.userGroup.id}"/>
                                                    <td>
                                                        <s:select list="%{getAllUsersRolesIdToNameMap()}"
                                                        name="userGroupAndUserRoleR10ps[%{#rowstatus.index}].userRoleId" id="userRoleId" required="true"
                                                        value="{#item.usersRole.id}"/>   
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                                 <s:hidden name="locale" value="%{#request.locale}"/>
                                 <div>
                                    <s:submit value="%{getText('sendButtonText')}" class="btn btn-sm btn-primary"/>
                                </div>  
                            </s:form>                                                                                                                      
                        </div>
                    </div>
                    <div>
                        
                    </div>
                </div>
                <!--/col-->
                <s:if test="%{userGroupsUnrelatedWithR10ps.size>0}">
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-header">
                                <i class="fa fa-align-justify"></i> <s:text name="userRolesAndNewUserGroupsAssociations"/>
                            </div>
                            <div class="card-block">
                                <s:form action="add-usergroup-and-userrole-r10p" theme="bootstrap" name="addUserGroupAndUserRoleR10psForm" id="addUserGroupAndUserRoleR10psForm">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-condensed">
                                            <thead>
                                                <tr>
                                                    <th><s:text name="userGroup"/></th>
                                                    <th><s:text name="userRole"/></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <s:iterator value="userGroupsUnrelatedWithR10ps" var="item" status="rowstatus" >
                                                    <tr>
                                                        <td><s:property value="%{#item.name}"/></td>
                                                        <s:hidden name="userGroupAndUserRoleR10ps[%{#rowstatus.index}].userGroupId" value="%{#item.id}"/>
                                                        <td>
                                                            <s:select list="%{getAllUsersRolesIdToNameMap()}"
                                                            name="userGroupAndUserRoleR10ps[%{#rowstatus.index}].userRoleId" id="userRoleId"
                                                            required="false" emptyOption="true"/>                                                  
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </tbody>
                                        </table>
                                    </div>
                                     <s:hidden name="locale" value="%{#request.locale}"/>
                                    <div>
                                        <s:submit value="%{getText('sendButtonText')}" class="btn btn-sm btn-primary"/>
                                    </div>  
                                </s:form>                                                                                                                      
                            </div>
                        </div>
                    </div>
                </s:if>
                <!--/col-->           
            </div>
            <!--/.row-->
        </div>

    </div>
    <!-- /.conainer-fluid -->
</main>
                       

