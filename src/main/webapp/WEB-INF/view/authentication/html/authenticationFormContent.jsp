<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:i18n name="controller.struts.action.common.pages.main.package">
    <div id="authentication-container">
        <div id="auth-headline" class="text-center">
            <a href="index.php" class="inline-block"><img id="zabgu_emblem" src="/resources/main-theme/img/emblem.png"></a>
            <h3 class="inline-block"><s:text name="tsuName"/></h3>
        </div>

        <div id="auth-block-container">
            <div id="login-form-container" class="auth-block center-block">
                <h3><s:text name="offerToLogin"/></h3>
                <s:form action="login" namespace="/authentication" theme="bootstrap" name="loginForm" id="loginForm">
                    <s:textfield name="userLoginAndPassword.login" id="login" key="login"/>
                    <s:password name="userLoginAndPassword.password" id="password" key="password"/>
                    <s:hidden name="locale" value="%{#request.locale}"/>
                    <div id="actionErrorsContainer" class="errors">
                        <ul id="actionErrors">
                        </ul>
                    </div>
                    <div>
                        <s:text name="sendButtonText" var="sendButtonText"/>
                        <s:submit value="%{#sendButtonText}" class="btn btn-sm btn-primary"/>
                    </div>
                </s:form>
            </div>
            <div id="registration-container" class="auth-block center-block">
                <s:url action="refistraion" var="registrationPage"/>
                <h3 class="text-center registration-block-title"><s:text name="offerToRegistration"/></h3>
                <button onclick="location.href=${registrationPage}" class="btn btn-sm btn-primary"><s:text
                        name="registrationButtonText"/></button>
            </div>
        </div>

        <div id="success-login-container">
            <div id="success-login-message"></div>
            <button id="logout-button" class="btn btn-sm btn-primary center-block"><s:text
                    name="logoutButtonText"/></button>
            <div id="logout-response-errors" class="errors"></div>
        </div>
    </div>
</s:i18n>