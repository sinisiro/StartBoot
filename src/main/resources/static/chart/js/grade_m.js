// 로그인하고 확인하기
var isLoginClicked = false;

$(window).scroll(function() {
  var healthOpinionPosition = $('.health_opinion')[0].getBoundingClientRect();

  if (healthOpinionPosition.top <= $(window).height() && healthOpinionPosition.bottom >= 0 && !isLoginClicked) {
    $('.contents').css('max-height', '2620px');
  }
});

$(".login_btn_con_btn").click(function(event){
  event.preventDefault();
  $(".login_before").hide();
  $(".login_after").show();
  $('.contents').css('max-height', 'inherit');
  isLoginClicked = true;
});
