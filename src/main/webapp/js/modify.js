$(document).ready(function(){
    $(".modify_container").hide();
    setTimeout(function(){
        $(".modify_container").slideDown(500);
    },1000);
    $(".modify_container input").focus(function(){
        $(this).css("outline","#000000 solid 1px");
    });
    $(".modify_container input").blur(function(){
        $(this).css("outline","");
    });
    $(".modify_container button").mouseover(function(){
        $(this).text("전송");
    }).mouseout(function(){
        $(this).text("Click");
    });
});