
/**
    문서 load시 세팅된다.
*/
$(document).ready(function(){
    //Native 호출
    $("#btnNativeCall").click(function(){
        var params = {header : { api: '99'}, body: {callBackFunc:  'callBack'}};
        webkit.messageHandlers.callApp.postMessage(params);
    });

    //캡쳐방지 호출
    $("#btnCapture").click(function(){
        var params = {header : { api: '132'}, body:
            {
            callBackFunc:  'fn_callBackCapture',
            logDate: '20220419',
            logTime: '12000101',
            screenId: 'ServerWebview'
            }
        };
        webkit.messageHandlers.callApp.postMessage(params);
    });

    $("#btnAppInit").click(function(){
        var params = {header : { api: '129'}, body: {callBackFunc:  'noCallback'}};
        webkit.messageHandlers.callApp.postMessage(params);
        alert("초기화 되었습니다");
    });

    $("#btnAppFirst").click(function(){
        var params = {header : { api: '130'}, body: {callBackFunc:  'fn_authCallBack'}};
        webkit.messageHandlers.callApp.postMessage(params);
    });

    //22.09.13 임시폴더 초기화
    $("#btnClearTmp").click(function(){
        alert("hello")
            var params = {header : { api: '133'}, body: {callBackFunc:  'callBack'}};
            webkit.messageHandlers.callApp.postMessage(params);
        });


    $("#btnMove").click(function(){
        $("#form01").attr("action","/native");
          form01.submit();
    });
    //popup 테스트
    $("#btnPopup").click(function(){
        window.open("/native/appCall")
    });

    $("#btnUpload").click(function(){
        $("#form01").attr("method","get");
        $("#form01").attr("action","/test/fileUploadForm");
          form01.submit();
    });

    $("#btnBack").click(function(){
        history.back();
    });

    //member에 초기 들어가있는 자료롤 가져온다.
    $("#btnFetch").click(function(){
        $.post("/native/dbAccess", {
        }, function(data) {
            var json = JSON.stringify(data); //'{ID=1, BIRTH=830101, NAME=맹수}'
            alert(json);
        }, 'json');

    });

//    $("#btnBioMove").click(function(){
//        $("#form01").attr("action","/native/appCall");
//          form01.submit();
//    });


    $("#btnBioMove").click(function(){

            alert("호출");
            console.log("앱 스키마 호출!!!!");

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
            //22.07 iOS 카카오 인앱에서 외부 앱 호출하면 인앱닫히는 현상 방지하기위함.
            $("#btn_appCall").attr("href", url)[0].click();
                location.href = url;
            },0);
        });




//natvie->서버 json 전송 테스트
    $("#btnTest").click(function(){
//        jsonObj = "['logTime': '23010101', 'screenId': 'webViewObservedCapt', 'logDate': '20220418']"
        jsonObj = '{"logDate": "20220418"}';
        Test(jsonObj);

    });


});

function Test(jsonObj){
    console.log(jsonObj);

    var parsedJSONObject = JSON.parse(jsonObj);     //{logDate: '20220418'}
    console.log(parsedJSONObject["logDate"]);

    var json = JSON.stringify(parsedJSONObject); //'{"logDate":"20220418"}'
    console.log(json);

}

function callBack(method){
    alert(method+":callback 완료");
}

function fn_callBackCapture(logDate, logTime, screenId){
//캡쳐 내역 저장하기 서버
      $.post("/native/insertCaptureLog", {
            logDate: logDate,
            logTime: logTime,
            screenId: screenId
        }, function(data) {
              if(data.result_cd == "0"){
//                  alert(data.result);
                 $("#form01").attr("action","/native");
                 form01.submit();
               }
               else{
                  alert(data.result);
               }

        }, 'json');


//    alert("callback 캡쳐 완료");
}

//json으로 전송
function fn_callBackCapture2(data){

    var parsedJSONObject = JSON.parse(data);

//    alert(parsedJSONObject["screenId"]);
    $.post("/native/insertCaptureLog", {
            logDate: parsedJSONObject["logDate"],
            logTime: parsedJSONObject["logTime"],
            screenId: parsedJSONObject["screenId"]
        }, function(data) {
              if(data.result_cd == "0"){
//                  alert(data.result);
                 $("#form01").attr("action","/native");
                 form01.submit();
               }
               else{
                  alert(data.result);
               }

        }, 'json');


}



function fn_authCallBack(res){
    let jsonObj = res;
//    alert(jsonObj.isAppFirstStarted );
    if(jsonObj.isAppFirstStarted == "Y"){
        //페이지 이동
        if(confirm("권한요청 화면 동의하시나요?")){
              var params = {header : { api: '131'}, body: {callBackFunc:  'noCallback'}};
              webkit.messageHandlers.callApp.postMessage(params);
              alert("동의하셨습니다");
          }
          else{
            return false;
          }
    }
    else{
        alert("이미 동의하셨습니다");
        return false;
    }
}


function change(){
    console.log("change");
    var result = $("#result").val();
    $("#result").val($("#plain").val());
    $("#plain").val(result);

}