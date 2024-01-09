

$(document).ready(function(){
    connect();

   $("#btnServer").click(function(){
       var plain = $("#message").val();      //id값을 가져온다.

         var settings = {
           "url": "/mq/publish",
           "method": "POST",
           "timeout": 0,
           "headers": {
             "Content-Type": "application/json"
           },
           "data": JSON.stringify({"val":plain}),
         };

         $.ajax(settings).done(function (response) {
           alert("발행완료");
         });
   });

   $("#btnEmit").click(function(){
       var topic = $("#topic").val();
       var messageTopic = $("#messageTopic").val();

         var settings = {
           "url": "/mq/publishToken",
           "method": "POST",
           "timeout": 0,
           "headers": {
             "Content-Type": "application/json"
           },
           "data": JSON.stringify({"topic":topic, "messageTopic":messageTopic}),
         };

         $.ajax(settings).done(function (response) {
           alert("발행완료");
         });
   });

   $("#btnRecv").click(function(){
          var topicName = $("#topicName").val();

            var settings = {
              "url": "/mq/receiveTopic",
              "method": "POST",
              "timeout": 0,
              "headers": {
                "Content-Type": "application/json"
              },
              "data": JSON.stringify({"topicName":topicName}),
            };

            $.ajax(settings).done(function (response) {
              alert("구독완료["+topicName+"]");
            });
      });


    $("#btnEmitM").click(function(){
          var topic = $("#topicM").val();
          var messageTopic = $("#messageTopicM").val();

            var settings = {
              "url": "/mq/publishTokenM",
              "method": "POST",
              "timeout": 0,
              "headers": {
                "Content-Type": "application/json"
              },
              "data": JSON.stringify({"topic":topic, "messageTopic":messageTopic}),
            };

            $.ajax(settings).done(function (response) {
              alert("발행완료");
            });
      });
});


var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(message) {
            showMessage(message.body);
        });
    });
}

function showMessage(message) {
    $('#messageList').text(message);
}
