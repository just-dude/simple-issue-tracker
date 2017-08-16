$(document).ready(function($){
    var form = $("#addMainPageForm");
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    //form.validate(addMainPageFormHandlersOptions.validationOptions);
    tinymce.init({ selector:'textarea',height: 300 });
    var newFormAction = form.attr("action").replace(/\.html/,".json")+"?locale="+locale;
    form.attr("action",newFormAction);
    form.asyncForm(addMainPageFormHandlersOptions.asyncFormOptions);
});
var localMessages = i18n.messages[utils.getShortLocaleOfPage()];

var addMainPageFormHandlersOptions = {
     
    validationOptions:{
        rules: {
            "mainPage.page.name": {
                    required: true,
                    maxlength: 100,
                    pattern: "[а-яёЁА-ЯA-Za-z\\-\\s]+"
            },
            "mainPage.page.title": {
                    required: true,
                    maxlength: 256,
                    maxlength: "[а-яёЁА-ЯA-Za-z\\-\\s]+"                            
            },
            "mainPage.page.content": {
                    required: true,
                    maxlength: 65536                        
            }
        },
        messages: {
            "mainPage.page.name": {
                    required: localMessages.required,
                    maxlength: localMessages.maxlength,
                    pattern: localMessages.commonNamePattern
            },
            "mainPage.page.title": {
                    required: localMessages.required,
                    maxlength: localMessages.maxlength,                          
            },
            "mainPage.page.content": {
                    required: localMessages.required,
                    maxlength: localMessages.maxlength
            }
        },
        errorPlacement: function ( error, element ) {
            error.addClass( "clientSideValidationError" );
            if ( element.prop( "type" ) === "checkbox" ) {
                    error.insertAfter( element.parent( "label" ) );
            } else {
                    error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( "clientSideInvalidFieldValue" ).removeClass("clientSideValidFieldValue").removeClass( "clientSideUnvalidatedFormField" );
        },
        unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( "clientSideValidFieldValue" ).removeClass( "clientSideInvalidFieldValue" ).removeClass( "clientSideUnvalidatedFormField" );
        }
    },
    asyncFormOptions:{
        afterSuccessResponse:function(){
            $("#addMainPageForm")[0].reset();
            $(".serverSideValidationError").hide();
        }
    }
}
 



