<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <tiles:insertAttribute name="commonAssets" />
        <tiles:insertAttribute name="customAssets" />
    </head>

    <body> 
        <div id="header" class="navbar-fixed sidebar-nav fixed-nav">
              <tiles:insertAttribute name="header" />
        </div>
        <div class="container min-height-1000">
            <div class="row">
                <div id="menu" class="col-lg-6 col-md-7">
                      <tiles:insertAttribute name="menu" />
                </div>
                <div id="content" class="col-lg-24 col-md-22">
                      <tiles:insertAttribute name="content" />
                </div>
                <div id="pressBar" class="col-lg-6 col-md-7">
                      <tiles:insertAttribute name="pressBar" />
                </div>
            </div>
        </div>
        <div id="footer">
              <tiles:insertAttribute name="footer" />
        </div>
    </body>
</html>


