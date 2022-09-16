$(document).ready(function(){
    $(".trash").mouseover(function(){
        $(this).addClass("vibration");
    }).mouseout(function(){
        $(this).removeClass("vibration");
    });

    $(".todo_list li p a:first-child").mouseover(function(){
        $("img",this).addClass("vibration");
    }).mouseout(function(){
        $("img",this).removeClass("vibration");
    });

    $(".board_list2").hide();
    var list_click = $(".board_list1").click(function(){
        $(this).next().fadeToggle();
    });

    $(".nav li").mouseover(function(){
        $(this).css("color","#333333");
    }).mouseout(function(){
       $(this).css("color","");
    });

    $(".add_menu li a").mouseover(function(){
       $(this).css("color","red");
    }).mouseout(function(){
        $(this).css("color","");
    });

    $(".btn_1").click(function(){
        if($(".content").attr("readonly")==null){
            // $(this).attr("href",content);
            return true;
        }

        $(".content").removeAttr("readonly");
        $(this).html("전송");
        return false;
    });

    $(".btn_2").mouseover(function(){
       $(this).css("color","#333333");
    }).mouseout(function(){
        $(this).css("color","");
    });
});