<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:i18n name="controller.struts.action.common.pages.main.package">
    <header>
        <nav class="navbar navbar-default navbar-static-top">
          <div class="container">
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li>
                    <s:url var="ruCurrentUrl"/>
                    <s:a href="%{#ruCurrentUrl}?locale=ru_RU">
                        <img class="lang_img" src="/resources/main-theme/img/rus_flag.png" >
                        <s:if test='%{#request.locale.toString().contains("ru")}'>
                            <span class="text"><s:text name="selectRussianLanguageText"/></span>
                        </s:if>
                    </s:a>              
                </li>
                <li>
                    <s:url var="enCurrentUrl"/>
                    <s:a href="%{#enCurrentUrl}?locale=en_US">
                        <img class="lang_img" src="/resources/main-theme/img/eng_flag.png" >
                        <s:if test='%{#request.locale.toString().contains("en")}'>
                            <span class="text"><s:text name="selectEnglishLanguageText"/></span>
                        </s:if>
                    </s:a>
                </li>
                <li>
                    <s:url var="chCurrentUrl"/>
                    <s:a href="%{#chCurrentUrl}?locale=ch">
                        <img class="lang_img" src="/resources/main-theme/img/chin_flag.png" >
                        <s:if test='%{#request.locale.toString().contains("ch")}'>
                            <span class="text"><s:text name="selectChineeseLanguageText"/></span>
                        </s:if>
                    </s:a>
                </li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" id="openLoginWindowReference">
                        <img class="" src="/resources/main-theme/img/input.png" />
                        <span id="input_title_text" class="text" >
                        </span>
                    </a>
                    <s:include value="/WEB-INF/view/main/authentication/html/authenticationForm.jsp"/> 
                </li>
              </ul>
            </div>
          </div>
        </nav>
    </header>
</s:i18n>