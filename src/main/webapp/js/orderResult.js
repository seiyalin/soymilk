
function initTable(){
	
}

function showOrder(){
	/*var $table = $("#cusTable");*/
	$.ajax({
		url:"showOnline.do",
		data:$("#selectQuery").serialize(),
		type:"post",
		dataType:"json",
		success:function(json){
			
			$("#cusTable").bootstrapTable('destroy').bootstrapTable({
				data:json,
				/*striped: true,  //表格显示条纹  
		        pagination: true, //启动分页  
	            pageSize: 10,  //每页显示的记录数  
	            pageNumber:1, //当前第几页  
	            pageList: [5, 10, 15, 20, 25],  //记录数可选列表  
*/	            search: true,  //是否启用查询  
	            showColumns: true,  //显示下拉框勾选要显示的列  
	           /* showRefresh: true,  //显示刷新按钮  
	            sidePagination: "server", //表示服务端请求  
*/	            columns:[
	                     {title:"生产商",
	                      field:'producer_id',
	                      searchable:true},
	                      
	                      {title:"渠道商",
		                  field:'channel_id',
		                  searchable:true},
		                  {title:"终端设备",
			              field:'machine_id',
			              searchable:true},
			              {title:"年份",
				          field:'year',
				          searchable:true},         
		                  {title:"月份",
			              field:'month',
			              searchable:true},
			              {title:"日期",
			              field:'day',
			              searchable:true},
		                  {title:"口味",
		                  field:'product_id',
		                  searchable:true},
		                  {title:"销量",
		                  field:'counts',
		                  sortable:true},
		                  {title:"销售额",
		                  field:'sales',     
		                  sortable:true
		                  }
	                      ]
			});
		}
	});
}


 						
 						