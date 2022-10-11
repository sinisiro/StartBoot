
/**
    문서 load시 세팅된다.
*/
$(document).ready(function(){


    alert("화면시작했음666");
    var plan_no = "108018100520017";
    var cust_no ="1";
    var age= "40";
    var sex = "1";
     var url = "";
    url += "dongbusign://aerox";
    url += "?serviceID="+ plan_no;
    url += "&custNo="+ cust_no;
    url += "&serverPublicKeyVer=";
    url += "&serverPublicKey=";
    url += "&ins_plhd_dvn=";
    url += "&dvcd=2";
    url += "&ment ="
    url += "&userAge=" + age;
    url += "&userSex="+sex;
    url += "&BIO_SIG_TOKEN_NO=testTokens";

//    location.href = url;
    setTimeout(function(){
//        $("#btn_appCall").attr("href", url)[0].click();
        //안드로이드의 경우, 앱으로 이동하면 웹페이지 이동한거를 무시해버린다.(나가버림).
//        $("#form01").attr("action","/native/endPage");
//          form01.submit();
    },0);




    $("#btnBio").click(function(){
            alert("호출");
            console.log("앱 스키마 호출!!!!");

            setTimeout(function(){
            //22.07 iOS 카카오 인앱에서 외부 앱 호출하면 인앱닫히는 현상 방지하기위함.
                $("#btn_appCall").attr("href", url)[0].click();
                $("#form01").attr("action","/native/endPage");
                form01.submit();
            },0);
        });

    $("#btnClose").click(function(){
        window.close();
    });

});

//window.onload = function(){

function exeApp(){
//    alert("load시작했음2");
  var plan_no = "108018100520017";
    var cust_no ="1";

    var age= "40";
    var sex = "1";
     var url = "";
    url += "dongbusign://aerox";
    url += "?serviceID="+ plan_no;
    url += "&custNo="+ cust_no;
    url += "&serverPublicKeyVer=";
    url += "&serverPublicKey=";
    url += "&ins_plhd_dvn=";
    url += "&dvcd=2";
    url += "&ment ="
    url += "&userAge=" + age;
    url += "&userSex="+sex;
    url += "&BIO_SIG_TOKEN_NO=testTokens";



    setTimeout(function(){
//        $("#btn_appCall").attr("href", url)[0].click();
    },0);
}

