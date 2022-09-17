$(document).ready(function(){
    $(".write_container").hide();
    setTimeout(function(){
        $(".write_container").slideDown(500);
    },1000);
    $(".write_container input").focus(function(){
        $(this).css("outline","#000000 solid 1px");
    });
    $(".write_container input").blur(function(){
        $(this).css("outline","");
    });
    $(".write_container button").mouseover(function(){
        $(this).text("전송");
    }).mouseout(function(){
        $(this).text("Click");
    });
});