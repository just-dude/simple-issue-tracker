<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="row">
    <div class="col-sm-12">
        <div class="card">
            <div class="card-header">
                <i class="fa fa-align-justify"></i> <s:text name="issuesManagement"/>

                <s:i18n name="controller.struts.action.common.pages.main.package">
                    <s:url var="addIssueUrl" namespace="/main/issues" action="add-issue-input">
                        <s:param name="locale" value="%{#request.locale}"/>
                    </s:url>
                    <button class="btn btn-primary" onclick="location.href='${addIssueUrl}'"><s:text
                            name="button.add"/></button>
                </s:i18n>

            </div>
            <div class="card-block">
                <div class="table-responsive">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th><s:text name="issue..login"/></th>
                            <th><s:text name="issue.profile.surname"/></th>
                            <th><s:text name="issue.profile.name"/></th>
                            <th><s:text name="issue.profile.email"/></th>
                            <th><s:text name="issue"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="issuesPage.content" var="item">
                            <s:url var="editIssueUrl" namespace="/main/issues" action="edit-issue-input">
                                <s:param name="issueId" value="%{#item.id}"/>
                                <s:param name="locale" value="%{#request.locale}"/>
                            </s:url>
                            <s:url var="removeIssueUrl" namespace="/main/issues" action="issues-management">
                                <s:param name="forRemoveIssueId" value="%{#item.id}"/>
                                <s:param name="locale" value="%{#request.locale}"/>
                                <s:param name="pageNumber" value="%{#request.pageNumber}"/>
                            </s:url>

                            <tr>
                                <td><s:property value="%{#item..login}"/></td>
                                <td><s:property value="%{#item.profile.surname}"/></td>
                                <td><s:property value="%{#item.profile.name}"/></td>
                                <td><s:property value="%{#item.profile.email}"/></td>
                                <td><s:property value="%{#item.group.name}"/></td>

                                <s:i18n name="controller.struts.action.common.pages.main.package">

                                    <td>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button class="btn btn-warning"
                                                        onclick="location.href='${editIssueUrl}'">
                                                    <s:text name="button.edit"/>
                                                </button>
                                            </div>
                                        </div>
                                    </td>


                                    <td>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button class="btn btn-danger"
                                                        onclick="location.href='${removeIssueUrl}'">
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

                <s:url var="pagginationBaseUrl" namespace="/main/issues" action="issues-management-input">
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

                       

