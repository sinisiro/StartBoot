

$(document).ready(function(){
console.log("here");
    //클라이언트->서버
   $("#btnServer").click(function(){
       alert("11111");

        var settings = {
           "url": "http://localhost:7080/test/sendSVData",
           "method": "POST",
           "timeout": 0,
           "headers": {
             "Content-Type": "application/json"
           },
           "data": JSON.stringify({"val":"server"}),
         };

         $.ajax(settings).done(function (response) {
           console.log(response);
         });
   });

   //클라이언트->서버
    $("#btnAjax").click(function(){

      var settings = {
        "url": "http://localhost:7080/test/getdata",
        "method": "POST",
        "timeout": 0,
        "headers": {
          "Content-Type": "application/json"
        },
        "data": JSON.stringify({"name":"sinisiro","age":"12"}),
      };

      $.ajax(settings).done(function (response) {
        console.log(response);
      });

    });

    //클라이언트->서버 application/x-www-form-urlencoded
    // 요거는 일부르 HttpServletRequest에 값이 담기게끔 처리해놓았음
    // val=clent&val2=client2
    $("#btnCors").click(function(){
//        alert("11111");
        $.post("http://localhost:8090/rest/v1/sendCLData", {
              val: "clent",
              val2:"client2"
          }, function(data) {
                if(data.result_cd == "0000"){
                    alert(data.val);
                 }
                 else{
                    alert(data.result);
                 }
          }, 'json');

    });

    //클라이언트->서버  application/json
    //{val: "sinisiro", val2: "sinisiro2"}
    $("#btnCors2").click(function(){
    alert("2222");
      var settings = {
        "url": "http://localhost:8090/rest/v1/sendCLData2",
        "method": "POST",
        "timeout": 0,
        "headers": {
          "Content-Type": "application/json"
        },
        "data": JSON.stringify({"val":"sinisiro", "val2":"sinisiro2"}),
      };

      $.ajax(settings).done(function (response) {
        console.log(response);
      });

    });

    //AES256
    $("#btnCrypt").click(function(){
    var plain = $("#plain").val();      //id값을 가져온다.
    var gubun = $('input[name="gubun"]:checked').val();//check box
//    var gubun = $("#gubun").val();        //select box

    console.log("gubun : "+gubun);

      var settings = {
        "url": "/test/getAES256",
        "method": "POST",
        "timeout": 0,
        "headers": {
          "Content-Type": "application/json"
        },
        "data": JSON.stringify({"val":plain, "gubun":gubun}),
      };

      $.ajax(settings).done(function (response) {
        console.log(response);
        if(response.result_cd=="0000"){
//        alert(response.val);
          $("#result").val(response.val);
        }
        else{
            alert(response.result);
        }
      });

    });

     //BASE64
    $("#btnBase64").click(function(){

      var plain = $("#plain").val();      //id값을 가져온다.
          var gubun = $('input[name="gubun"]:checked').val();//check box
      //    var gubun = $("#gubun").val();        //select box

      var settings = {
        "url": "http://localhost:7080/test/getBASE64",
        "method": "POST",
        "timeout": 0,
        "headers": {
          "Content-Type": "application/json"
        },
        "data": JSON.stringify({"val":plain, "gubun":gubun}),
      };

      $.ajax(settings).done(function (response) {
        console.log(response);
        if(response.result_cd=="0000"){
          $("#result").val(response.val);
        }
        else{
            alert(response.result);
        }
      });

    });

     //AES256
        $("#btnAESBase").click(function(){
        var plain = $("#plain").val();      //id값을 가져온다.
            var gubun = $('input[name="gubun"]:checked').val();//check box
        //    var gubun = $("#gubun").val();        //select box

          var settings = {
            "url": "/test/getAESBASE",
            "method": "POST",
            "timeout": 0,
            "headers": {
              "Content-Type": "application/json"
            },
            "data": JSON.stringify({"val":plain, "gubun":gubun}),
          };

          $.ajax(settings).done(function (response) {
            console.log(response);
            if(response.result_cd=="0000"){
    //        alert(response.val);
              $("#result").val(response.val);
            }
            else{
                alert(response.result);
            }
          });

        });

        //22.06.09 wk웹뷰 팝업 띄우기
        $("#btnPopup").click(function(){
            window.open('/test/popup')
        });



});

function change(){
    console.log("change");
    var result = $("#result").val();
    $("#result").val($("#plain").val());
    $("#plain").val(result);

}

// 22.06.09 총무지원에서 해당값이 먹히지 않아서 테스트로 만들어봄
$(document).on('click', '#btnClick', function(){
	alert("클릭 테스트 ");
});