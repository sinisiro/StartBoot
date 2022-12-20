
var fileIdx = -1;
var currFileIdx = [-1, -1];
var fileSizeLimit = 20;
var proc_dvn = "01";
var interval = null;
var xhr = null;
var num = "a3A/EZWRg3vaaq/IHWMxvQ==";
var phgp_rl_ptgr_dt = null;	// 사진실제촬영일자
var phgp_apdx_way_cd = -1;	// 사진첨부방식코드
var file1 = null;
var exifDate1 = null;
var MAX_TIMEOUT = 50*1000; // 타임아웃

$(document).ready(function() {

		$(":file").css({ "opacity": 0, "filter": "alpha(opacity=0)", width: "1px", height: "1px" }).change(function(e) {
			switch($(this).attr("id")) {
				case "file1_1":
					fileIdx = 0;
					phgp_apdx_way_cd = 1;
					break;
				case "file1_2":
					fileIdx = 1;
					phgp_apdx_way_cd = 2;
					break;
			}
			if(isAllowedExtension($(this).val().toLowerCase())) {
				fn_handleFile(e);
			}
			else {
				alert("그림파일(jpg, jpeg, gif, png, bmp, tif, tiff)만 등록 가능합니다.");
			}
		});



    $("#regBtn").click(function() {
			if(currFileIdx[0] == -1) {
				alert("사진 등록을 해주시기 바랍니다.");
				return;
			}

			$("#bgDim").css("z-index", "200");
			$("#regBtn").html("등록중입니다.&nbsp;&nbsp;");

			var interval = setInterval(function() {
				switch($("#regBtn").html()) {
					case "등록중입니다.&nbsp;&nbsp;":
						$("#regBtn").html("등록중입니다..&nbsp;");
						break;
					case "등록중입니다..&nbsp;":
						$("#regBtn").html("등록중입니다...");
						break;
					case "등록중입니다...":
						$("#regBtn").html("등록중입니다.&nbsp;&nbsp;");
						break;
				}

			}, 200);
			var formData = new FormData();

			if(file1 != null && file1.size > 0) {
				formData.append("file1", file1, "image1.jpg");
			}
			else {
				formData.append("file1", currFileIdx[0] == 0 ? document.getElementById("file1_1").files[0] : document.getElementById("file1_2").files[0]);
			}
			if(exifDate1 != null) {
				formData.append("exifDate1", exifDate1);
			}

            /* 이방법으론 formdata 전송 불가
            //https://stackoverflow.com/questions/6974684/how-to-send-formdata-objects-with-ajax-requests-in-jquery
            $.post("/test/fileUploadMultiPartResized", formData,
             function(data){
                   if(data.result_cd == "0000"){
                        console.log(i+"번째 성공");
                         sendSuccCallBack(data);
                   }else{
                       sendErrCallback(xhr, status, error);
                   }
               }, 'json');
               */
                $.ajax({
                url: '/test/fileUploadMultiPartResized',
                    data: formData,
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function(data){
//                         alert(data);
                         $("#form1").attr("action","/test/fileUploadResizingEnd");      //id를 지정해주어야 함 id="form1"
                         form1.submit();
                    }
                });

        }
	);

});


// 사진촬영 or 불러오기
	function fn_handleFile(e) {
		if(!checkFileSize(e.target.files[0])) {
			alert("파일 사이즈는 " + fileSizeLimit + "MB를 초과할 수 없습니다.");
			return;
		}

		var img = document.createElement("img");
		var reader = new FileReader();
	    reader.onload = function(e) {
	    	currFileIdx[0] = fileIdx;
	        var preview = $("#preview1");
	    	preview.attr("src", reader.result).css({"width": "100%", "visibility": "hidden"});
	        EXIF.getData(document.form1.file[fileIdx].files[0], function() {
	       		phgp_rl_ptgr_dt = typeof EXIF.getTag(this, 'DateTime') == "undefined" ? "" : EXIF.getTag(this, 'DateTime').substring(0, 19).replace(/[:]/g, '').replace(/(\s*)/g,"");
	       		exifDate1 = typeof EXIF.getTag(this, 'DateTimeOriginal') == "undefined" ? "" : EXIF.getTag(this, 'DateTimeOriginal').substring(0, 10).replace(/[:]/g, '');
	       		if(fileIdx % 2 == 1) {
	       			exifDate1 += ",gallery";
	       		}
	           	preview.css("visibility", "visible");
	        });
	        try {

	            img.src = e.target.result;
	            img.onload = function() {
					var canvas = document.createElement("canvas");
 		            // var ctx = canvas.getContext("2d");
					// ctx.drawImage(img, 0, 0);

					var MAX_WIDTH = 1920;
					var MAX_HEIGHT = 1920;

					var width = this.width;
					var height = this.height;

					console.log("width: " + width);
					console.log("height: " + height);

					if(width > height) {
						if(width > MAX_WIDTH) {
							height *= MAX_WIDTH / width;
							width = MAX_WIDTH;
						}
					}
					else {
						if(height > MAX_HEIGHT) {
							width *= MAX_HEIGHT / height;
							height = MAX_HEIGHT;
						}
					}
					canvas.width = width;
					canvas.height = height;

					var ctx = canvas.getContext("2d");
					ctx.drawImage(img, 0, 0, width, height);

					var dataurl = canvas.toDataURL("image/jpeg");
					file1 = dataURItoBlob(dataurl);
					console.log("리사이징 후: " + file1.size);

	            }
	        } catch(ex) { }
	    }
	    reader.readAsDataURL(e.target.files[0]);
	}

	function dataURItoBlob(dataURI) {
		var byteString;
		if(dataURI.split(',')[0].indexOf('base64') >= 0) byteString = atob(dataURI.split(',')[1]);
		else byteString = unescape(dataURI.split(',')[1]);

		var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

		var ab = new ArrayBuffer(byteString.length);
		var ia = new Uint8Array(ab);
		for(var i = 0; i < byteString.length; i++) {
			ia[i] = byteString.charCodeAt(i);
		}

		return new Blob([ab], {type: mimeString});
	}


    function isAllowedExtension(filename) {
		if(filename == "" || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".gif") || filename.endsWith(".png") || filename.endsWith(".bmp") || filename.endsWith(".tif") || filename.endsWith(".tiff")) {
			return true;
		}
		return false;
	}
    function checkFileSize(file) {
        console.log("선택 파일 사이즈: " + file.size);
        console.log("최대 사이즈: " + fileSizeLimit);
        if(file.size <= fileSizeLimit * 1024 * 1024) return true;
        else return false;
    }

