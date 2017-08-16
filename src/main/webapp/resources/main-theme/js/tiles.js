$(document).ready(function($){
    $('.tile').each(
        function(){
            $(this).bind('mouseenter',function(e){
                var current = $(e.currentTarget);
                current.children('.tile_wraper').stop(true,false);
                current.children('.tile_wraper').animate({marginTop: "-181px"}, 400);
            });
        }
    );
    $('.tile').each(
        function(){
            $(this).bind('mouseleave',function(e){
                var current = $(e.currentTarget);
                current.children('.tile_wraper').stop(true,false);
                var marg=current.children('.tile_wraper').css('marginTop');
                marg=marg.substr(0,marg.length-2);
                current.children('.tile_wraper').animate({marginTop: "0px"}, 300);
                if (marg<-10)
                {
                        current.children('.tile_wraper').animate({marginTop: "-18px"}, 200);
                        current.children('.tile_wraper').animate({marginTop: "0px"}, 200);
                        current.children('.tile_wraper').animate({marginTop: "-8px"}, 100);
                        current.children('.tile_wraper').animate({marginTop: "0px"}, 100);
                }
            });
        }
    );
});

