

$(document).ready(function($){
    $("#navigation").menu();
    console.log($("#menu").attr("class"));
    //navigationComponents.initNavigationComponents();
});

var navigationComponents = {
    initNavigationComponents: function(){
        $('nav > ul.nav').on('click', 'a', function(e){
            if ($(this).hasClass('nav-dropdown-toggle')) {
                    $(this).parent().removeClass('nt').toggleClass('open');
            }

        });
        $(".nav-link.dropdown-toggle").on('click',function(e){
            if ($(this).hasClass('dropdown-toggle')) {
                    $(this).parent().toggleClass('open');
            }

        });
    }
}

