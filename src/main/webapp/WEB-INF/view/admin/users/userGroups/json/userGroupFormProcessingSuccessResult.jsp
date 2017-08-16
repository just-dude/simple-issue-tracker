<%@ taglib prefix="sjson" uri="SimpleJson" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<sjson:object>
    <sjson:property name="head" encloseInQuotes="false">
        <sjson:object>
            <sjson:property name="status" encloseInQuotes="true">Success</sjson:property>
        </sjson:object>
    </sjson:property>
    <sjson:property name="body" encloseInQuotes="false">
        <sjson:object>
            <s:i18n name="controller.struts.action.common.pages.package">
                <sjson:property name="message" encloseInQuotes="true">
                    <s:text name="requestSuccess.message"/>
                </sjson:property> 
            </s:i18n>
            <sjson:property name="userGroup" encloseInQuotes="false">
                <sjson:tojson param="${userGroup}"/>
            </sjson:property>
        </sjson:object>
    </sjson:property>
</sjson:object> 