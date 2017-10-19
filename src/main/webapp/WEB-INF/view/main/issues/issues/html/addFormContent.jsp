<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="sendButtonText"/>


<div class="row">
    <div class="col-sm-6">
        <s:form action="add-issue" theme="bootstrap" name="addIssueForm" id="addIssueForm">
            <div class="card">
                <div class="card-header">
                    <strong><s:text name="addIssue.title"/></strong>
                </div>
                <div class="card-block">

                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:select list="%{findAllIssueTypes()}" key="issue.type"
                                      name="issue.type" id="type" required="true"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textarea name="issue.name" id="name"
                                        key="issue.name"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textarea name="issue.description" id="description"
                                        key="issue.description"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textarea name="additionalAttributes" id="additionalAttributes"
                                        key="issue.additionalAttributes"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:select list="%{findAllUserAccounts()}" key="issue.assignee"
                                      name="issue.assignee.id" id="assignee" required="true"/>
                        </div>
                    </div>

                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:select list="%{findAllIssuePriorities()}" key="issue.priority"
                                      name="issue.priority" id="priority" required="true"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="issue.requiredResolvedDateTime" id="requiredResolvedDateTime"
                                         key="issue.requiredResolvedDateTime"/>
                        </div>
                    </div>
                    <!--/row-->
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

