tinyMCEPopup.requireLangPack();
 
var FilesManagerDialog = {

   // Инициализируем наш плагин	
 
   init : function() {
		var qs = new QueryString(window.parent.document.location.search);
	    var section= qs.value('section');
		var page_id=window.parent.document.getElementById('page_id').value;
	    console.log(page_id);
		 console.log(123);
		$('#fileUploadForm').ajaxForm({
			beforeSend: FilesManager.Before_Send_File,
			uploadProgress: FilesManager.File_Upload_Progress,
			complete:  FilesManager.File_Complete,
			resetForm: true,
			url: '../../../../php/text.files.uploader.php?section='+section+'&page_id='+page_id
		});
		$('#file').bind('change',function(){ $('#fileUploadForm').submit(); });
   },

   // Функция обработки и вставки ссылки
   insert : function() {
		var fileURL = document.getElementById('fileURL').value;
		var fileName = document.getElementById('fileName').value;
		if(fileName.length!=0 && fileURL.length!=0)
		{
			var isSimpleReference=document.getElementById('isSimpleReference').checked;
			if (!isSimpleReference)
			{
				var fileType=this.getFileType(this.getFileExtension(fileURL));
				var resultRef = "&nbsp;<span target='_blank' style='color: #778077;padding: 5px 0px;'>[&nbsp;Файл :&nbsp;<a class=\"" + fileType + "\" href=\"";
				resultRef+= fileURL + "\">" + fileName + "</a>&nbsp;]</span> ";
				console.log('not on ');
			}
			else
				resultRef="<a  href=\""+fileURL+"\">"+fileName+"</a>";
			tinyMCEPopup.editor.execCommand('mceInsertContent', false, resultRef);
			tinyMCEPopup.close();
		}
    },
	
	getFileType : function(ext) {
		console.log(ext);
		var fileType=null;
		var exts={
							tables  :  new Array('xls','xlsx','ods','ots'),
							texts  :  new Array('txt'),
							documents  :  new Array ('doc','docx','rtf','odt','ott'),
							pdf_files  :  new Array ('pdf'),
							ppt_files  : new Array ('ppt','pptx','odp','otp'),
							archives  :  new Array ('rar','zip')
						};
		for(var type in exts)
			for(var i in exts[type])
				if (ext==exts[type][i])
					fileType=type;
		return fileType;
	},
	
	getFileExtension : function(file) {
		return file.substr(file.lastIndexOf('.')+1);
	}

};

var FilesManager= {
	
	Before_Send_File : function()
	{
		$('#progress_bar').css({display: 'block'});
		$('#resWrapper').css({display: 'none'});
		$('#bar').width('0%');
		$('#progressTitle').html('Идет загрузка.. 0%');
	},

	File_Upload_Progress : function(event,position,total,percentComplete)
	{
		var percentVal = percentComplete + '%';
		$('#bar').width(percentVal);
		$('#progressTitle').html('Идет загрузка.. '+percentVal);
	},
	
	File_Complete : function(xhr)
	{
		$('#progress_bar').css({display: 'none'});
		$('#resWrapper').css({display: 'block'});
		var msg = xhr.responseText;
		var errPos = msg.indexOf('error:');
		var allRightPos = msg.indexOf('all right files :');
		console.log(msg);
		if (allRightPos>-1 && allRightPos<5)
		{	
			var result=jQuery.parseJSON(msg.substr(allRightPos+17));
			var fileName;
			var filePath;
			for(fileName in result)
				filePath=result[fileName];
			var str='<p id="success">Файл  : </br>'+fileName+' успешно загружен</p>';
			$('#resMsg').html(str);
			document.getElementById('fileURL').value=filePath;
			document.getElementById('fileName').value=fileName;
		}else if(errPos>-1 && errPos<5)
		{
			var result=jQuery.parseJSON(msg.substr(errPos+6));
			var str='<p id="error">Ошибка !!!</br>'+FilesManager.ObjToStr(result)+'</p>';
			$('#resMsg').html(str);
		}
		else
		{
			var str='<p id="error">Произошла неизвестая ошибка, обратитесь к администратору</p>';
			$('#resMsg').html(str);
		}
		console.log(result);
		console.log(errPos);
		console.log(allRightPos);
	
	},
	
	ObjToStr : function(obj)
	{	
		var str="";
		if (typeof obj == 'object' || typeof obj == 'array')
			for(var prop in obj)
				return "</br>"+prop+" : "+FilesManager.ObjToStr(obj[prop])+"</br>";
		else
				return obj;	
	},
	
	getPropValues : function(obj)
	{	
		var str="";
		for(var prop in obj)
			str+=prop+" , </br>";
		return str.substring(0,str.length-7);
	}
}

tinyMCEPopup.onInit.add(FilesManagerDialog.init, FilesManagerDialog);



QueryString = function (qs)
{
    this.dict= {};

    // If no query string  was passed in use the one from the current page
    if (!qs) qs= location.search;

    // Delete leading question mark, if there is one
    if (qs.charAt(0) == '?') qs= qs.substring(1);

    // Parse it
    var re= /([^=&]+)(=([^&]*))?/g;
    while (match= re.exec(qs))
    {
        var key= decodeURIComponent(match[1].replace(/\+/g,' '));
        var value= match[3] ? QueryString.decode(match[3]) : '';
        if (this.dict[key])
            this.dict[key].push(value);
        else
            this.dict[key]= [value];
    }
},

QueryString.decode = function(s)
{
    s= s.replace(/\+/g,' ');
    s= s.replace(/%([EF][0-9A-F])%([89AB][0-9A-F])%([89AB][0-9A-F])/gi,
        function(code,hex1,hex2,hex3)
        {
            var n1= parseInt(hex1,16)-0xE0;
            var n2= parseInt(hex2,16)-0x80;
            if (n1 == 0 && n2 < 32) return code;
            var n3= parseInt(hex3,16)-0x80;
            var n= (n1<<12) + (n2<<6) + n3;
            if (n > 0xFFFF) return code;
            return String.fromCharCode(n);
        });
    s= s.replace(/%([CD][0-9A-F])%([89AB][0-9A-F])/gi,
        function(code,hex1,hex2)
        {
            var n1= parseInt(hex1,16)-0xC0;
            if (n1 < 2) return code;
            var n2= parseInt(hex2,16)-0x80;
            return String.fromCharCode((n1<<6)+n2);
        });
    s= s.replace(/%([0-7][0-9A-F])/gi,
        function(code,hex)
        {
            return String.fromCharCode(parseInt(hex,16));
        });
    return s;
},

QueryString.prototype.value= function (key)
{
    var a= this.dict[key];
    return a ? a[a.length-1] : undefined;
},

QueryString.prototype.values= function (key)
{
    var a= this.dict[key];
    return a ? a : [];
},

QueryString.prototype.keys= function ()
{
    var a= [];
    for (var key in this.dict)
        a.push(key);
    return a;
}

