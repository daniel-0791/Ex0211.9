$(document).ready(function(){
    $("#name").focus(function(){
        //  alert();
        $(this).css("background-color","#F9D16B");
    });

    $("#name").blur(function(){//用户名文本框失去焦点触发验证事件
        $(this).css("background-color","#FFF");
        if(!$(this).val()||!(/^[A-Za-z0-9_]{6,15}$/).test($(this).val())){
            $("#nameTip").html("用户名不能为空且只能为英文或者数字,长度为4-15");
        }else{
            $("#nameTip").html("输入正确");
        }

    });

});