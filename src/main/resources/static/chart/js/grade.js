// 로그인하고 확인하기
var isLoginClicked = false;

$(".login_btn_con_btn").click(function(event){
  event.preventDefault();
  $(".login_before").hide();
  isLoginClicked = true;
});



Chart.defaults.font.family = 'Noto Sans KR';
//
//var chartArea = document.getElementById('myChart1').getContext('2d');
//var myChart = new Chart(chartArea, {
//  type: 'bar',
//  data: {
//    labels: ['', '', '', '', ''],
//    datasets: [{
//      label: '',
//      data: [157, 148, 111, 150, 191],
//      backgroundColor: ['#FFD2BA', '#FFD2BA', '#E3F4E9', '#E3F4E9', '#FFD2BA'],
//      borderColor: ['#CA4C07', '#CA4C07', '#157941', '#157941', '#CA4C07'],
//      borderRadius: '5',
//      borderWidth: 1,
//      order: 2,
//    },
//    {
//      type: 'line', // 라인 차트 추가
//      label: '',
//      data: [140, 100, 111, 150, 135],
//      borderColor: '#767676',
//      fill: false,
//      order: 1,
//      pointStyle: 'circle',
//      pointRadius: 4,
//      pointBorderWidth: 2,
//      pointBackgroundColor: '#535353',
//      pointBorderColor: 'white',
//      shadowBlur: 3,
//      shadowOffsetX: 3,
//      shadowOffsetY: 10,
//    }]
//  },
//  options: {
//    maxBarThickness: getMaxBarThickness(),
//    scales: {
//      x: {
//        beginAtZero: true,
//        grid: {
//          color: 'transparent',
//        },
//        ticks: {
//          color: 'transparent',
//        },
//        barThickness: 20,
//      },
//      y: {
//        beginAtZero: true,
//        stacked: true,
//        grid: {
//          color: 'transparent',
//        },
//        display: false,
//      }
//    },
//    plugins: {
//      legend: {
//        display: false,
//      },
//      tooltip: {
//        enabled: false
//      },
//    },
//  }
//});
//
//var chartArea2 = document.getElementById('myChart2').getContext('2d');
//var myChart = new Chart(chartArea2, {
//  type: 'bar',
//  data: {
//    labels: ['', '', '', ''],
//    datasets: [{
//      label: '',
//      data: [157, 111, 111, 165,],
//      backgroundColor: ['#FFD2BA', '#E3F4E9', '#E3F4E9', '#FFD2BA'],
//      borderColor: ['#CA4C07', '#157941', '#157941', '#CA4C07'],
//      borderRadius: '5',
//      borderWidth: 1,
//      order: 2,
//    },
//    {
//      type: 'line', // 라인 차트 추가
//      label: '',
//      data: [140, 115, 111, 150,],
//      borderColor: '#767676',
//      fill: false,
//      order: 1,
//      pointStyle: 'circle',
//      pointRadius: 4,
//      pointBorderWidth: 2,
//      pointBackgroundColor: '#535353',
//      pointBorderColor: 'white'
//    }]
//  },
//  options: {
//    maxBarThickness: getMaxBarThickness(),
//    scales: {
//      x: {
//        beginAtZero: true,
//        grid: {
//          color: 'transparent',
//        },
//        ticks: {
//          color: 'transparent',
//        },
//        barThickness: 20,
//      },
//      y: {
//        beginAtZero: true,
//        stacked: true,
//        grid: {
//          color: 'transparent',
//        },
//        display: false,
//      }
//    },
//    plugins: {
//      legend: {
//        display: false,
//      },
//      tooltip: {
//        enabled: false
//      },
//    },
//  }
//});

function getMaxBarThickness() {
  var flexWrapPcWidth = document.querySelector('.flex_wrap_pc').offsetWidth;
  var maxWidth = flexWrapPcWidth === 1200 ? 54 : 32;
  return maxWidth;
}

var radarChart = document.getElementById("radarChart");
var myChart = new Chart(radarChart, {
  type: 'radar',
  data: {
    labels: ["체질량 지수", "수축기 혈압", "공복혈당", "총 콜레스테롤", "이완기 혈압"],
    datasets: [{
      label: '',
      backgroundColor: "rgba(255, 200, 200, 0.42)",
      data: [3, 3, 1.8, 3, 4],
      borderColor: '#CA4C07',
      borderWidth: 1,
      radius: 0,
    }, {
      label: "Student B",
      backgroundColor: "rgba(212, 212, 212, 0.8)",
      data: [2, 1, 2, 2, 0],
      borderColor: '#ACACAC',
      borderWidth: 1,
      radius: 0,
    }]
  },
  options: {
    layout: {
      padding: {
        top: 1,
      }
    },
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        enabled: false,
      }
    },
    scales: {
      r: {
        grid: {
          color: "#D4D4D4",
          lineWidth: 1,
        },
        ticks: {
          display: false,
          beginAtZero: true,
          min: 0,
          max: 5,
          stepSize: 1
        },
        pointLabels: {
          display: false,
        }
      }
    }
  }
});

var pieChart = document.getElementById("pieChart");
var myChart = new Chart(pieChart, {
  type: 'pie',
  data: {
    labels: ["체질량 지수", "수축기 혈압"],
    datasets: [{
      label: 'My First Dataset',
      data: [2.8, 97.2],
      backgroundColor: [
        '#157941',
        '#E3F4E9',
      ],
      borderColor: [
        '#157941',
        '#157941',
      ],
      borderWidth: 1,
      hoverOffset: 4
    }]
  },
  options: {
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        enabled: false,
      }
    },
    events: [],
  }
});