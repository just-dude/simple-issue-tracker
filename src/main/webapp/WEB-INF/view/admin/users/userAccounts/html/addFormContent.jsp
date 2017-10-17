<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="sendButtonText"/>


<div class="row">
    <div class="col-sm-6">
        <s:form action="add-user" theme="bootstrap" name="addUserForm" id="addUserForm">
            <div class="card">
                <div class="card-header">
                    <strong><s:text name="addUser.title"/></strong>
                </div>
                <div class="card-block">

                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="userAccount.accountInfo.login" id="login"
                                         key="userAccount.accountInfo.login"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:password name="userAccount.accountInfo.hashedPassword" id="password"
                                        key="userAccount.accountInfo.password"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:password name="repeatPassword" id="repeatPassword"
                                        key="userAccount.accountInfo.repeatPassword"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="userAccount.profile.name" id="name"
                                         key="userAccount.profile.name"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="userAccount.profile.surname" id="surname"
                                         key="userAccount.profile.surname"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="userAccount.profile.email" id="email"
                                         key="userAccount.profile.email"
                                         class="form-control"/>
                        </div>
                    </div>
                    <!--/row-->


                    <div class="row">
                        <div class="col-sm-12">
                            <s:select list="%{findAllUserGroups()}" key="chooseUserGroupLabel"
                                      name="userAccount.group.id" id="userAccountId" required="true"
                                      value="2"/>
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

