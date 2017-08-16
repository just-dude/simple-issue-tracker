$(document).ready(function($){
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    $(".controls > [data-user-id]").each(function(){ 
            $(this).asyncButton({
                url: "/admin/users/remove-user.json?locale="+locale,
                data: "userId="+$(this).data("user-id"),
                afterSuccessResponse:function(){
                    console.log("my afterSuccessResponse");
                    location.reload();
                }
            });
        }
    );
    //form.asyncForm(addUserFormHandlersO ptions.asyncFormOptions);
});


