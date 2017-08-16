<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="addUser.sendButtonText" />

<main class="main">
    <s:i18n name="controller.struts.action.common.pages.admin.package">
    <ol class="breadcrumb">
        <s:url var="adminMainPageUrl" namespace="/admin" action="index">
            <s:param name="locale" value="%{#request.locale}"/>
        </s:url>
        <s:url var="usersManagementUrl" namespace="/admin/users" action="users-management">
            <s:param name="locale" value="%{#request.locale}"/>
        </s:url>
        <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
        <li class="breadcrumb-item"><s:a href="%{#usersManagementUrl}"><s:text name="usersManagement"/></s:a></li>
        <li class="breadcrumb-item"><s:text name="addUser"/></li>
    </s:i18n>
    </ol>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6"> 
                    <s:form action="add-user" theme="bootstrap" name="addUserForm" id="addUserForm">
                        <div class="card">   
                            <div class="card-header">
                                <strong><s:text name="addUser.body.title"/></strong>
                            </div>
                            <div class="card-block">

                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userAccountChangedData.login" id="login" key="userAccountChangedData.login" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:password name="user.userAccountChangedData.password" id="password" key="userAccountChangedData.password" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:password name="repeatPassword" id="repeatPassword" key="userAccountChangedData.repeatPassword" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.name" id="name" key="userProfile.name" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.surname" id="surname" key="userProfile.surname" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.patronymic" id="patronymic" key="userProfile.patronymic" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.dateOfBirth" id="dateOfBirth" key="userProfile.dateOfBirth" class="form-control" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="user.userProfile.email" id="email" key="userProfile.email" class="form-control" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:select list="%{#{'m':getText('userProfile.sex.male'), 'f':getText('userProfile.sex.female')}}" key="userProfile.sex"
                                                    name="user.userProfile.sex" id="sex" required="true"/>
                                    </div>
                                </div>
                                <!--/row--> 
                                
                                <div class="row">
                                    
                                    <div class="col-sm-12">
                                        
                                        <s:select list="%{getAllZoneNamesAndZoneNamesWithOffsetsMap()}" key="userProfile.timezone"
                                                    name="user.userProfile.timezone" id="timezone" required="true"
                                                    value="'Asia/Chita'"/>
                                    </div>
                                </div>


                                    
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:select list="%{getAllUserGroups()}" key="chooseUserGroupLabel"
                                                    name="user.userGroupId" id="userGroupId" required="true"
                                                    value="4"/>
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
