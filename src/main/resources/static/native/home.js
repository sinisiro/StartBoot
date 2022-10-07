
/**
    문서 load시 세팅된다.
*/
$(document).ready(function(){

    $("#btnNative").click(function(){
        $("#form01").attr("action","/native/init");
          form01.submit();
    });

    $("#btnAppCall").click(function(){
        alert("이동 호출");
        $("#form01").attr("action","/native/appCall");
          form01.submit();
    });

});

