<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:i18n name="controller.struts.action.common.message.package">
    <div class="row">
        <div class="col-sm-6">
            <s:if test="hasActionMessages()">
                <div class="card card-inverse card-primary">
                    <div class="card-block pb-0">
                        <p>
                            <s:property value="getActionMessages().get(0)"/>
                        </p>
                    </div>
                </div>
            </s:if>
            <s:elseif test="hasActionErrors()">
                <div class="card card-inverse card-danger">
                    <div class="card-block pb-0">
                        <p>
                            <s:property value="getActionErrors().get(0)"/>
                        </p>
                    </div>
                </div>
            </s:elseif>
        </div>
    </div>

</s:i18n>
