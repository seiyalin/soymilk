var catalogyIds;

/* 
 * 判断图片类型 
 *  
 * @param ths  
 *          type="file"的javascript对象 
 * @return true-符合要求,false-不符合 
 */  
function checkImgType(ths){  
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(ths)) {  
            return false;  
        }  
    return true;  
} 

$(document).on("click",".modal-dialog span[data-role='remove']",function(){
	var parentSpan = $(this).parent();
	if(typeof(parentSpan.attr("catalogyValue"))!="undefined"){
		catalogyIds.splice($.inArray(parentSpan.attr("catalogyValue"),catalogyIds),1);
	}
	parentSpan.remove();
});


$(document).ready(function(){
	//定义datatable
	var oTable = $("#product_datatable").dataTable(
		$.extend($.fn.bdmp.datatableConf,{
			"sAjaxSource": "machine_List.do", //ajax请求地址+ "*/
			"aoColumns" : [
				{
				  "aTargets": [0],
				  "mData": null,
				  "bSortable": false,
				  "bSearchable": false ,
				  "mRender": function (data, type, full) {
					  return '<label class="checkbox_label"><input class="colored-blue checkboxes" type="checkbox"><span class="text"></span></label>';
			 		} 
		   		},
		   		{
				  "aTargets": [1],
				  "mData": "machineId",
				  "bSortable": false,
				  "bSearchable": false,
				  
				},
				{
				  "aTargets": [2],
				  "mData": "machineName",
				  "bSortable": false,
				  "bSearchable": false,
				},
				{
				  "aTargets": [3],
				  "mData": "appId",
				  "bSortable": false,
				  "bSearchable": false,
				},
				{
					  "aTargets": [4],
					  "mData": "mchId",
					  "bSortable": false,
					  "bSearchable": false,
					 
				},
				{
					  "aTargets": [5],
					  "mData": "apiKey",
					  "bSortable": false,
					  "bSearchable": false,
					 
				},
			   {
					"aTargets": [6],
					"mData":"createIp",
					"bSortable": false,
					"bSearchable": false,
					
				}], 
				/*{
					"aTargets": [5],
					"mData": null,
					"bSortable": false,
				    "bSearchable": false ,
					"mRender": function (data, type, full) {
						 return '<div><button id="add_waterinfo" class="btn btn-primary" style="margin-right: 10px;" disabled="disabled"><i class="fa fa-edit "></i>添加</button><button id="see">查看</button>';
				} 
				}*/
				
			"fnInitComplete": function (oSettings,json) {
				//增加搜索
				$("#product_datatable_filter").append('<label style="margin-left:20px;"><span class="input-icon"><input id="name" class="form-control input-sm" type="text" placeholder="请输入名称"><i class="glyphicon glyphicon-search blue"></i></span></label>');
		    	
		    	//增加搜素查询
		    	$("#product_datatable_length").prepend('<a id="resetButton" href="javascript:void(0);" class="btn btn-yellow" style="margin-right: 10px;"><i class="fa fa-mail-reply"></i>重置</a>');
		    	$("#product_datatable_length").prepend('<a id="searchButton" href="javascript:void(0);" class="btn btn-blue" style="margin-right: 10px;"><i class="fa fa-search"></i>搜索</a>');
		    },
		    "fnDrawCallback": function(oSettings){
		    	$("#product_datatable .group-checkable").prop("checked", false);
		    	renderButtonFn();
		    },
		    "fnServerParams": function (aoData) {
		    	//搜素设置参数
		    	$.fn.bdmp.addParam(aoData,{"name":"name", "value":$.trim($("#product_datatable_filter #name").val())});
		    }
		})
	);

	//注册添加钮事件
	$("#addButton").on("click", function () {
		
		 var addDialog = bootbox.dialog({
             message: $("#addDialog").html(),
             title: "添加",
             className: "modal-darkorange",
             buttons: {
                 "保存": {
                     className: "btn-primary",
                     callback: function () {
			 
                    	var machineId= addDialog.find("#machineId").val();
 						var machineName = addDialog.find("#machineName").val();
 				        var appId= addDialog.find("#appId").val();
 				        var mchId= addDialog.find("#mchId").val();
 				        var apiKey= addDialog.find("#apiKey").val();
 				        var createIp= addDialog.find("#createIp").val();
 				     
 						/*if (name == "") {
 							$.fn.bdmp.message.error("养殖编号不能为空!");
 							return false;
 						}
 				
 						if (name.length > 30) {
 							$.fn.bdmp.message.error("姓名长度不能超过30!");
 							return false;
 						}
 						
 						if (orderNo == "") {
 							$.fn.bdmp.message.error("养殖区域不能为空!");
 							return false;
 						}*/
 						
 						$.post("machine_Save.do",addDialog.find("form").serialize(),function(data) {
 							if(data.success){
 								
 								$.fn.bdmp.message.success("添加成功!");
 								oTable.fnReloadAjax();
 						   		addDialog.modal("hide");
                    				return true;
 							}else{
 								$.fn.bdmp.message.error(data.message);
 								return false;
 							}
 						}, 'json');
 						
                     	return false;
                     }
                 },
                 "关闭": {
                     className: "btn-default",
                     callback: function () {
                     	return true;
                     }
                 }
             }
         });
    });
    
    //注册修改按钮事件
    $("#updateButton").on("click", function () {
    	var row = oTable.fnGetData(oTable.$("tr.active")[0]);
    	$("#updateDialog #machineName").attr("value",row.machineName);
    	$("#updateDialog #appId").attr("value",row.appId);
    	$("#updateDialog #mchId").attr("value",row.mchId);
    	$("#updateDialog #apiKey").attr("value",row.apiKey);
    	$("#updateDialog #createIp").attr("value",row.createIp);
     	$("#updateDialog #machineId").attr("value",row.machineId);
    	
    	var updateDialog = bootbox.dialog({
               message: $("#updateDialog").html(),
               title: "修改",
               className: "modal-darkorange",
               buttons: {
                   "保存": {
                       className: "btn-primary",
                       callback: function () {
    		
	       						var machineName = updateDialog.find("#machineName").val();
	       						var appId = updateDialog.find("#appId").val();
	       						var mchId = updateDialog.find("#mchId").val();
	       						var apiKey = updateDialog.find("#apiKey").val();
	       						var createIp = updateDialog.find("#createIp").val();
	       				
 	      						var data = updateDialog.find("form").serialize();
 	                          	data += "&machineid=" + row.machineId;
 	  							$.post("machine_Update.do",data,function(data) {
 	  								if(data.success){
 	  									$.fn.bdmp.message.success("修改成功!");
 	  									oTable.fnReloadAjax();
 	  							   		updateDialog.modal("hide");
 	                         				return true;
 	  								}else{
 	  									$.fn.bdmp.message.error(data.message);
 	  									return false;
 	  								}
 	  							}, 'json');
							
 	  							return false;
                       }
                   },
                   "关闭": {
                       className: "btn-default",
                       callback: function () {
                       	return true;
                       }
                   }
               }
           });
    	
    });
    
    //注册删除按钮事件
    $("#deleteButton").on("click", function () {

    	if(oTable.$("tr.active").size() == 0){
    		$.fn.bdmp.message.warning("请选择记录!");
    	}else{
    		var ids = "";
    		$.each(oTable.$("tr.active"),function(i,n){  
                if(i==0){
                    ids += oTable.fnGetData(n).machineId; 
                }else {
                    ids += (","+oTable.fnGetData(n).machineId); 
                } 
            });
    		
    		bootbox.confirm({
    	        message: '<span><i class="glyphicon glyphicon-question-sign yellow"></i></span>&nbsp;&nbsp;<span class="font">确定删除吗?</span>',
    	        className: "bdmp_confirm",
    	        callback: function(result) {
    	        	if (result) {
    	        		$.post("machine_Delete.do",{machineIds:ids},function(data) {
    						if(data.success){
    							$.fn.bdmp.message.success("删除成功!");
    							oTable.fnReloadAjax();
    							
    					   	}else{
    							$.fn.bdmp.message.error(data.message);
    						}
    					}, 'json');
    	            }
    		    }
        	});
    	}
    });
    
    //注册刷新按钮事件
    $("#refreshButton").on("click", function () {
    	oTable.fnPageChange($.fn.bdmp.page.currentPage(oTable)); 
    });
    
   
 
    //注册checkbox以及行选中事件
    $.fn.bdmp.registerCheckboxAction("product_datatable",renderButtonFn);
    
    function renderButtonFn(){
    	var deleteButtonFlag = true;
    	
    	$.each(oTable.$("tr.active"),function(i,n){
    		if(oTable.fnGetData(n).defaulted){
    			deleteButtonFlag = false;
    		}
        });
    	
    	if(oTable.$("tr.active").size()==1){
    		$("#updateButton").removeAttr("disabled");
    		
    	}else{
    		$("#updateButton").attr("disabled","disabled");
    		
    	}
    	if(oTable.$("tr.active").size()>0){
    		$("#deleteButton").removeAttr("disabled");
    	}else{
    		$("#deleteButton").attr("disabled","disabled");
    	}
    	
    	if(deleteButtonFlag&&oTable.$("tr.active").size()>0){
    		$("#deleteButton").removeAttr("disabled");
    	}else{
    		$("#deleteButton").attr("disabled","disabled");
    	}
    	
    }
});