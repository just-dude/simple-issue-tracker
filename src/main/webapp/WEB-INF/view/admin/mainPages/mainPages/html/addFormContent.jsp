<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<main class="main">
    <s:i18n name="controller.struts.action.common.pages.admin.package">
    <s:text var="sendButtonText" name="commonSendButtonText" />
    <ol class="breadcrumb">
        <s:url var="adminMainPageUrl" namespace="/admin" action="index">
            <s:param name="locale" value="%{#request.locale}"/>
        </s:url>
        <s:url var="mainPagesManagementUrl" namespace="/admin/main-pages" action="pages-management">
            <s:param name="locale" value="%{#request.locale}"/>
        </s:url>
        <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
        <li class="breadcrumb-item"><s:a href="%{#mainPagesManagementUrl}"><s:text name="mainPagesManagement"/></s:a></li>
        <li class="breadcrumb-item"><s:text name="addMainPage"/></li>
    </s:i18n>
    </ol>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6"> 
                    
                    
                    <s:form action="add-page" theme="bootstrap" name="addMainPageForm" id="addMainPageForm">
                        <div class="card">   
                            <div class="card-header">
                                <strong><s:text name="addMainPage.body.title"/></strong>
                            </div>
                            <div class="card-block">
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="mainPage.page.name" id="name" key="pageName" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textfield name="mainPage.page.title" id="title" key="pageTitle" />
                                    </div>
                                </div>
                                <!--/row-->
                                <div class="row">
                                    <div class="col-sm-12">
                                        <s:textarea name="mainPage.page.content" id="content" key="pageContent" />
                                    </div>
                                </div>
                                <!--/row-->
                                 <div class="row">
                                    <div class="col-sm-12">
                                        <s:select list="%{getPageIdToPageNameMap()}" key="chooseParentPage"
                                                    name="mainPage.parentMainPageId" id="parentPageId" required="true"
                                                    value="1"/>
                                    </div>
                                </div>                                     
                               
                                <s:hidden name="mainPage.page.language" id="language" value="%{#request.parameters.language}"/>
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
