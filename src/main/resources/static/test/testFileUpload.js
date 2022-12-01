
var bSended = false;

//최대 이미지 전송 갯수.
var MAX_IMAGE_SEND_NUMBER = 8;
// 최대 이미지 크기. ajax(post) 기본제한이 2 메가이다.
var MAX_IMAGE_SIZE = 3 * 1000 * 1024; // 3 mega byte
// 최대 타임아웃.
var MAX_TIMEOUT = 50*1000; // 타임아웃

var bIsSending = false;

var imgfile_selector;

$(document).ready(function(){

	function createImgFileSelector(maxNum,maxSize)
	{
	    console.log("createImgFileSelector");
		imgfile_selector = new imgFileSelector(document
				.getElementById('files_list'),maxNum,maxSize);
		imgfile_selector.init(document
				.getElementById('upload-image'));
	}

	createImgFileSelector(MAX_IMAGE_SEND_NUMBER,MAX_IMAGE_SIZE);


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

    // 사진삭제 모드
    $("#btnDeleteMode").click(function()
    {
        setDelMode();
    });

    // 사진삭제 취소
    $("#btnDeleteCancel").click(function()
    {
        cancelDelMode();
    });

    // 사진등록
    $("#btnSubmit").click(function()
    {
        totalSize = imgfile_selector.imageMap.size;
        console.log("등록하기");
        sendImageLoadingSync();
    });



    // 사진삭제
    $("#btnDelete").click(function()
    {
        console.log("삭제");
        $('input[name*=filecheckbox_]').each(function()
        {
            if($(this).is(":checked"))
            {
                var seqNo = $(this).val();

                $('div[name*=divthumbimg_' + seqNo + ']').remove();

                console.log("delete before imageMap.size : "  + imgfile_selector.imageMap.size);
                imgfile_selector.imageMap.remove(seqNo);
                console.log("delete after imageMap.size : "  + imgfile_selector.imageMap.size);
            }
       });
    });



});

function callBack(method){
    alert(method+":callback 완료");
}


// 삭제모드를 세팅한다.
	function setDelMode()
	{
		$("#btnCamera").css("display","none");
		$("#btnCall").css("display","none");
		$("#btnDeleteMode").css("display","none");

		$("#btnDelete").css("display","");
		$("#btnDeleteCancel").css("display","");

		$('input[name*=filecheckbox_]').css("display","");      //checkbox를 나타나게 처리한다. 파일 업로드시 imgselector.js에서 이미 세팅해놓은 체크박스.
		$('input[name*=filecheckbox_]').prop('checked', false);
	}

	// 삭제모드를 취소한다.
    function cancelDelMode()
    {
        $("#btnCamera").css("display","");
        $("#btnCall").css("display","");
        $("#btnDeleteMode").css("display","");

        $("#btnDelete").css("display","none");
        $("#btnDeleteCancel").css("display","none");

        $('input[name*=filecheckbox_]').css("display","none");
    }




	var seq = 1;
	var currentKey = "";
	var totalSize = 0;

	function sendImage()
	{

        //*practice **/
        var obj = {a:1, b:2, c:3}; // note that objects have properties (a, b, c) and values (1, 2, 3) like in the given example

        for (var prop in obj) { // "prop" is just a variable name. you can replace it with anything like anyVariableNameAndThisWillAlsoWork
//            console.log(prop);  // here, we want to know what the variable "prop" contains and have it displayed in the console so we would understand how for...in loop works
        }
        //파일 전송.
        const iterator1 = imgfile_selector.imageMap[Symbol.iterator]();
        const iteratorKeys = imgfile_selector.imageMap.keys();
        //*practice **/

         var queryData =""
         var url=""
         var msg=""


        for(var i=0; i < imgfile_selector.imageMap.size; i++){
        {
//        msg = "전송중 " + Number(j.seq+1) + "/" + imgfile_selector.imageMap.size;
//        showLoadingBar(msg);

            queryData = "img_b64Data=" +encodeURIComponent(imgfile_selector.imageMap.get(i))
                    + "&seq=" + i;
            url = "/test/fileUploadJson3";


            ajax_Data(url
                    ,queryData
                    ,MAX_TIMEOUT
                    ,sendSuccCallBack
                    ,sendErrCallback
                    ,"전송중... "+ Number(i+1) + "/" + imgfile_selector.imageMap.size
                    ,true);

//
//            $.post("/test/fileUploadJson3", {
//                seq: i,
//                val : img_b64Data,
//             }, function(data){
//                   if(data.result_cd == "0000"){
//                        console.log(i+"번째 성공");
//                         sendSuccCallBack2(data);
//                   }else{
//                       sendErrCallback(xhr, status, error);
//                   }
//               }, 'json');
        }
        imgfile_selector.seqNo = 0 ;
	    }
	}



	var sendSuccCallBack = function(text)
	{

		var j = JSON.parse(text);
		console.log("sendSuccCallBack text : "  + j);

		if(j.result == "Y")
		{

//		    console.log(j.seq);
			imgfile_selector.imageMap.delete(Number(j.seq));
//			console.log("size:"+imgfile_selector.imageMap.size);
//			console.log('div[name*=divthumbimg_' + j.seq + ']');
			$('div[name*=divthumbimg_' + j.seq + ']').remove();
		}
		else
		{
			bIsSending = false;
			resendConfirm(text.errMsg);
		}
	};


    var sendSuccCallBack2 = function(text)
	{
    console.log("sendSuccCallBack text : "  + text);


		if(text.result == "Y")
		{
		    console.log(text.seq);
			imgfile_selector.imageMap.delete(text.seq);
//			console.log('div[name*=divthumbimg_' + text.seq + ']');
			$('div[name*=divthumbimg_' + text.seq + ']').remove();

		}
		else
		{
			bIsSending = false;
			resendConfirm(text.errMsg);
		}
	};


	var sendErrCallback = function(xhr, status, error)
	{
		bIsSending = false;

		if(error == "Unauthorized")
		{
			alert("세션이 만료되었습니다.\n수신받으신 문자의 URL로 다시 접속해주세요.");
		}
		else
		{
			resendConfirm(error);
		}
	};

	var resendConfirm = function(errMsg)
	{
		totalSize = imgfile_selector.imageMap.size;
		seq = 1;

		// 재시도 여부 물음.
		var q = confirm("전송중 오류가 발생했습니다.("+errMsg +")\n재시도하시겠습니까?");
		if(q == true)
		{
			sendImage();
		}
	};


     //delay 함수.
    function _fnsleep(delay) {
        var start = new Date().getTime();
        while (new Date().getTime() < start + delay);
    }

    var cnt=0;


    /**
    비동기 함수 보완하여 로딩바 해결.

    sendImage() 로 하면 파일 처리 진행현황을 표시할수 없는 문제발생
    for문을 돌며 로딩바띄워주고 ajax를 모두 수행해 버리기 때문에, 기대했던 서버 처리후에 로딩바가 바뀌것을 할 수 없었음.
    그래서 sendImageLoadingSync 수행 ->로딩바 띄워줌->콜백에서 파일처리 다안되있으면 sendImageLoadingSync 호출 로 변경하여 해결.
    */

	function sendImageLoadingSync()
    {
         var queryData =""
         var url=""
         var msg=""

        queryData = "img_b64Data=" +encodeURIComponent(imgfile_selector.imageMap.get(cnt))
                        + "&seq=" + cnt;
        url = "/test/fileUploadJson3";


        ajax_Data(url
                ,queryData
                ,MAX_TIMEOUT
                ,sendSuccCallBackLoadingSync
                ,sendErrCallback
                ,"전송중... "+ Number(cnt+1) + "/" + totalSize
                ,true);
    }


	var sendSuccCallBackLoadingSync = function(text)
	{
		var j = JSON.parse(text);

		if(j.result == "Y")
		{
			imgfile_selector.imageMap.delete(Number(j.seq));
			$('div[name*=divthumbimg_' + j.seq + ']').remove();

            var remainSize = imgfile_selector.imageMap.size;
            if(remainSize > 0)
            {
                cnt++;
                sendImageLoadingSync();
            }
            else{
                imgfile_selector.seqNo = 0 ;
                cnt =0;
            }
		}
		else
		{
			bIsSending = false;
			resendConfirm(text.errMsg);
		}
	};
