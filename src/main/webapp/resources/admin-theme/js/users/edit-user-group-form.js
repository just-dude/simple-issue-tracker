$(document).ready(function($){
    var form = $("#editUserGroupForm");
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    form.validate(editUserGroupFormHandlersOptions.validationOptions);
    var newFormAction = form.attr("action").replace(/\.html/,".json")+"?locale="+locale;
    form.attr("action",newFormAction);
    form.asyncForm();
});
var localMessages = i18n.messages[utils.getShortLocaleOfPage()];

var editUserGroupFormHandlersOptions = {
     
    validationOptions:{
         rules: {
            "userGroup.name": {
                    required: true,
                    maxlength: 100,
                    pattern: "[а-яёЁА-ЯA-Za-z\\-\\s]+"
            }
        },
        messages: {
            "userGroup.name": {
                    required: localMessages.required,
                    maxlength: localMessages.maxlength,
                    pattern: localMessages.loginPattern
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
    
    /*asyncFormOptions:{
        prepareForm: function(){
            console.log("other prepare");
        },
        afterSuccessResponse:function(){
            console.log("my afterSuccessResponse");
            $("#addUserForm")[0].reset();
        }
    }*/
    
}
 



