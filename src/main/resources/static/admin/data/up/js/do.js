function fetchAPIData() {
    return new Promise(function (resolve, reject) {
        var url = 'http://localhost/UpData';

        $.ajax({
            url: url,
            type: 'GET',
            headers: {
                'satoken': localStorage.getItem("token")
            },
            success: function (response) {
                if (response.code === 0) {
                    var data = response.data;
                    resolve(data);
                } else {
                    reject(response.msg);
                }
            },
            error: function (xhr, status, error) {
                reject(error);
            }
        });
    });
}
var apiData;
    fetchAPIData()
    .then(function (data) {
         apiData = data;
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('content'));

    var labelTop = {
        normal: {
            label: {
                show: true,
                position: 'center',
                formatter: '{b}',
                textStyle: {
                    baseline: 'bottom'
                }
            },
            labelLine: {
                show: false
            }
        }
    };
    var labelFromatter = {
        normal: {
            label: {
                formatter: function (params) {
                    return 100 - params.value + '%'
                },
                textStyle: {
                    baseline: 'top'
                }
            }
        },
    }
    var labelBottom = {
        normal: {
            color: '#ccc',
            label: {
                show: true,
                position: 'center'
            },
            labelLine: {
                show: false
            }
        },
        emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var radius = [40, 55];
    option = {
        legend: {
            x: 'center',
            y: 'center',
            data: [
                '女生占比', '男生占比', '一线城市', '二线城市', '三线城市',
                '四线城市', '五线城市', '0到10岁', '11到20岁'
            ]
        },
        title: {
            text: '用户画像',
            subtext: '用户画像分析',
            x: 'center',
            textStyle: {
                fontSize: 18,
                color: 'white'
            },
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            width: '20%',
                            height: '30%',
                            itemStyle: {
                                normal: {
                                    label: {
                                        formatter: function (params) {
                                            return 'other\n' + params.value + '%\n'
                                        },
                                        textStyle: {
                                            baseline: 'middle'
                                        }
                                    }
                                },
                            }
                        }
                    }
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                type: 'pie',
                center: ['10%', '30%'],
                radius: radius,
                x: '0%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.female_ratio, itemStyle: labelBottom},
                    {name: '女生占比', value: apiData.female_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['30%', '30%'],
                radius: radius,
                x: '20%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.male_ratio, itemStyle: labelBottom},
                    {name: '男生占比', value: apiData.male_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['50%', '30%'],
                radius: radius,
                x: '40%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.first_tier_city_ratio, itemStyle: labelBottom},
                    {name: '一线城市', value: apiData.first_tier_city_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['70%', '30%'],
                radius: radius,
                x: '60%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.second_tier_city_ratio, itemStyle: labelBottom},
                    {name: '二线城市', value: apiData.second_tier_city_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['90%', '30%'],
                radius: radius,
                x: '80%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.third_tier_city_ratio, itemStyle: labelBottom},
                    {name: '三线城市', value: apiData.third_tier_city_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['10%', '70%'],
                radius: radius,
                y: '55%',   // for funnel
                x: '0%',    // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.fourth_tier_city_ratio, itemStyle: labelBottom},
                    {name: '四线城市', value: apiData.fourth_tier_city_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['30%', '70%'],
                radius: radius,
                y: '55%',   // for funnel
                x: '20%',    // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.fifth_tier_city_ratio, itemStyle: labelBottom},
                    {name: '五线城市', value: apiData.fifth_tier_city_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['50%', '70%'],
                radius: radius,
                y: '55%',   // for funnel
                x: '40%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.age_0_to_10_ratio, itemStyle: labelBottom},
                    {name: '0到10岁', value: apiData.age_0_to_10_ratio, itemStyle: labelTop}
                ]
            },
            {
                type: 'pie',
                center: ['70%', '70%'],
                radius: radius,
                y: '55%',   // for funnel
                x: '60%', // for funnel
                itemStyle: labelFromatter,
                data: [
                    {name: 'other', value: 100-apiData.age_11_to_20_ratio, itemStyle: labelBottom},
                    {name: '11到20岁', value: apiData.age_11_to_20_ratio, itemStyle: labelTop}
                ]
            }
        ]
    };
    myChart.setOption(option);
})
    .catch(function (error) {
        console.log(error);
    });
