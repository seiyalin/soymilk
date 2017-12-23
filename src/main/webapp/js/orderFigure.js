
$(document).ready(function(){
	// 初始化echarts示例mapChart
	var mapChart = echarts.init(document.getElementById('map-wrap'));
	mapChart.setOption(option);
	mapChart.on('click',function(params){
		if(params.seriesName!="设备"){
			/*var city = params.name;
			loadChart(city);*/
			return;
		}
		else{
			/*alert(params.name);*/
			if(($("#year option:selected").val()=="all") || ($("#month option:selected").val()=="all") ||($("#month option:selected").val()=="--")){
				alert("请选择正确的年份和月份！");
				return;
			}
			else{

			var producerId = document.getElementById("producerId");
			var channelId = document.getElementById("channelId");
			var machineId = document.getElementById("machineId");
			var year = document.getElementById("year");
			var month = document.getElementById("month");
			var day = document.getElementById("day");
			var s=params.name.split('-');
			producerId.value = s[0];
			channelId.value = s[1];
			machineId.value = s[2];
			var dayTitle=params.name+" "+$("#year option:selected").val()+"年"+ $("#month option:selected").val()+"月销量";
			var monthTitle=params.name+" "+$("#year option:selected").val()+"年销量";
			var yearTitle=params.name+"各年销量";
			
			day.value="all";	
			$.ajax({
				url:"showOnline.do",
				data:$("#queryByMap").serialize(),
				type:"post",
				dataType:"json",
				success:function(json){
					var dayData1=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
					var dayData2=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
					var dayData3=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
					var dayData=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
					for(var i=0; i<json.length; i++){
						var index=parseInt(json[i].day.substring(8));
						if(json[i].product_id==1)
							dayData1[index-1]=json[i].counts;
						if(json[i].product_id==2)
							dayData2[index-1]=json[i].counts;
						if(json[i].product_id==3)
							dayData3[index-1]=json[i].counts;
						dayData[index-1]=dayData[index-1]+ parseInt(json[i].counts);
					}
					dayChart.setOption({
						title : {
					        text: dayTitle,
					        x:'center'
					    },
						series : [
							        {
							            name:'口味1',
							            type:'line',
							            data:dayData1,
							            markPoint : {
							                data : [
							                    {type : 'max', name: '最大值'},
							                    {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name: '平均值'}
							                ]
							            }
							        },
							        {
							            name:'口味2',
							            type:'line',
							            data:dayData2,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        },
							        {
							            name:'口味3',
							            type:'line',
							            data:dayData3,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        },
							        {
							            name:'所有口味',
							            type:'line',
							            data:dayData,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        }
							    ]
					});
				}
			});
			
			
			day.value="--";			
			month.value="all";			
			$.ajax({
				url:"showOnline.do",
				data:$("#queryByMap").serialize(),
				type:"post",
				dataType:"json",
				success:function(json){
					var monthData1=[0,0,0,0,0,0,0,0,0,0,0,0];
					var monthData2=[0,0,0,0,0,0,0,0,0,0,0,0];
					var monthData3=[0,0,0,0,0,0,0,0,0,0,0,0];
					var monthData=[0,0,0,0,0,0,0,0,0,0,0,0];
					for(var i=0; i<json.length; i++){
						var index=parseInt(json[i].month.substring(5));
						if(json[i].product_id==1)
							monthData1[index-1]=json[i].counts;
						if(json[i].product_id==2)
							monthData2[index-1]=json[i].counts;
						if(json[i].product_id==3)
							monthData3[index-1]=json[i].counts;
						monthData[index-1]=monthData[index-1]+ parseInt(json[i].counts);
					}
					monthChart.setOption({
						title : {
					        text: monthTitle,
					        x:'center'
					    },
						series : [
							        {
							            name:'口味1',
							            type:'line',
							            data:monthData1,
							            markPoint : {
							                data : [
							                    {type : 'max', name: '最大值'},
							                    {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name: '平均值'}
							                ]
							            }
							        },
							        {
							            name:'口味2',
							            type:'line',
							            data:monthData2,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        },
							        {
							            name:'口味3',
							            type:'line',
							            data:monthData3,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        },
							        {
							            name:'所有口味',
							            type:'line',
							            data:monthData,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        }
							    ]
					});
				}
			});
			
			day.value="--";			
			month.value="--";
			year.value="all";
			
			$.ajax({
				url:"showOnline.do",
				data:$("#queryByMap").serialize(),
				type:"post",
				dataType:"json",
				success:function(json){
					var yearData1=[0,0,0,0,0,0];
					var yearData2=[0,0,0,0,0,0];
					var yearData3=[0,0,0,0,0,0];
					var yearData=[0,0,0,0,0,0];
					for(var i=0; i<json.length; i++){
						var index=parseInt(json[i].year)-2017;
						if(json[i].product_id==1)
							yearData1[index]=json[i].counts;
						if(json[i].product_id==2)
							yearData2[index]=json[i].counts;
						if(json[i].product_id==3)
							yearData3[index]=json[i].counts;
						yearData[index]=yearData[index]+ parseInt(json[i].counts);
					}
					yearChart.setOption({
						title : {
					        text: yearTitle,
					        x:'center'
					    },
						series : [
							        {
							            name:'口味1',
							            type:'line',
							            data:yearData1,
							            markPoint : {
							                data : [
							                    {type : 'max', name: '最大值'},
							                    {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name: '平均值'}
							                ]
							            }
							        },
							        {
							            name:'口味2',
							            type:'line',
							            data:yearData2,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        },
							        {
							            name:'口味3',
							            type:'line',
							            data:yearData3,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        },
							        {
							            name:'所有口味',
							            type:'line',
							            data:yearData,
							            markPoint : {
							                data : [
							                     {type : 'max', name: '最大值'},
							                     {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name : '平均值'}
							                ]
							            }
							        }
							    ]
					});
				}
			});
			
		}
		}
	});
	
	var dayChart = echarts.init(document.getElementById('day_sales'));
	dayChart.setOption(option_day);
	
	var monthChart = echarts.init(document.getElementById('month_sales'));
	monthChart.setOption(option_month);
	
	var yearChart = echarts.init(document.getElementById('year_sales'));
	yearChart.setOption(option_year);
});

var myData = [
          	{name: 'SH-YP-HD001', value: [121.51, 31.302,'SH-YP-HD001']},
            {name: 'SH-YP-HD002', value: [121.508, 31.303,'SH-YP-HD002']},
            {name: 'SH-HK-SHJ001', value: [121.509, 31.297,'SH-HK-SHJ001']},
            {name: 'SH-HK-SHJ002', value: [121.507, 31.296,'SH-HK-SHJ002']},
            {name: 'BJ-HD-QH001', value: [116.3, 40.01, 'BJ-HD-QH001']} 
          ];


// mapChart的配置
var option = {
	geo: {
	      map: 'china',
	      itemStyle: {					// 定义样式
	         normal: {					// 普通状态下的样式
	              areaColor: '#d9d2e9',
	              borderColor: '#111',
	            },
	         emphasis: {					// 高亮状态下的样式
	              areaColor: '#99cc33',
	            }
	  	  }
	},
	backgroundColor:'#ffffff',
	series: [
	 		{
	 			name: '设备', // series名称
	 			type: 'scatter', // series图表类型
	 			coordinateSystem: 'geo', // series坐标系类型
	 			data:myData,
	 			label:{
	 				normal:{
	 					show:false,
	 					formatter: '{b}',
	                    position: 'right',
	 				},
	 				emphasis:{
	 					show:true
	 				}
	 				
	 			}
	 		}
	 ],
	/*visualMap: {
			type: 'continuous', // 连续型
			min: 0,       		// 值域最小值，必须参数
			max: 200,			// 值域最大值，必须参数
			calculable: true,	// 是否启用值域漫游
			inRange: {
	             	color: ['#50a3ba','#eac736','#d94e5d']
	                             // 指定数值从低到高时的颜色变化
	          },
			textStyle: {
				color: '#fff'	// 值域控件的文本颜色
			}
	 }*/

};

var option_day= {
	    title : {
	        text: '日销量',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	    	orient: 'vertical',  
	        left: 'left',  
	        data:['口味1','口味2','口味3','所有口味']
	    },
	    /*toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },*/
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16',
	                    '17','18','19','20','21','22','23','24','25','26','27','28','29','30','31']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'口味1',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'口味2',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        },
	        {
	            name:'口味3',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        },
	        {
	            name:'所有口味',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};

var option_month= {
	    title : {
	        text: '月销量',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	    	orient: 'vertical',  
	        left: 'left',  
	        data:['口味1','口味2','口味3','所有口味']
	    },
	    /*toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },*/
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['1','2','3','4','5','6','7','8','9','10','11','12']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'口味1',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'口味2',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        },
	        {
	            name:'口味3',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        },
	        {
	            name:'所有口味',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};

var option_year= {
	    title : {
	        text: '年销量',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	    	orient: 'vertical',  
	        left: 'left',  
	        data:['口味1','口味2','口味3','所有口味']
	    },
	    /*toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },*/
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : [2017,2018,2019,2020,2021,2022]
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'口味1',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'口味2',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        },
	        {
	            name:'口味3',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        },
	        {
	            name:'所有口味',
	            type:'line',
	            data:[],
	            markPoint : {
	                data : [
	                     {type : 'max', name: '最大值'},
	                     {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};
                    


	                    


 						
 						