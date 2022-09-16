$(document).ready(function(){
    $(".login_container").hide();
    setTimeout(function(){
        $(".login_container").slideDown(500);
    },1000);
    $(".login_container input").focus(function(){
        $(this).css("outline","#000000 solid 1px");
    });
    $(".login_container input").blur(function(){
        $(this).css("outline","");
    });
    $(".login_container button").mouseover(function(){
        $(this).text("전송");
    }).mouseout(function(){
        $(this).text("Click");
    });
});