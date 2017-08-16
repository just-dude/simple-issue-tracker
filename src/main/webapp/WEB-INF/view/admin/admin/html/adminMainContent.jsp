<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:url var="addUserUrl" namespace="/admin/users" action="add-user-input">
    <s:param name="locale" value="%{#request.locale}"/>
</s:url>
<main class="main">
    <ol class="breadcrumb">
        <s:i18n name="controller.struts.action.common.pages.admin.package">
            <li class="breadcrumb-item"><s:text name="mainPage"/></li>
        </s:i18n>
    </ol>
    
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-block" style="padding: 200px 0px;">                            
                            <h1 class="text-xs-center"><s:text name="greeting"/></h1>
                        </div>
                    </div>
                </div>
                <!--/col-->
            </div>
            <!--/.row-->
        </div>

    </div>
    <!-- /.conainer-fluid -->
</main>
                       

