<?php
  
	$rel_path= '../photo/'.$_GET['section'].'/temp_images/';
	$phys_path=$_SERVER['DOCUMENT_ROOT'].'/photo/'.$_GET['section'].'/temp_images/';

	define(RELATIVE_PATH, $rel_path);
	define(PHYSICAL_PATH,  $phys_path);
?>
