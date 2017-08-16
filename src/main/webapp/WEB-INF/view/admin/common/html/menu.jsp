<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
          
<s:i18n name="controller.struts.action.common.pages.admin.package">
    <div class="sidebar">

        <nav class="sidebar-nav">
            <ul class="nav">
                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-puzzle"></i><s:text name="usersMenuItemText"/></a>
                    <ul class="nav-dropdown-items">
                        <s:url namespace="/admin/users" action="users-management" var="usersManagementLink" >
                            <s:param name="locale"><s:property value="#request.locale"/></s:param>
                        </s:url>
                        <li class="nav-item">
                            <s:a href="%{usersManagementLink}" class="nav-link"><i class="icon-puzzle"></i><s:text name="usersManagementMenuItemText"/></s:a>
                        </li>
                    </ul>
                    <ul class="nav-dropdown-items">
                        <s:url namespace="/admin/users" action="usergroups-management" var="userGroupsManagementLink" >
                            <s:param name="locale"><s:property value="#request.locale"/></s:param>
                        </s:url>
                        <li class="nav-item">
                            <s:a href="%{userGroupsManagementLink}" class="nav-link"><i class="icon-puzzle"></i><s:text name="userGroupsManagementMenuItemText"/></s:a>
                        </li>
                    </ul>
                    
                    
                    <ul class="nav-dropdown-items">
                        <s:url namespace="/admin/users" action="usergroup-and-userrole-r10p-management" var="userGroupAndUserRoleR10psManagementLink" >
                            <s:param name="locale"><s:property value="#request.locale"/></s:param>
                        </s:url>
                        <li class="nav-item">
                            <s:a href="%{userGroupAndUserRoleR10psManagementLink}" class="nav-link"><i class="icon-puzzle"></i><s:text name="userGroupAndUserRoleRelationshipManagement"/></s:a>
                        </li>
                    </ul>
                    
                </li>
                
                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-puzzle"></i><s:text name="mainPagesManagementMenuItemText"/></a>
                    
                    <ul class="nav-dropdown-items">
                        <s:url namespace="/admin/main-pages" action="pages-management" var="mainPagesManagementLink" >
                            <s:param name="locale"><s:property value="#request.locale"/></s:param>
                        </s:url>
                        <li class="nav-item">
                            <s:a href="%{mainPagesManagementLink}" class="nav-link"><i class="icon-puzzle"></i><s:text name="mainPagesManagementMenuItemText"/></s:a>
                        </li>
                    </ul>
                </li>
                <!--<li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-star"></i> Icons</a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="icons-font-awesome.html"><i class="icon-star"></i> Font Awesome</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="icons-simple-line-icons.html"><i class="icon-star"></i> Simple Line Icons</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="widgets.html"><i class="icon-calculator"></i> Widgets <span class="tag tag-info">NEW</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="charts.html"><i class="icon-pie-chart"></i> Charts</a>
                </li>
                <li class="divider"></li>
                <li class="nav-title">
                    Extras
                </li>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-star"></i> Pages</a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="pages-login.html" target="_top"><i class="icon-star"></i> Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="pages-register.html" target="_top"><i class="icon-star"></i> Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="pages-404.html" target="_top"><i class="icon-star"></i> Error 404</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="pages-500.html" target="_top"><i class="icon-star"></i> Error 500</a>
                        </li>
                    </ul>
                </li>-->

            </ul>
        </nav>
    </div>
</s:i18n>