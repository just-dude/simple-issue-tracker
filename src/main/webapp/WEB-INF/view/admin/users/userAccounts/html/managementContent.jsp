<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="row">
    <div class="col-sm-12">
        <div class="card">
            <div class="card-header">
                <i class="fa fa-align-justify"></i> <s:text name="usersManagement"/>

                <s:i18n name="controller.struts.action.common.pages.admin.package">
                    <s:url var="addUserUrl" namespace="/admin/users" action="add-user-input">
                        <s:param name="locale" value="%{#request.locale}"/>
                    </s:url>
                    <button class="btn btn-primary" onclick="location.href='${addUserUrl}'"><s:text
                            name="button.add"/></button>
                </s:i18n>

            </div>
            <div class="card-block">
                <div class="table-responsive">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th><s:text name="userAccount.accountInfo.login"/></th>
                            <th><s:text name="userAccount.profile.surname"/></th>
                            <th><s:text name="userAccount.profile.name"/></th>
                            <th><s:text name="userAccount.profile.email"/></th>
                            <th><s:text name="userAccount"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="userAccountsPage.content" var="item">
                            <s:url var="editUserUrl" namespace="/admin/users" action="edit-user-input">
                                <s:param name="userAccountId" value="%{#item.id}"/>
                                <s:param name="locale" value="%{#request.locale}"/>
                            </s:url>
                            <s:url var="removeUserUrl" namespace="/admin/users" action="users-management">
                                <s:param name="forRemoveUserId" value="%{#item.id}"/>
                                <s:param name="locale" value="%{#request.locale}"/>
                                <s:param name="pageNumber" value="%{#request.pageNumber}"/>
                            </s:url>

                            <tr>
                                <td><s:property value="%{#item.accountInfo.login}"/></td>
                                <td><s:property value="%{#item.profile.surname}"/></td>
                                <td><s:property value="%{#item.profile.name}"/></td>
                                <td><s:property value="%{#item.profile.email}"/></td>
                                <td><s:property value="%{#item.group.name}"/></td>

                                <s:i18n name="controller.struts.action.common.pages.admin.package">

                                    <td>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button class="btn btn-warning"
                                                        onclick="location.href='${editUserUrl}'">
                                                    <s:text name="button.edit"/>
                                                </button>
                                            </div>
                                        </div>
                                    </td>


                                    <td>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button class="btn btn-danger"
                                                        onclick="location.href='${removeUserUrl}'">
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

                <s:url var="pagginationBaseUrl" namespace="/admin/users" action="users-management-input">
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

                       

