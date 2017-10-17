<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<s:text var="sendButtonText" name="sendButtonText"/>
<div class="row">
    <div class="col-sm-6">
        <s:form action="edit-usergroup" theme="bootstrap" name="editUserGroupForm" id="editUserGroupForm">
            <div class="card">
                <div class="card-header">
                    <strong><s:text name="editUserGroup.title"/></strong>
                </div>
                <div class="card-block">
                    <!--/row-->

                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:hidden name="userGroup.id" id="id"/>
                        </div>
                    </div>
                    <!--/row-->
                    <div class="row">
                        <div class="col-sm-12">
                            <s:textfield name="userGroup.name" id="name"
                                         key="userGroup.name"/>
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
