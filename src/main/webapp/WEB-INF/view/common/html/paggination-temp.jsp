<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:i18n name="controller.struts.action.common.pages.admin.package">
    
    <s:if test="%{pagginationPageNumber>1}">
        <s:url var="prevPageUrl" namespace="/admin/users" action="users-management">
            <s:param name="locale" value="%{#request.locale}"/>
            <s:param name="pagginationPageNumber" value="%{pagginationPageNumber-1}"/>
        </s:url>
    </s:if>
    <s:else>
        <s:url var="prevPageUrl" value="#"></s:url>
    </s:else>
    <s:if test="%{pagginationPageNumber<#pagginationTotalPagesCount}">
        <s:url var="nextPageUrl" namespace="/admin/users" action="users-management">
            <s:param name="locale" value="%{#request.locale}"/>
            <s:param name="pageNumber" value="%{#pagginationPageNumber+1}"/>
        </s:url>
    </s:if>
    <s:else>
        <s:url var="nextPageUrl" value="#"></s:url>
    </s:else>
    request.pagginationTotalPagesCount <s:property value="#request.pagginationTotalPagesCount"/>
    request.pagginationPageNumber <s:property value="#request.pagginationPageNumber"/>
    <s:if test="#request.pagginationPageNumber<#request.pagginationTotalPagesCount">
        <ul class="pagination">assadfsdfsdfdsf
            <li class="page-item"><s:a class="page-link" href="%{#prevPageUrl}"><s:text name="pagination.prevPageText"/></s:a></li>
            <s:if test="%{#request.pagginationTotalPagesCount>6}">
                <s:if test="%{pagginationPageNumber>0 && pagginationPageNumber<4}">                                
                    <s:set  var="endFirstNumber" value="%{pagginationPageNumber==3?4:3}" />
                    <s:set  var="firstLastNumber" value="%{pagginationPageNumber==3?pagginationTotalPagesCount:pagginationTotalPagesCount-1}" />
                    <s:iterator  begin="1" end="%{#endFirstNumber}" var="item">
                        <s:url var="curPageUrl" namespace="/admin/users" action="users-management">
                            <s:param name="locale" value="%{#request.locale}"/>
                            <s:param name="pagginationPageNumber" value="%{#item}"/>
                        </s:url>
                        <s:if test="%{#item==pagginationPageNumber}">
                            <li class="page-item active">
                                <s:a class="page-link" href="%{#curPageUrl}">
                                    <s:property value="top" />
                                </s:a>
                            </li>
                        </s:if>
                        <s:else>
                            <li class="page-item">
                                <s:a class="page-link" href="%{#curPageUrl}">
                                    <s:property value="top" />
                                </s:a>
                            </li>
                        </s:else>                                        
                    </s:iterator>
                    <li class="page-item"><a class="page-link" href="#">..</a></li>
                    <s:iterator  begin="%{#firstLastNumber}" end="%{#pagginationTotalPagesCount}" var="item">
                        <s:url var="curPageUrl" namespace="/admin/users" action="users-management">
                            <s:param name="locale" value="%{#request.locale}"/>
                            <s:param name="pagginationPageNumber" value="%{#item}"/>
                        </s:url>
                        <li class="page-item">
                            <s:a class="page-link" href="%{#curPageUrl}">
                                <s:property value="top" />
                            </s:a>
                        </li>
                    </s:iterator>                                        
                </s:if>
                <s:elseif test="%{pagginationPageNumber>0 && pagginationPageNumber<#pagginationTotalPagesCount-2}">
                    <s:url var="firstPageUrl" namespace="/admin/users" action="users-management">
                        <s:param name="locale" value="%{#request.locale}"/>
                        <s:param name="pageNumber" value="1"/>
                    </s:url>
                    <s:url var="lastPageUrl" namespace="/admin/users" action="users-management">
                        <s:param name="locale" value="%{#request.locale}"/>
                        <s:param name="pageNumber" value="%{#pagginationTotalPagesCount}"/>
                    </s:url>
                    <li class="page-item">
                        <s:a class="page-link" href="%{#firstPageUrl}">1</s:a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">..</a></li>
                    <s:set  var="firstRangeNumber" value="%{#pagginationPageNumber-1}" />
                    <s:set  var="lastRangeNumber" value="%{#pagginationPageNumber+1}" />
                    <s:iterator  begin="%{#firstRangeNumber}" end="%{#lastRangeNumber}" var="item">
                        <s:url var="curPageUrl" namespace="/admin/users" action="users-management">
                            <s:param name="locale" value="%{#request.locale}"/>
                            <s:param name="pageNumber" value="%{#item}"/>
                        </s:url>
                        <s:if test="%{#item==#pagginationPageNumber}">
                            <li class="page-item active">
                                <s:a class="page-link" href="%{#curPageUrl}">
                                    <s:property value="top" />
                                </s:a>
                            </li>
                        </s:if>
                        <s:else>
                            <li class="page-item">
                                <s:a class="page-link" href="%{#curPageUrl}">
                                    <s:property value="top" />
                                </s:a>
                            </li>
                        </s:else>                                        
                    </s:iterator>
                    <li class="page-item"><a class="page-link" href="#">..</a></li>
                    <li class="page-item">
                        <s:a class="page-link" href="%{#lastPageUrl}"><s:property value="%{#pagginationTotalPagesCount}"/></s:a>
                    </li>
                </s:elseif>
                <s:elseif test="%{pagginationPageNumber>=#pagginationTotalPagesCount-2 && pagginationPageNumber<=#pagginationTotalPagesCount}">                                
                    <s:set  var="endFirstNumber" value="%{pagginationPageNumber==pagginationTotalPagesCount-2?1:2}" />
                    <s:set  var="firstLastNumber" value="%{pagginationPageNumber==pagginationTotalPagesCount-2?pagginationTotalPagesCount-3:pagginationTotalPagesCount-2}" />
                    <s:iterator  begin="1" end="%{#endFirstNumber}" var="item">
                        <s:url var="curPageUrl" namespace="/admin/users" action="users-management">
                            <s:param name="locale" value="%{#request.locale}"/>
                            <s:param name="pageNumber" value="%{#item}"/>
                        </s:url>
                        <li class="page-item">
                            <s:a class="page-link" href="%{#curPageUrl}">
                                <s:property value="top" />
                            </s:a>
                        </li>                                       
                    </s:iterator>
                    <li class="page-item"><a class="page-link" href="#">..</a></li>
                    <s:iterator  begin="%{#firstLastNumber}" end="%{#pagginationTotalPagesCount}" var="item">
                        <s:url var="curPageUrl" namespace="/admin/users" action="users-management">
                            <s:param name="locale" value="%{#request.locale}"/>
                            <s:param name="pagginationPageNumber" value="%{#item}"/>
                        </s:url>
                        <s:if test="%{#item==pagginationPageNumber}">
                            <li class="page-item active">
                                <s:a class="page-link" href="%{#curPageUrl}">
                                    <s:property value="top" />
                                </s:a>
                            </li>
                        </s:if>
                        <s:else>
                            <li class="page-item">
                                <s:a class="page-link" href="%{#curPageUrl}">
                                    <s:property value="top" />
                                </s:a>
                            </li>
                        </s:else>    
                    </s:iterator>                                        
                </s:elseif>
            </s:if>
            <s:else>
                SDF
                <s:iterator  begin="1" end="%{#request.pagginationTotalPagesCount}" var="item">
                    <s:url var="curPageUrl" namespace="/admin/users" action="users-management">
                        <s:param name="locale" value="%{#request.locale}"/>
                        <s:param name="pagginationPageNumber" value="%{#item}"/>
                    </s:url>
                    <s:if test="%{#item==pagginationPageNumber}">
                        <li class="page-item active">
                            <s:a class="page-link" href="%{#curPageUrl}">
                                <s:property value="top" />
                            </s:a>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="page-item">
                            <s:a class="page-link" href="%{#curPageUrl}">
                                <s:property value="top" />
                            </s:a>
                        </li>
                    </s:else>                                         
                </s:iterator>
            </s:else>
            <li class="page-item"><s:a class="page-link" href="%{#nextPageUrl}"><s:text name="pagination.nextPageText"/></s:a></li>
        </ul>
    </s:if>
</s:i18n> 
                       

