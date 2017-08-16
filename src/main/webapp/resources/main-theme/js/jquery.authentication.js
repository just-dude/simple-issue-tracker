(function( $ ){
    
    $.fn.authentication = function(options) {
        
        var formDialogContent = formDialogContent;
        
         var utils = {            
            createDialog: function(dialogContent,dialogOptions,afterCloseCalback){
                dialogContent.dialog({
                    autoOpen: false,
                    modal: true,
                    draggable: false,
                    resizable: false,
                    width: dialogOptions.modalFormWidth,
                    close: function( event, ui ) {
                        afterCloseCalback();
                    }                            
                });             
            }                       
        };
        
        var defaultSettings = {
            locale: "ru",
            modalFormWidth: 520,
            formDialogContent: $("#authentication-container")
        };
        

        var settings = $.extend( defaultSettings, options);   
        utils.createDialog(settings.formDialogContent,{modalFormWidth: settings.modalFormWidth},function(){});
        
        var form = settings.formDialogContent.find("#loginForm");
        var newFormAction = form.attr("action").replace(/\.html/,".json")+"?locale="+settings.locale;
        form.attr("action",newFormAction);
        form.asyncForm({
            viewProcessedRequestResultMessage: function(status,message,afterViewProcessedRequestResultMessage){ 
                if(status!="Success"){
                    $("#login-form-errors").text(message);
                    form.find("input").on("change",function(){$("#login-form-errors").text("");});
                } else{
                    $("#success-login-message").text(message);
                    $("#auth-block-container").hide();
                    $("#success-login-container").show();
                }
                
                afterViewProcessedRequestResultMessage();
                console.log("viewProcessedRequestResultMessage my message!!!!");
            },
            
            afterSuccessResponse:function(){
                settings.formDialogContent.one( "dialogclose", function( event, ui ) {location.reload()});
            }
        });
        $("#logout-button").asyncButton({
            url: "/authentication/logout.json?locale="+settings.locale,
            viewProcessedRequestResultMessage: function(status,message,afterViewProcessedRequestResultMessage){
                if(status!="Success"){
                    $("#logout-response-errors").text(message);
                } else{
                    $("#success-login-message").text(message);
                    $("#success-login-container").hide();
                    $("#auth-block-container").show();
                }
                afterViewProcessedRequestResultMessage();
            },
            afterSuccessResponse:function(){
                console.log("my afterSuccessResponse");
                //location.reload();
            }
        });
        this.click(function(){
            settings.formDialogContent.dialog("open");
        });
    };
})( jQuery );


