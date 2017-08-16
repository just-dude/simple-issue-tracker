/**
 * Justboil.me - a TinyMCE image upload plugin
 * jbimages/js/dialog.js
 *
 * Released under Creative Commons Attribution 3.0 Unported License
 *
 * License: http://creativecommons.org/licenses/by/3.0/
 * Plugin info: http://justboil.me/
 * Author: Viktor Kuzhelnyi
 *
 * Version: 2.3 released 23/06/2013
 */

 tinyMCEPopup.requireLangPack();

var jbImagesDialog = {
	
	resized : false,
	iframeOpened : false,
	timeoutStore : false,
	
	init : function() {
		document.getElementById("upload_target").src += '/' + tinyMCEPopup.getLang('jbimages_dlg.lang_id', 'english');
		qs= new QueryString();
	    var str= qs.value('section');
		var val =document.getElementById("upload_target").src;
		var pos =val.indexOf('.php')+4;
		document.getElementById("upload_target").src = val.substr(0,pos)  +val.substr(pos)+"?section="+ str;
		val =document.getElementById('upl').attributes.action.value;
		pos =val.indexOf('.php')+4;
		document.getElementById('upl').attributes.action.value = val.substr(0,pos) +val.substr(pos)+"?section="+ str;
		if (navigator.userAgent.indexOf('Opera') > -1)
		{
			document.getElementById("close_link").style.display = 'block';
		}
	},
	
	inProgress : function() {
		document.getElementById("upload_infobar").style.display = 'none';
		document.getElementById("upload_additional_info").innerHTML = '';
		document.getElementById("upload_form_container").style.display = 'none';
		document.getElementById("upload_in_progress").style.display = 'block';
		this.timeoutStore = window.setTimeout(function(){
			document.getElementById("upload_additional_info").innerHTML = tinyMCEPopup.getLang('jbimages_dlg.longer_than_usual', 0) + '<br />' + tinyMCEPopup.getLang('jbimages_dlg.maybe_an_error', 0) + '<br /><a href="#" onClick="jbImagesDialog.showIframe()">' + tinyMCEPopup.getLang('jbimages_dlg.view_output', 0) + '</a>';
			tinyMCEPopup.editor.windowManager.resizeBy(0, 30, tinyMCEPopup.id);
		}, 20000);
	},
	
	showIframe : function() {
		if (this.iframeOpened == false)
		{
			document.getElementById("upload_target").className = 'upload_target_visible';
			tinyMCEPopup.editor.windowManager.resizeBy(0, 190, tinyMCEPopup.id);
			this.iframeOpened = true;
		}
	},
	
	uploadFinish : function(result) {
		if (result.resultCode == 'failed')
		{
			window.clearTimeout(this.timeoutStore);
			document.getElementById("upload_in_progress").style.display = 'none';
			document.getElementById("upload_infobar").style.display = 'block';
			document.getElementById("upload_infobar").innerHTML = result.result;
			document.getElementById("upload_form_container").style.display = 'block';
			
			if (this.resized == false)
			{
				tinyMCEPopup.editor.windowManager.resizeBy(0, 30, tinyMCEPopup.id);
				this.resized = true;
			}
		}
		else
		{
			document.getElementById("upload_in_progress").style.display = 'none';
			document.getElementById("upload_infobar").style.display = 'block';
			document.getElementById("upload_infobar").innerHTML = tinyMCEPopup.getLang('jbimages_dlg.upload_complete', 0);
			tinyMCEPopup.editor.execCommand('mceInsertContent', false, '<img src="' + result.filename +'" />');
			tinyMCEPopup.close();
		}
	}

};

tinyMCEPopup.onInit.add(jbImagesDialog.init, jbImagesDialog);

function QueryString(qs)
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
}

QueryString.decode= function(s)
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
};

QueryString.prototype.value= function (key)
{
    var a= this.dict[key];
    return a ? a[a.length-1] : undefined;
};

QueryString.prototype.values= function (key)
{
    var a= this.dict[key];
    return a ? a : [];
};

QueryString.prototype.keys= function ()
{
    var a= [];
    for (var key in this.dict)
        a.push(key);
    return a;
};
