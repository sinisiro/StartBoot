
/*
 * ajax 헬퍼 
 * by 안정진
 * 최종 수정일. 2017.02.13
 * bHideLoadingBar 를 true로 설정하지 않는다면 ajax 시작과 종료간에 로딩바가 뜬다.
 */

// ajax - data
var ajax_Data = function(reqUrl,queryData,maxTimeOut,succCallback,errCallback,msg,bHideLoadingBar) {
	
	var m = msg;
	var bControlLBar = bHideLoadingBar;
	
	$.ajaxSetup({
		dataType: "text",
		crossDomain : true,
		cache:false,
		type: "POST",
		timeout:maxTimeOut
	});

	$.ajax({
		url : reqUrl, 
		data: queryData,
		beforeSend:function() {		
			if(bControlLBar){
			    showLoadingBar(m);
			}

		},
		complete:function() {
			if(bControlLBar)
				hideLoadingBar();
		},
		success : function(text) {
			succCallback(text);
		},
		error : function(xhr, status, error) {
			errCallback(xhr, status, error);
		},
	});
};

// ajax - file
var ajax_File = function(reqUrl,formData, maxTimeOut, succCallback,errCallback,msg,bControlLoadingBar) {	
	
	var m = msg;
	var bControlLBar = bControlLoadingBar;
	
	$.ajaxSetup({
		processData: false,
		contentType : false,
		type: 'POST',
		timeout:maxTimeOut
	});

	$.ajax({
		url : reqUrl, 
		data: formData,
		beforeSend:function() {		
			if(bControlLBar)
				showLoadingBar(m);
		},
		complete:function() {
			if(bControlLBar)
				hideLoadingBar();
		},
		success : function(text) {		
			succCallback(text);
		},
		error : function(xhr, status, error) {
			errCallback(xhr, status, error);
		},
	});
};