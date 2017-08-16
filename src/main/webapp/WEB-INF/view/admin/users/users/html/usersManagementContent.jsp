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
            <li class="breadcrumb-item"><s:text name="usersManagement"/></li>
        </s:i18n>
    </ol>
    
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-align-justify"></i> <s:text name="mainPagesList.body.title"/>

                            <s:i18n name="controller.struts.action.common.pages.admin.package">
                                <s:url var="addUserUrl" namespace="/admin/users" action="add-user-input">
                                    <s:param name="locale" value="%{#request.locale}"/>
                                </s:url>
                                <button class="btn btn-primary" onclick="location.href='${addUserUrl}'"><s:text name="button.add"/></button>
                            </s:i18n>

                        </div>
                        <div class="card-block">
                            <div class="table-responsive">
                                <table class="table table-hover table-condensed">
                                    <thead>
                                        <tr>
                                            <th><s:text name="userAccount.login"/></th>
                                            <th><s:text name="userProfile.surname"/></th>
                                            <th><s:text name="userProfile.name"/></th>
                                            <th><s:text name="userProfile.patronymic"/></th>
                                            <th><s:text name="userProfile.dateOfBirth"/></th>
                                            <th><s:text name="userProfile.sex"/></th>
                                            <th><s:text name="userProfile.email"/></th>
                                            <th><s:text name="userProfile.timezone"/></th>
                                            <th><s:text name="userGroup"/></th>

                                                <th></th>


                                                <th></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="usersList" var="item">
                                            <s:url var="editUserUrl" namespace="/admin/users" action="edit-user-input">
                                                <s:param name="userId" value="userAccount.userId"/>
                                                <s:param name="locale" value="%{#request.locale}"/>
                                            </s:url>

                                            <tr>
                                                <td><s:property value="userAccount.login"/></td>
                                                <td><s:property value="userProfile.surname"/></td>
                                                <td><s:property value="userProfile.name"/></td>
                                                <td><s:property value="%{userProfile.patronymic.get()}"/></td>
                                                <td><s:property value="userProfile.dateOfBirth"/></td>
                                                <td><s:property value="%{getText(userProfile.sex.toString())}"/></td>
                                                <td><s:property value="userProfile.email"/></td>
                                                <td><s:property value="userProfile.timezone"/></td>
                                                <td><s:property value="%{#item.userGroup.name}"/></td>
                                                
                                                    <s:i18n name="controller.struts.action.common.pages.admin.package">

                                                        <td>
                                                            <div class="control-group">
                                                                <div class="controls">
                                                                    <button class="btn btn-warning" onclick="location.href='${editUserUrl}'">
                                                                        <s:text name="button.edit"/>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </td>


                                                        <td>
                                                            <div class="control-group">
                                                                <div class="controls">
                                                                    <button id="removeUser${userAccount.userId}" class="btn btn-danger" dataMap-user-id="${userAccount.userId}">
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
                       

