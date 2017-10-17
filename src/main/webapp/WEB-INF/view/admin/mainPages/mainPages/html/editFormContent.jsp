<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="editUser.sendButtonText"/>
<main class="main">
    <s:i18n name="controller.struts.action.common.pages.admin.package">
    <ol class="breadcrumb">
        <s:url var="adminMainPageUrl" namespace="/admin" action="index">
            <s:param name="locale" value="%{#request.locale}"/>
        </s:url>
        <s:url var="usersManagementUrl" namespace="/admin/users" action="users-management-input">
            <s:param name="locale" value="%{#request.locale}"/>
        </s:url>
        <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
        <li class="breadcrumb-item"><s:a href="%{#usersManagementUrl}"><s:text name="usersManagement"/></s:a></li>
        <li class="breadcrumb-item"><s:text name="editUser"/></li>
        </s:i18n>
    </ol>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6">
                    <s:form action="edit-user" theme="bootstrap" name="editUserForm" id="editUserForm">
                        <div class="card">
                            <div class="card-header">
                                <strong><s:text name="editUser.body.title"/></strong>
                            </div>
                            <div class="card-block">
                                <!--/row-->

                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userAccountChangedData.oldPassword" id="oldPassword"
                                                     key="userAccountChangedData.oldPassword"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:password name="user.userAccountChangedData.password" id="newPassword"
                                                    key="userAccountChangedData.newPassword"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:password name="repeatPassword" id="repeatPassword"
                                                    key="userAccountChangedData.repeatNewPassword"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:hidden name="user.userProfile.userId" id="userId"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.name" id="name" key="userProfile.name"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.surname" id="surname"
                                                     key="userProfile.surname"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:if test="%{user.userProfile.patronymic.isPresent()}">
                                            <s:textfield name="user.userProfile.patronymic" id="patronymic"
                                                         key="userProfile.patronymic"
                                                         value="%{user.userProfile.patronymic.get()}"/>
                                        </s:if>
                                        <s:else>
                                            <s:textfield name="user.userProfile.patronymic" id="patronymic"
                                                         key="userProfile.patronymic" value=""/>
                                        </s:else>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.dateOfBirth" id="dateOfBirth"
                                                     key="userProfile.dateOfBirth" class="form-control"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.email" id="email" key="userProfile.email"
                                                     class="form-control"/>
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:select
                                                list="%{#{'m':getText('userProfile.sex.male'), 'f':getText('userProfile.sex.female')}}"
                                                key="userProfile.sex"
                                                value="user.userProfile.sex" name="user.userProfile.sex" id="sex"
                                                required="true"/>
                                    </div>
                                </div>
                                <!--/row-->

                                <div class="row">

                                    <div class="col-sm-12">

                                        <s:select list="%{getAllZoneNamesAndZoneNamesWithOffsetsMap()}"
                                                  key="userProfile.timezone"
                                                  name="user.userProfile.timezone" id="timezone" required="true"
                                                  value="user.userProfile.timezone"/>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:select list="%{getAllUserGroups()}" key="chooseUserGroupLabel"
                                                  name="user.userGroupId" id="userGroupId" required="true"
                                                  value="user.userGroupId"/>
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
