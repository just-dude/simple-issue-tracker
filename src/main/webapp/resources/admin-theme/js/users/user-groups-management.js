$(document).ready(function($){
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    $(".controls > [data-usergroup-id]").each(function(){ 
            $(this).asyncButton({
                url: "/admin/users/remove-usergroup.json?locale="+locale,
                data: "userGroupId="+$(this).data("usergroup-id"),
                afterSuccessResponse:function(){
                    console.log("my afterSuccessResponse");
                    location.reload();
                }
            });
        }
    );
});


