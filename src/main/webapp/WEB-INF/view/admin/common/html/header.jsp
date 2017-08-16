<%@ taglib prefix="s" uri="/struts-tags" %> 
<s:i18n name="controller.struts.action.common.pages.admin.package">
    <header class="navbar">
        <div class="container-fluid">
            <s:url var="adminMainPageUrl" namespace="/admin" action="index">
                <s:param name="locale" value="%{#request.locale}"/>
            </s:url>
            <s:a href="%{#adminMainPageUrl}" class="navbar-brand"></s:a>
            <ul class="nav navbar-nav hidden-md-down">
                <s:url var="siteIndexPageURL" namespace="" action="index">
                    <s:param name="locale" value="%{#request.locale}"/>
                </s:url>
                <li class="nav-item px-1">
                    <s:a class="nav-link" href="%{siteIndexPageURL}"><s:text name="goToWebsiteLinkText"/></s:a>
                </li>   
            </ul>
            <!--<ul class="nav navbar-nav float-xs-right hidden-md-down">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" dataMap-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                        <img src="/img/avatars/6.jpg" class="img-avatar" alt="admin@bootstrapmaster.com">
                        <span class="hidden-md-down">Admin</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">

                        <div class="dropdown-header text-xs-center">
                            <strong>Account</strong>
                        </div>

                        <a class="dropdown-item" href="#"><i class="fa fa-bell-o"></i> Updates<span class="tag tag-info">42</span></a>
                        <a class="dropdown-item" href="#"><i class="fa fa-envelope-o"></i> Messages<span class="tag tag-success">42</span></a>
                        <a class="dropdown-item" href="#"><i class="fa fa-tasks"></i> Tasks<span class="tag tag-danger">42</span></a>
                        <a class="dropdown-item" href="#"><i class="fa fa-comments"></i> Comments<span class="tag tag-warning">42</span></a>

                        <div class="dropdown-header text-xs-center">
                            <strong>Settings</strong>
                        </div>

                        <a class="dropdown-item" href="#"><i class="fa fa-user"></i> Profile</a>
                        <a class="dropdown-item" href="#"><i class="fa fa-wrench"></i> Settings</a>
                        <a class="dropdown-item" href="#"><i class="fa fa-usd"></i> Payments<span class="tag tag-default">42</span></a>
                        <a class="dropdown-item" href="#"><i class="fa fa-file"></i> Projects<span class="tag tag-primary">42</span></a>
                        <div class="divider"></div>
                        <a class="dropdown-item" href="#"><i class="fa fa-shield"></i> Lock Account</a>
                        <a class="dropdown-item" href="#"><i class="fa fa-lock"></i> Logout</a>
                    </div>
                </li>
            </ul>-->
        </div>
    </header>
</s:i18n>
