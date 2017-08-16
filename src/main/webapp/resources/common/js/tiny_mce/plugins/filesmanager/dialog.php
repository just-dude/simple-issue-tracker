<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Прикрепление файла.</title>
 	<script type="text/javascript" src="../../../../plugins/datetimepicker/js/jquery.js"></script>
	<script type="text/javascript" src="../../../../js/jquery.form.js"></script>
 	<script type="text/javascript" src="../../tiny_mce_popup.js"></script>
	<script type="text/javascript" src="../../utils/form_utils.js"></script>
	<script type="text/javascript" src="./js/dialog.js"></script>
	<link type="text/css" href="css/dialog.css" rel="stylesheet" />
</head>
<body>
<div id='result'>
	<div id='resWrapper'>
		<p id='resTitle'>Результат : </p>
		<p id='resMsg'></p>
	</div>
	<div id='progress_bar'>
		<p id='progressTitle'>Идет загрузка.. 0%</p>
		<div id="progress">
			<div id="bar"></div >
		</div>
	</div>
</div>
	<form id="fileUploadForm" name="fileUploadForm"  enctype="multipart/form-dataMap" method="post">
			<input id="file" type="file" name="file" style="display: none" >
	</form>
	<form name="fileAttributesForm" onsubmit="mcefilesmanager.insert();return false;" action="#">
		<p id="fileNameTitle" >Имя файла:</p>
		<input id="fileName" name="fileName" type="text" class="text" size=40 />
		<label class="file_upload" for="file">
			<img src="./img/upload.png" style="width: 18px; margin-left: 10px; cursor: pointer;" >
		</label>
		<p id="fileURLTitle" >URL:</p>
		<input id="fileURL" name="fileURL" type="text" class="text" size=40 />
		<p id="isSimpleReferenceTitle" >Обычная ссылка : <input id="isSimpleReference" name="isSimpleReference" type="checkbox"  /></p>

		<div class="mceActionPanel">
			<input type="button" id="insert" name="insert" value="{#insert}" onclick="FilesManagerDialog.insert();" >
			<input type="button" id="cancel" name="cancel" value="{#cancel}" onclick="tinyMCEPopup.close();" />
		</div>
		
	</form>
 
</body>
</html>