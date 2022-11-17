
$(document).ready(function(){

    //22.09.13 임시폴더 초기화
    $("#btnClearTmp").click(function(){
        alert("hello!!");
        var params = {header : { api: '133'}, body: {callBackFunc:  'callBack'}};
        webkit.messageHandlers.callApp.postMessage(params);
//        alert("삭제완료");
        form01.submit();
    });

     $("#btnCamera").click(function(){
        $("#upload-image").attr("capture", "camera");
        $("#upload-image").click();
     });


     $("#btnCall").click(function(){
        $("#upload-image").removeAttr("capture");
         $("#upload-image").click();
      });
});

function callBack(method){
    alert(method+":callback 완료");
}

