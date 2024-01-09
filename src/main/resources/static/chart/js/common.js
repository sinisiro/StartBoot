$(window).scroll(function() {
  var elementPosition = $('.health_caution_rank')[0].getBoundingClientRect();

  if (elementPosition.top <= $(window).height() && elementPosition.bottom >= 0) {
    $('.health_caution_img').css('animation', 'caution_img ease 1s forwards');
    $('.rank_graph_1 .rank_arrow').css('animation', 'rank_graph1 ease 1s  1s forwards');
    $('.rank_graph_2 .rank_arrow').css('animation', 'rank_graph2 ease 1s .5s forwards');
    $('.rank_graph_3 .rank_arrow').css('animation', 'rank_graph3 ease 1s forwards');
    $('.rank_graph_1 .ranking').css('animation', 'ranking ease 1s 1s forwards');
    $('.rank_graph_2 .ranking').css('animation', 'ranking ease 1s .6s forwards');
    $('.rank_graph_3 .ranking').css('animation', 'ranking ease 1s .2s forwards');
  }
});

$(document).ready(function() {
  $('.toggleCheckbox').change(function() {
    if ($(this).is(':checked')) {
      $('.disease_chart1').fadeOut('200', function() {
        $('.disease_chart2').fadeIn('200');
      });
    } else {
      $('.disease_chart2').fadeOut('200', function() {
        $('.disease_chart1').fadeIn('200');
      });
    }
  });
});


$(document).ready(function(){
  $(".disease_list").hide();
  $(".very_dangerous").show();
  $(".very_dangerous_btn").addClass("current");

  $(".very_dangerous_btn").click(function(){
    $(".health_summary_list li").removeClass("current");
    $(this).addClass("current");
    $(".disease_list").hide();
    $(".very_dangerous").show();
  });

  $(".dangerous_btn").click(function(){
    $(".health_summary_list li").removeClass("current");
    $(this).addClass("current");
    $(".disease_list").hide();
    $(".dangerous").show();
  });

  $(".caution_btn").click(function(){
    $(".health_summary_list li").removeClass("current");
    $(this).addClass("current");
    $(".disease_list").hide();
    $(".caution").show();
  });

  $(".relief_btn").click(function(){
    $(".health_summary_list li").removeClass("current");
    $(this).addClass("current");
    $(".disease_list").hide();
    $(".relief").show();
  });

});


// 팝업
$(document).ready(function(){
  $('.disease_list ul li').click(function() {
    $('#modalWrap').css('display', 'block');
    $('body').css('overflow', 'hidden');
  });

  $('.info_btn').click(function() {
    $('#modalWrap2').css('display', 'block');
    $('body').css('overflow', 'hidden');
  });

  $('#closeBtn').click(function() {
    $('#modalWrap').css('display', 'none');
    $('#modalWrap2').css('display', 'none');
    $('body').css('overflow', 'inherit');
  });

  $('#closeBtn2').click(function() {
    $('#modalWrap2').css('display', 'none');
    $('body').css('overflow', 'inherit');
  });

  $('.modal_close').click(function(event) {
    event.preventDefault();
    $('#modalWrap').css('display', 'none');
    $('#modalWrap2').css('display', 'none');
    $('body').css('overflow', 'inherit');
  });

  $('.modalBody').click(function(event) {
    event.stopPropagation();
  });

  $('#modalWrap').click(function(event) {
    $('#modalWrap').css('display', 'none');
    $('body').css('overflow', 'inherit');
  });
  $('#modalWrap2').click(function(event) {
    $('#modalWrap2').css('display', 'none');
    $('body').css('overflow', 'inherit');
  });
  $('.close_btn').click(function(event) {
    $('.pop_login').css('display', 'none');
  });
});


