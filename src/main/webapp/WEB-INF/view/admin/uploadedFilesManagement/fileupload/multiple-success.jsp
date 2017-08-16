<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Struts2 Showcase - Fileupload sample</title>
</head>

<body>
<div class="page-header">
	<h1>Fileupload sample</h1>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<ul>
		       <!-- <li>ContentType: "uploadContentType"</li>
		        <li>FileName: "uploadFileName" </li>
		        <li>File: property value="upload" /></li>
		        <li>Caption:property value="caption" /></li>-->
                       <p>Uploads: </p>
                        <s:iterator value="upload" status="stat">
                            <p>
                                <s:property value="#stat.count" />. File
                                <ul>
                                    <li>File path: <s:property value="upload[#stat.count-1]" /></li>
                                    <li>Old file name: <s:property value="uploadFileName[#stat.count-1]" /></li>
                                    <li>Content type: <s:property value="uploadContentType[#stat.count-1]" /></li>
                                </ul>
                            </p>
                        </s:iterator>
                        
	        </ul>
		</div>
	</div>
</div>

</body>
</html>

