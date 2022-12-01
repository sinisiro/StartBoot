<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="format-detection" content="telephone=no, address=no, email=no">
	<title>DB손해보험</title>
	<%@ include file="/WEB-INF/jsp/include/head_new.jsp" %>
	<%@ include file="/WEB-INF/jsp/include/loading.jsp" %>
	<script type="text/javascript" charset="utf-8" src="js/util/ajaxutil.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/util/imgfileselector.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/util/hashmap.js"></script>
	
</head>
<body>
<form>
	<div id="wrap">
		<div id="content">
			<div id="step1">
				<header class="header_t2">
					<h1 class="logo">
						<a href="#none" class="link_db"><img src="./img/new/img_logodb.png" alt="DB손해보험"></a>
					</h1>
					<div class="wrap_tit_t4">
						<h2 class="tit">신분증 및 관련 서류 사진 등록</h2>		
						<p class="sub_txt">첨부할 서류를 확인 후 아래항목에 맞게 선택하여 사진첨부 진행하시기 바랍니다.</p>			
					</div>
				</header><!-- //header -->
	
				<section class="content_t2">
					<div class="desc mgt0">
						<h3 class="tit">대상계약</h3>
						<div class="tbl_t1">
			                <table>
			                    <caption>계약 설계번호, 증권번호</caption>
			                    <colgroup>
			                        <col style="width:30%">
			                        <col style="width:70%">
			                    </colgroup>
			                    <tbody>
			                        <tr>
			                        	<th scope="row">설계번호</th>
			                        	<td>${incInfo.sPlan_No}</td>
			                        </tr>
			                        <tr>
			                        	<th scope="row">증권번호</th>
			                        	<td>${incInfo.sPl_No}</td>
			                        </tr>
			                    </tbody>
			                </table>
			            </div>
					</div><!-- //desc-->	
					
					<div class="desc">	
						<h3 class="tit">서류유형 선택</h3>	
						<div class="box_round c1">
							<span class="radio">
								<input id="doc_Type1" type="radio" name="rad01" checked="checked">
								<i class="ico"></i>
								<label for="doc_Type1">계약변경신청서</label>
							</span>
							<span class="radio">
								<input id="doc_Type2" type="radio" name="rad01">
								<i class="ico"></i>
								<label for="doc_Type2">신분증</label>
							</span>
							<span class="radio">
								<input id="doc_Type3" type="radio" name="rad01">
								<i class="ico"></i>
								<label for="doc_Type3">관련서류</label>
							</span>
						</div>
					</div><!-- //desc-->
							
					
					<div class="desc">	
						<h3 class="tit">사진 등록하기</h3>	
						<div class="box_round c1">
							 <!-- 썸네일 들어가는 영역 ( 기존 js 활용 ) -->
							<div class="filebox1" style="display: block;" id="fileboxid_1">
				            	<form id="frm" method="post" action="<c:url value='/imageRegist.do'/>" enctype="multipart/form-data">
									<div class="filebox2">
										<input name="type" type="hidden" value="genericFileMulti"size="10" /> 
										<input type="file" id="upload-image"multiple="multiple" data-role="none" name="file[]" size="8" accept="image/*">
										</input>
									</div>
									<div id="thumbnail">
									</div>
									<br> 
									<input type="hidden" id="doc_Type" name="doc_Type" value="">
								</form>
								<div id="files_list"></div>
							</div>
							 <!-- 썸네일 들어가는 영역 ( 기존 js 활용 ) -->
						</div>	
						<div class="wrap_btn_t2 fileboxBtn clfix">
							<button type="button" class="btn t2 c2" id="btnCamera" style="">사진촬영</button>
							<button type="button" class="btn t2 cl1" id="btnCall" style="">불러오기</button>
							<button type="button" class="btn t2 cl3" id="btnDeleteMode" style="">사진삭제</button>
							<button type="button" class="btn btn_bg t2 c3" id="btnDelete" style="display:none">삭제</button>
							<button type="button" class="btn btn_bg t2 c2" id="btnDeleteCancel" style="display:none">취소</button>
						</div>		
					</div><!-- //desc-->
					
					<div class="desc">			
						<p class="txt_notice">사진은 최대 8장까지만 등록 가능합니다.</p>		
						<p class="txt_notice">사진 삭제시 삭제할 이미지를 선택 후 삭제 진행하시기 바랍니다.</p>		
					</div><!-- //desc-->	
									
					<div class="fixed_bottom">
		                <input id="btnSend" type="button" value="등록하기" class="btn_submit"> <!-- off class추가 : 비활성화 -->
		            </div>
		            
				</section><!-- //section -->
			</div><!-- // step1 -->
			<div id="step2" style="display: none;">
				<header class="header_t2">
					<h1 class="logo">
						<a href="#none" class="link_db"><img
							src="./img/new/img_logodb.png" alt="DB손해보험"></a>
					</h1>
					<div class="wrap_tit_t3">
						<h2 class="tit">사진첨부 완료</h2>
					</div>
				</header>
				<!-- //header -->

				<section class="content_t2">
					<div class="desc">
						<div class="wrap_ico ani_sldown">
							<em class="ico ico_complete"></em> <em
								class="ico ico_complete02"></em>
						</div>
						<p class="sub_txt_t2 align_center mgt40">
							<strong class="s1"><span class="fc_dbgreen" id="cust_nm"></span>
							님</strong><br /> 계약변경 관련서류 사진첨부가 완료되었습니다.
						</p>
						<p class="sub_txt_t2 align_center mgt20">
							<strong class="s2" id="pa_nm"></strong> 가<br /> 계약변경 내용 확인 후
							안내드리도록 하겠습니다.
						</p>
					</div>
					<!-- //desc-->

					<div class="fixed_bottom">
						<input type="button" id="btn_close" value="확인" class="btn_submit">
						<!-- off class추가 : 비활성화 -->
					</div>

				</section>
				<!-- //section -->
			</div><!-- step2 -->
			<input type="hidden" id="nm" value="${incInfo.sToCust_Msg}">
		</div><!-- //content-->
	</div>
</form>


<script>    

var bSended = false;

//최대 이미지 전송 갯수.
var MAX_IMAGE_SEND_NUMBER = 8; 
// 최대 이미지 크기. ajax(post) 기본제한이 2 메가이다.
var MAX_IMAGE_SIZE = 3 * 1000 * 1024; // 3 mega byte
// 최대 타임아웃.
var MAX_TIMEOUT = 50*1000; // 타임아웃

var bIsSending = false;

var imgfile_selector;

$(document).ready(function() 
{
	function createImgFileSelector(maxNum,maxSize) 
	{
		imgfile_selector = new imgFileSelector(document
				.getElementById('files_list'),maxNum,maxSize);
		imgfile_selector.init(document
				.getElementById('upload-image'));
	}
	
	createImgFileSelector(MAX_IMAGE_SEND_NUMBER,MAX_IMAGE_SIZE);

	function checkDocType() 
	{
		var doc_Type = "";
		if($("#doc_Type1").is(":checked"))
			doc_Type = "JKB001";
        else if($("#doc_Type2").is(":checked"))
        	doc_Type = "JKB008";
        else if($("#doc_Type3").is(":checked"))
        	doc_Type = "JKB009";
		return doc_Type; 
	}
	
	$("input[name*=rad01]").change(function()
		{ 
		var doc_Type = ""; 
		
		var len = $('input[name*=file_]').length - 1;
		if(len > 0)
		{
			doc_Type = $("#doc_Type").val();
			if(doc_Type == "JKB001")
				$("#doc_Type1").prop('checked', true);
			else if(doc_Type == "JKB008")
				$("#doc_Type2").prop('checked', true);
			else if(doc_Type == "JKB009")
				$("#doc_Type3").prop('checked', true);

			alert("불러온 사진들을 전송하시거나 삭제해주세요.");
			return;
		}

		doc_Type = checkDocType();
		$("#doc_Type").val(doc_Type);
	});
	
	// 사진촬영
	$("#btnCamera").click(function() 
	{		
		$("#upload-image").attr("capture","camera");
		$("#upload-image").click();
	});

	// 불러오기
	$("#btnCall").click(function() 
	{
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
	
	// 사진삭제
	$("#btnDelete").click(function() 
	{
		$('input[name*=filecheckbox_]').each(function() 
		{
			if($(this).is(":checked"))
			{
				var seqNo = $(this).val();
				
				$('div[name*=divthumbimg_' + seqNo + ']').remove();
				
				console.log("delete before imageMap.size : "  + imgfile_selector.imageMap.size());
				imgfile_selector.imageMap.remove(seqNo);
				console.log("delete after imageMap.size : "  + imgfile_selector.imageMap.size());
			}
	   });
	});

	// 삭제모드를 세팅한다.
	function setDelMode() 
	{
		$("#btnCamera").css("display","none");
		$("#btnCall").css("display","none");
		$("#btnDeleteMode").css("display","none");
		
		$("#btnDelete").css("display","");
		$("#btnDeleteCancel").css("display","");
		
		$('input[name*=filecheckbox_]').css("display","");
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

	// 전송 요청
	$("#btnSend").click(function() 
	{	
		if(bIsSending)
			return;

		var doc_Type = checkDocType();
		if(doc_Type == "")
        {
            alert("문서유형을 선택해주세요.");
            return;
        }
		
		$("#doc_Type").val(doc_Type);
		
		var len = imgfile_selector.imageMap.size();

		if(len < 1)
		{
			alert("사진을 첨부해주세요.");
			return;
		}
		
		
		
		if(confirm("계약에 꼭 필요한 서류만 첨부되어야 합니다. 등록처리를 완료하시겠습니까?")){
			bIsSending = true;
			
			totalSize = imgfile_selector.imageMap.size();
			seq = 1;
			sendImage();
		}/* else{
			return false;
		} */
		
		
	});
	
	var seq = 1;
	var currentKey = "";
	var totalSize = 0;
	
	function sendImage() 
	{
		var url = "/mobileinc/imageRegistAjax.do";

		var doc_Type = $("#doc_Type").val();

		for(var prop in imgfile_selector.imageMap.map)
		{
			var msg = "전송중 " + seq + "/" + totalSize;
			showLoadingBar(msg);
			
			console.log("sendImage : "  + seq + "/" + totalSize);
			console.log("doc_Type : "  + doc_Type);

			seq ++;
			currentKey = prop;
			
			var queryData = "doc_Type=" + doc_Type 
						  + "&img_b64Data=" + encodeURIComponent(imgfile_selector.imageMap.map[prop]);
			
			ajax_Data(url
					,queryData
					,MAX_TIMEOUT
					,sendSuccCallBack
					,sendErrCallback
					,msg
					,false);	
			return;	
		}  
	}
 
	var sendSuccCallBack = function(text)
	{
		console.log("sendSuccCallBack text : "  + text);
		
		var j = JSON.parse(text);
		
		if(j.result == "Y")
		{
			imgfile_selector.imageMap.remove(currentKey);
			$('div[name*=divthumbimg_' + currentKey + ']').remove();
			
			var remainSize = imgfile_selector.imageMap.size();
			
			console.log("remainSize : "  + remainSize);
			
			if(remainSize > 0)
			{
				sendImage();
			}
			else
			{
				bIsSending = false;
				hideLoadingBar();
				$("#step1").css("display","none");
				$("#step2").css("display","");
				var nm = $('#nm').val().trim();
				var cust_nm = nm.split("고객님")[0].trim() + " ";
				var pa_nm1 = nm.split("DB손해보험")[1].trim();
				var pa_nm2 = "취급자(" + pa_nm1.split("PA")[0].trim() + ") ";
				$("#cust_nm").text(cust_nm);
				$("#pa_nm").text(pa_nm2);
			}
		}
		else
		{
			bIsSending = false;
			hideLoadingBar();
			resendConfirm(j.errMsg);
		}
	};
	
	var sendErrCallback = function(xhr, status, error)
	{
		bIsSending = false;
		hideLoadingBar();
		
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
		totalSize = imgfile_selector.imageMap.size();
		seq = 1;
		
		// 재시도 여부 물음.
		var q = confirm("전송중 오류가 발생했습니다.("+errMsg +")\n재시도하시겠습니까?");
		if(q == true)
		{
			sendImage();
		}
	};
	
	$("#btn_close").click(function(){
		closeWindow();
	});
	
	function closeWindow() {
		var _ua = window.navigator.userAgent || window.navigator.vendor || window.opera;
		if (getBrowserName() == "kakaotalk") {
			window.location.href = (/iPad|iPhone|iPod/.test(_ua)) ? "kakaoweb://closeBrowser" : "kakaotalk://inappbrowser/close";
		} else if(getBrowserName() == "chrome") {
			window.close();
			setTimeout(function() {
				document.write("");
			}, 500);
		} else {
			var win = window.open("about:blank", "_self");
			win.close();
		}
    }
	
	function getBrowserName() {
		if (navigator) {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.indexOf('naver') != -1) {
				return 'naver';
			} else if (ua.indexOf('kakaotalk') != -1) {
				return 'kakaotalk';
			} else if (ua.indexOf('opr') != -1 || ua.indexOf('opera') != -1) {
				return 'opera';
			} else if (ua.indexOf('bdbrowser') != -1) {
				return 'baidu';
			} else if (ua.indexOf('ucbrowser') != -1) {
				return 'uc';
			} else if (ua.indexOf('chrome') != -1 && window.speechSynthesis) {
				return 'chrome';
			} else if (ua.indexOf('safari') != -1
					&& ua.indexOf('android') == -1) {
				return 'safari';
			} else if (ua.indexOf('firefox') != -1) {
				return 'firefox';
			} else if (ua.indexOf('msie') != -1) {
				return 'ie';
			} else if (ua.indexOf('trident') != -1) {
				return 'ie10+';
			}
			return 'etc';
		}
	}
	
	history.pushState(null, null, location.href);
	window.onpopstate = function(event){
		history.go(1);
	};
});

</script>    

</body>
</html>
