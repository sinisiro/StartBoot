
$(document).ready(function(){
	$("#frm").submit();
});



//22.09.13 임시폴더 초기화
$("#btnClearTmp").click(function(){
    alert("hello")
        var params = {header : { api: '133'}, body: {callBackFunc:  'callBack'}};
        webkit.messageHandlers.callApp.postMessage(params);
    });