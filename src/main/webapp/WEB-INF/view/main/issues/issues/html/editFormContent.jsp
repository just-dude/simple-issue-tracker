<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="sendButtonText"/>
<div class="row">
    <div class="col-sm-6">
        <s:form action="edit-issue" theme="bootstrap" name="editIssueForm" id="editIssueForm">
            <div class="card">
                <div class="card-header">
                    <strong><s:text name="editIssue.title"/></strong>
                </div>
                <div class="card-block">
                    <!--/row-->

                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:hidden name="issue.id" id="id"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="issue.profile.name" id="name"
                                         key="issue.profile.name"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="issue.profile.surname" id="surname"
                                         key="issue.profile.surname"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="issue.profile.email" id="email"
                                         key="issue.profile.email"
                                         class="form-control"/>
                        </div>
                    </div>
                    <!--/row-->


                    <div class="row">
                        <div class="col-sm-12">
                            <s:select list="%{findAllIssueGroups()}" key="chooseIssueGroupLabel"
                                      name="issue.group.id" id="issueId" required="true"
                                      value="%{issue.group.id}"/>
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
