(function() {

	tinymce.create('tinymce.plugins.FilesManager', {

   		// инициализируем плагин 
   		// ed - экземляр редактора 
   		// url - абсолютный url плагина 
		init : function(ed, url) {

   			// Регестрирум комаду, для открытия окна плагина
    		ed.addCommand('mcefilesmanager', function() {

    			//  параметры окна
    			ed.windowManager.open({
     				file: url + '/dialog.php',
    				width: 400 + ed.getLang('filesmanagerlink.delta_width', 0),
     				height: 270+ ed.getLang('filesmanagerlink.delta_height', 0),
     				inline: 1
    			}, 
				{

     				// данные для плагина - его абсолютный URL
     				plugin_url : url

    			});
   			});
 
   			// регестрируем иконку плагина
   			ed.addButton('filesmanagerlink', {
    			title: 'Вставка файла',
    			cmd: 'mcefilesmanager',
    			image: url + '/img/fileup.ico'
   			});
  		},


  		// "Выходные данные" плагина
  		getInfo : function() {
   			return {
    			longname : 'Files Uploader',
    			author: 'stupid',
   				authorurl: '',
    			version: "0.001"
   			};
  		}
 	});


 	// Регестрируем плагин
   	tinymce.PluginManager.add('filesmanager', tinymce.plugins.FilesManager);
})();