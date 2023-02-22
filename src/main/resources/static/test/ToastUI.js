

$(document).ready(function(){

        //보안문자.
        var base64 = "iVBORw0KGgoAAAANSUhEUgAAAMgAAAAyCAIAAACWMwO2AAAOQklEQVR42u2de3RUxR3H16LyhiARAgKSgIIQCBoCGhACUnlUq2C1PEQgYEylikW0gkAMCFqpgEKxCCJaqQ8q9W21+KhW0UKt1hdYrajVKlipD/DJOf1kJud3JjN7787upqd29/7+2LPZO3Pv3JnP/f2+87iTWJmyIUOGDFU2bNiw7yo78cQThw8fPmLEiJEjR44aNep7yk4++eTvKzvllFNOPfXU0aNHjxkz5rTTTvuBsjPOOOOHysaOHTtu3Ljx48dPmDDhzDPPnKhs0qRJk5VNmTKlvLx86tSp06ZNO/vssysqKs4555zKysofKTv33HN/rOy88847//zzZ8yYccEFF/xE2cyZMy+88MJZs2ZddNFFF1988U+VzZ49e46ySy+9dO7cufPmzZs/f35VVdVlyhYsWLBQ2eWXX75o0aLFixdfccUVV1555c+ULVmy5OfKrr766mXKli9ffs0111x77bUrVqxYuXLlL5StWrXql8pWr159/fXXr1mzZu3atTfccMM6ZTfeeOP69etvuummm2+++VfKNmzY8Gtlt95662233Xb77bffcccdGzdu/I2yTZs2/VbZXXfddffdd99zzz333nvvfffdd//99z/wwAMPPvjg75Q99NBDv1e2efPmRx555NFHH33ssccef/zxPyh74okn/qjsqaeeevrpp7ds2fLMM888++yzf1K2devWPyt77rnn/qLshRde+KuyF1988aWXXnr55ZdfeeWVV199dfv27Tt27Hjttdf+puz111//u7I333xz586db7311ttvv/3OO+/8Q9m77777T2Xvv//+Bx98sGvXrt27d3/44Yf/UvbRRx/9W9nHH3/8ySefxEywTjjhBBOsEcoErJNOOskEa7QyAev0008XsDRVJlhnnXWWgKWpCgJr+vTpIWDNUiZgXXLJJQKWpsoEq7q6WsDSVJlgXXXVVQLW0qVLQ8C67rrrQsBar0zAuuWWWwQsTZUJ1p133ilgaaqCwHr44YdDwHryyScFrC3KBCyo2rZtmwnW888/L2BpqoLAeuONN0LAeu+994LAgqo9e/YIWJ9++mktWNpdCVjirjRY2l0JWOKuNFjaXQlY4q40WNpdCVjirjRYFcoELNyVgKWpErDEXWmwtLsSsMRd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";
        $("#img").attr("src","data:image/png;base64,"+base64).css({'background-repeat':'no-repeat'});

       // GRID 를 생성한다
        // 나이는 수정할 수 있도록 설정한다.
		var grid = new tui.Grid( {
			el: document.getElementById('grid'),
			columns: [
				{
					header: '이름',
					name: 'name'
				},
				{
					header: '나이',
					name: 'value',
					editor: 'text'
				}
			]
		} );

        // GRID 에 데이터를 입력한다.
		var arrData = [
			{
				name: '홍길동',
				value: '20세'
			},
			{
				name: '이지연',
				value: '26세'
			}
		];

		grid.resetData( arrData );

//    const Chart = toastui.Chart;

//    var el = document.getElementById('chart');
//    const data = {
//      categories: ['Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
//      series: [
//        {
//          name: 'Budget',
//          data: [5000, 3000, 5000, 7000, 6000, 4000, 1000],
//        },
//        {
//          name: 'Income',
//          data: [8000, 4000, 7000, 2000, 6000, 3000, 5000],
//        },
//      ],
//    };
//    var options = {
//      chart: { width: 700, height: 400 },
//    };
//
//
//    var chart = toastui.Chart.barChart({ el, data, options });
//     const chart = new BarChart({ el, data, options }); // 두 번째 방법

     const el = document.getElementById('chart2');
       const data = {
        categories: [
          'Jan',
          'Feb',
          'Mar',
          'Apr',
          'May',
          'Jun',
          'Jul',
          'Aug',
          'Sep',
          'Oct',
          'Nov',
          'Dec',
        ],
        series: [
          {
            name: 'Seoul',
            data: [20, 40, 25, 50, 15, 45, 33, 34, 20, 30, 22, 13],
          },
          {
            name: 'Sydney',
            data: [5, 30, 21, 18, 59, 50, 28, 33, 7, 20, 10, 30],
          },
          {
            name: 'Moscow',
            data: [30, 5, 18, 21, 33, 41, 29, 15, 30, 10, 33, 5],
          },
        ],
      };
      const options = {
        chart: { title: 'Average Temperature', width: 900, height: 400 },
        xAxis: { pointOnColumn: false, title: { text: 'Month' } },
        yAxis: { title: 'Temperature (Celsius)' },
      };

     chart = toastui.Chart.areaChart({ el, data, options });

});