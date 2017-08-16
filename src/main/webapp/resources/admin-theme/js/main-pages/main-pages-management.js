$(document).ready(function($){
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    $(".controls > [data-usergroup-id]").each(function(){ 
            $(this).asyncButton({
                url: "/admin/main-pages/remove-main-page.json?locale="+locale,
                data: "mainPageId="+$(this).data("main-page-id"),
                afterSuccessResponse:function(){
                    console.log("my afterSuccessResponse");
                    location.reload();
                }
            });
        }
    );
});


