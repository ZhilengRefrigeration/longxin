  // 禁止浏览器缩放
  var scrollFunc = function(e) {
    e = e || window.event;
    if (e.wheelDelta && event.ctrlKey) { //IE/Opera/Chrome
        event.returnValue = false;
    } else if (e.detail) { //Firefox
        event.returnValue = false;
    }
};
/*注册事件*/
if (document.addEventListener) {
    document.addEventListener('DOMMouseScroll', scrollFunc, false);
} //W3C
window.onmousewheel = document.onmousewheel = scrollFunc; //IE/Opera/Chrome/Safari
// 动态设置高度
$(function() {
    var height = $(window).height();
    $("body").height(height);
});

function fetchData(url, chartId, title, subtext) {
    axios
        .get(url, {
            headers: {
                'satoken': localStorage.getItem("token")
            }
        })
        .then(function (response) {
            if(response.data.code != 0){
                console.error(response.data.msg);
                window.location.href = "../../../";
            }
            var data = response.data.data || {};
            var formattedData = [];
            Object.keys(data).forEach(function (key) {
                var value = data[key];
                if (value === null || value === undefined) {
                    value = 0;
                }
                formattedData.push({ value: value, name: key });
            });

            var option = {
                title: {
                    text: title,
                    subtext: subtext,
                    x: 'right',
                    y: 'bottom',
                    textStyle: {
                        color: 'white',
                    },
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)',
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: formattedData.map(function (item) {
                        return item.name;
                    }),
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: { show: true },
                        dataView: { show: true, readOnly: false },
                        restore: { show: true },
                        saveAsImage: { show: true },
                    },
                },
                calculable: false,
                series: [
                    {
                        name: '计算结果',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: formattedData,
                    },
                ],
            };

            var myChart = echarts.init(document.getElementById(chartId));
            myChart.setOption(option);
        })
        .catch(function (error) {
            console.error(error);
        });
}

$(document).ready(function () {
    var urls = [
        'http://localhost/calculateMonthlyChurnRateByMonth',
        'http://localhost/LossByMonth',
        'http://localhost/JumpOutByMonth',
        'http://localhost/Conversion_rate',
    ];

    var chartIds = ['main', 'main1', 'main2', 'main3'];
    var titles = ['月份的流失率', '月份的复购率', '月份的跳失率', '行为的下单转化率'];
    var subtexts = ['流失率数据统计', '复购率数据统计', '跳失率数据统计', '下单转化率数据统计'];

    urls.forEach(function (url, index) {
        fetchData(url, chartIds[index], titles[index], subtexts[index]);
    });
});
