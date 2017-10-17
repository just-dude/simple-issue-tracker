<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="row">
    <div class="col-sm-12">
        <div class="card">
            <div class="card-header">
                <i class="fa fa-align-justify"></i> <s:text name="userGroupsManagement"/>

                <s:i18n name="controller.struts.action.common.pages.admin.package">
                    <s:url var="addUserGroupUrl" namespace="/admin/users" action="add-usergroup-input">
                        <s:param name="locale" value="%{#request.locale}"/>
                    </s:url>
                    <button class="btn btn-primary" onclick="location.href='${addUserGroupUrl}'"><s:text
                            name="button.add"/></button>
                </s:i18n>

            </div>
            <div class="card-block">
                <div class="table-responsive">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th><s:text name="userGroup.name"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="userGroupsPage.content" var="item">
                            <s:url var="addUserGroupUrl" namespace="/admin/users" action="edit-usergroup-input">
                                <s:param name="userGroupId" value="%{#item.id}"/>
                                <s:param name="locale" value="%{#request.locale}"/>
                            </s:url>
                            <s:url var="removeUserGroupUrl" namespace="/admin/users" action="usergroups-management">
                                <s:param name="forRemoveUserGroupId" value="%{#item.id}"/>
                                <s:param name="locale" value="%{#request.locale}"/>
                                <s:param name="pageNumber" value="%{#request.pageNumber}"/>
                            </s:url>

                            <tr>
                                <td><s:property value="%{#item.name}"/></td>

                                <s:i18n name="controller.struts.action.common.pages.admin.package">

                                    <td>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button class="btn btn-warning"
                                                        onclick="location.href='${addUserGroupUrl}'">
                                                    <s:text name="button.edit"/>
                                                </button>
                                            </div>
                                        </div>
                                    </td>


                                    <td>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button class="btn btn-danger"
                                                        onclick="location.href='${removeUserGroupUrl}'">
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

                <s:url var="pagginationBaseUrl" namespace="/admin/users" action="usergroups-management-input">
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

                       

