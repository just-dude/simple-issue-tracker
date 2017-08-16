$(document).ready(function($){
    var form = $("#editUserForm");
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    $.datepicker.setDefaults($.datepicker.regional[ locale]);
    $("#dateOfBirth").datepicker({dateFormat: "dd.mm.yy"});
    form.validate(editUserFormHandlersOptions.validationOptions);
    var newFormAction = form.attr("action").replace(/\.html/,".json")+"?locale="+locale;
    form.attr("action",newFormAction);
    form.asyncForm();
});
var localMessages = i18n.messages[utils.getShortLocaleOfPage()];

var editUserFormHandlersOptions = {
     
    validationOptions:{
        rules: {
            "user.userAccountChangedData.oldPassword": {
                    required: false,
                    minlength: 10,
                    maxlength: 255  
            },
            "user.userAccountChangedData.password": {
                    required: false,
                    minlength: 10,
                    maxlength: 255                            
            },
            "repeatPassword": {
                    equalTo: "#newPassword",
                    required: false,
                    maxlength: 255
            },
            "user.userProfile.name": {
                required: true,
                maxlength: 100,
                pattern: "[а-яёЁА-ЯA-Za-z\\-\\s]+"
            },
            "user.userProfile.surname": {
                required: true,
                maxlength: 100,
                pattern: "[а-яёЁА-ЯA-Za-z\\-\\s]+"
            },
            "user.userProfile.patronymic": {
                maxlength: 100,                
                pattern: "[а-яёЁА-ЯA-Za-z\\-\\s]+"
            },
            "user.userProfile.dateOfBirth": {
                required: true,
                dateRU: true,
                minDateRU: new Date(1100,1,2),
                maxDateRU: new Date()
            },
            "user.userProfile.email":{
                required: true,
                customEmail: true,
                maxlength: 255
            }
        },
        messages: {
            "user.userAccountChangedData.oldPassword": {
                    minlength: localMessages.minlength,                          
                    maxlength: localMessages.maxlength,
            },
            "user.userAccountChangedData.password": {
                    minlength: localMessages.minlength,                          
                    maxlength: localMessages.maxlength,                          
            },
            "repeatPassword": {
                    maxlength: localMessages.maxlength,
                    equalTo: localMessages.repeatPasswordEqualToPassword
            },
            "user.userProfile.name": {
                required: localMessages.required,
                maxlength: localMessages.maxlength,
                pattern: localMessages.commonNamePattern
            },
            "user.userProfile.surname": {
                required: localMessages.required,
                maxlength: localMessages.maxlength,
                pattern: localMessages.commonNamePattern
            },
            "user.userProfile.patronymic": {
                required: localMessages.required,
                maxlength: localMessages.maxlength,
                pattern: localMessages.commonNamePattern
            },
            "user.userProfile.dateOfBirth": {
                required: localMessages.required,
                minDateRU: localMessages.min,
                maxDateRU: localMessages.max,
                dateRU: localMessages.date                    
            },
            "user.userProfile.email":{
                required: localMessages.required,
                customEmail: localMessages.email
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
 



