$(document).ready(function(){
    $(".register_container").hide();
    setTimeout(function(){
        $(".register_container").slideDown(500);
    },1000);
    $(".register_container input").focus(function(){
        $(this).css("outline","#000000 solid 1px");
    });
    $(".register_container input").blur(function(){
        $(this).css("outline","");
    });
    $(".register_container button").mouseover(function(){
        $(this).text("전송");
    }).mouseout(function(){
        $(this).text("Click");
    });
    $("#input_check").click(function(){
        var checked = $("#input_check").prop("checked");
        if(checked){
            $(".isFinished").text("Done");
        }
        else{
            $(".isFinished").text("Yet");
        }
    })
});