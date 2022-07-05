
/**
    문서 load시 세팅된다.
*/
$(document).ready(function(){

     $("#btnBio").click(function(){

            console.log("앱 스키마 호출");

            var plan_no = "108018100520017";
            var cust_no ="1";
            var age= "40";
            var sex = "1";


            var url = "";
            url += "dongbuins://aerox";
            url += "?serviceID= "+ plan_no;
            url += "&custNo ="+ cust_no;
            url += "&serverPublicKeyVer =";
            url += "&serverPublicKey =";
            url += "&ins_plhd_dvn =";
            url += "&ment ="
            url += "&userAge =" + age;
            url += "&userSex ="+sex;
            url += "&BIO_SIG_TOKEN_NO = testToken";

            setTimeout(function(){
                location.href = url;
            },0);


        });

    $("#btnClose").click(function(){
        window.close();
    });
});

