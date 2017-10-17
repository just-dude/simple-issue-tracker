<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<main class="main">
    <ol class="breadcrumb">
        <s:i18n name="controller.struts.action.common.pages.admin.package">
            <s:url var="adminMainPageUrl" namespace="/admin" action="index">
                <s:param name="locale" value="%{#request.locale}"/>
            </s:url>
            <li class="breadcrumb-item"><s:a href="%{#adminMainPageUrl}"><s:text name="mainPage"/></s:a></li>
            <li class="breadcrumb-item"><s:text name="mainPagesManagement"/></li>
        </s:i18n>
    </ol>

    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-align-justify"></i> <s:text name="mainPagesList.body.title"/>

                            <s:i18n name="controller.struts.action.common.pages.admin.package">
                                <s:url var="addMainPageUrlRU" namespace="/admin/main-pages" action="add-page-input">
                                    <s:param name="locale" value="%{#request.locale}"/>
                                    <s:param name="language" value="'ru'"/>
                                </s:url>
                                <s:url var="addMainPageUrlEN" namespace="/admin/main-pages" action="add-page-input">
                                    <s:param name="locale" value="%{#request.locale}"/>
                                    <s:param name="language" value="'en'"/>
                                </s:url>
                                <div class="dropdown" style="display: inline-block;">
                                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2"
                                            dataMap-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <s:text name="button.add"/>
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                        <button class="dropdown-item" onclick="location.href='${addMainPageUrlRU}'"
                                                type="button">
                                            <s:text name="russianLanguage"/>
                                        </button>
                                        <button class="dropdown-item" onclick="location.href='${addMainPageUrlEN}'"
                                                type="button">
                                            <s:text name="englishLanguage"/>
                                        </button>
                                    </div>
                                </div>
                            </s:i18n>

                        </div>
                        <div class="card-block">
                            <div class="table-responsive">
                                <table class="table table-hover table-condensed">
                                    <thead>
                                    <tr>
                                        <th><s:text name="page.id"/></th>
                                        <th><s:text name="page.language"/></th>
                                        <th><s:text name="page.name"/></th>
                                        <th><s:text name="page.creationUTCDateTime"/></th>
                                        <th><s:text name="page.creator"/></th>
                                        <th><s:text name="page.lastChangingUTCDateTime"/></th>
                                        <th><s:text name="page.lastChanger"/></th>
                                        <th><s:text name="parentMainPage"/></th>

                                        <th></th>


                                        <th></th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="mainPagesList" var="item">
                                        <s:url var="editMainPageUrl" namespace="/admin/main-pages/"
                                               action="edit-page-input">
                                            <s:param name="mainPageId" value="page.id"/>
                                            <s:param name="locale" value="%{#request.locale}"/>
                                        </s:url>

                                        <tr>
                                            <td><s:property value="page.id"/></td>
                                            <td><s:property value="page.language"/></td>
                                            <td><s:property value="page.name"/></td>
                                            <td><s:property value="page.creationUTCDateTime"/></td>
                                            <td><s:property value="page.creator.userProfile.surname"/> <s:property
                                                    value="page.creator.userProfile.name"/></td>
                                            <td><s:property value="page.lastChangingUTCDateTime"/></td>
                                            <td><s:property value="page.lastChanger.userProfile.surname"/> <s:property
                                                    value="page.lastChanger.userProfile.name"/></td>
                                            <td><s:property value="parentMainPage.name"/></td>

                                            <s:i18n name="controller.struts.action.common.pages.admin.package">

                                                <td>
                                                    <div class="control-group">
                                                        <div class="controls">
                                                            <button class="btn btn-warning"
                                                                    onclick="location.href='${editMainPageUrl}'">
                                                                <s:text name="button.edit"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </td>


                                                <td>
                                                    <div class="control-group">
                                                        <div class="controls">
                                                            <button id="removeUser${page.id}" class="btn btn-danger"
                                                                    dataMap-main-page-id="${page.id}">
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
            <!--/.row-->
        </div>

    </div>
    <!-- /.conainer-fluid -->
</main>
                       

