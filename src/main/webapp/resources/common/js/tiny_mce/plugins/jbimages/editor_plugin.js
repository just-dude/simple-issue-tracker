(function(){tinymce.PluginManager.requireLangPack('jbimages');tinymce.create('tinymce.plugins.jbImagesPlugin',{init:function(ed,url){ed.addCommand('jbImages',function(){var unixtime_ms=new Date().getTime(); qs= new QueryString(); var str= qs.value('section'); ed.windowManager.open({file:url+'/dialog.htm?z'+unixtime_ms+"&section="+str,width:330+parseInt(ed.getLang('jbimages.delta_width',0)),height:155+parseInt(ed.getLang('jbimages.delta_height',0)),inline:1},{plugin_url:url})});ed.addButton('jbimages',{title:'Загрузка изображения с компьютера',cmd:'jbImages',image:url+'/img/jbimages-bw.gif'});ed.onNodeChange.add(function(ed,cm,n){cm.setActive('jbimages',n.nodeName=='IMG')})},createControl:function(n,cm){return null},getInfo:function(){return{longname:'JustBoil.me Images Plugin',author:'Viktor Kuzhelnyi',authorurl:'http://justboil.me/',infourl:'http://justboil.me/',version:"2.3"}}});tinymce.PluginManager.add('jbimages',tinymce.plugins.jbImagesPlugin)})();

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
