<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>设备管理</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!--Basic Styles-->
    <link href="../common/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="../common/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="../common/assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->
    <!-- <link href="http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css"> -->

    <!--Beyond styles-->
    <link id="beyond-link" href="../css/beyond.min.css" rel="stylesheet" />
    <link href="../common/assets/css/demo.min.css" rel="stylesheet" />
    <link href="../common/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="../common/assets/css/animate.min.css" rel="stylesheet" />
    <link href="../common/assets/css/customer.css" rel="stylesheet" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />

    <!--Page Related styles-->
    <link href="../common/assets/css/dataTables.bootstrap.css" rel="stylesheet" />
    
 	<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="../common/assets/js/skins.min.js"></script>
     <script src="../common/laydate/laydate.js"></script>
    <link rel="stylesheet" type="text/css" href="../common/assets/css/fileinput.min.css" />

    <style>
        .position_change{
            position:absolute;
            top:78px;
            left:215px;
            width:80px;
        }   
    </style>
</head>
<body>

  <!-- Loading Container -->
    <div class="loading-container">
        <div class="loading-progress">
            <div class="rotator">
                <div class="rotator">
                    <div class="rotator colored">
                        <div class="rotator">
                            <div class="rotator colored">
                                <div class="rotator colored"></div>
                                <div class="rotator"></div>
                            </div>
                            <div class="rotator colored"></div>
                        </div>
                        <div class="rotator"></div>
                    </div>
                    <div class="rotator"></div>
                </div>
                <div class="rotator"></div>
            </div>
            <div class="rotator"></div>
        </div>
    </div>
    <!--  /Loading Container -->
<div id="content">

 <!--  <div id="content-header">
      <h1>表格</h1>
  </div> -->
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
       
        <div class="widget-box">   
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <h5>设备管理信息</h5>
          </div>
                <!-- Page Body -->
                <div class="page-body">
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="widget widget-table-container">
                               <!--  <div class="widget-header ">
                                    <span class="widget-caption">设备信息</span>
                                    <div class="widget-buttons">
                                        <a href="#" data-toggle="maximize">
                                            <i class="fa fa-expand"></i>
                                        </a>
                                        <a href="#" data-toggle="collapse">
                                            <i class="fa fa-minus"></i>
                                        </a>
                                        <a href="#" data-toggle="dispose">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div> -->
                                <div class="widget-body">
                                	<div class="table-toolbar">
                                        <div class="buttons-preview" style="border-bottom: 1px solid #e5e5e5;">
                                            <a id="addButton" href="javascript:void(0);" class="btn btn-default purple" style="margin-right: 10px;"><i class="fa fa-plus"></i>新增</a>
											<a id="updateButton" href="javascript:void(0);" class="btn btn-primary" style="margin-right: 10px;" disabled="disabled"><i class="fa fa-edit "></i>编辑</a>
											<a id="deleteButton" href="javascript:void(0);" class="btn btn-danger" style="margin-right: 10px;" disabled="disabled"><i class="fa fa-times"></i>删除</a>
											<a id="refreshButton" href="javascript:void(0);" class="btn btn-success" style="margin-right: 10px;"><i class="fa fa-refresh"></i>刷新</a>
											
                                        </div>
                                    </div>
                                    <div class="panel-body" style="padding-bottom:0px;">
                                        <table class="table table-striped table-bordered table-hover" id="product_datatable" width="100%" >
			                                <thead>
										        <tr>
													<th width="5%">
														<label class="checkbox_label">
															<input class="colored-blue group-checkable" type="checkbox"><span class="text"></span>
														</label>
													</th>
													<th width="10%">设备编号</th>
													<th width="10%">设备名称</th>
													<th width="10%">app_id</th>
													<th width="10%">mch_id</th>						
													<th width="10%">api_key</th>
													<th width="10%">create_ip</th>
													
												</tr>
											</thead>
			                                 <tbody>
			                                 </tbody>
			                            </table> 
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /Page Body -->			
               </div>          
        </div>
      </div>
    </div>
  </div>

<!--========添加弹出框=========---->
	<div id="addDialog" style="display:none;">
		<div id="horizontal-form">
           <form class="form-horizontal">
               <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">设备编号</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="machineId" name="machineId" maxlength="50">
                   </div>
               </div>
              
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">设备名称</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="machineName" name="machineName" maxlength="50">
                   </div>
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">app_id</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="appId" name="appId" maxlength="50">
                   </div>
               </div>         
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">mch_id</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="mchId" name="mchId" maxlength="50">
                   </div>
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">api_key</span></label>
                   <div class="col-sm-4">
                       <input type="text" class="form-control" id="apiKey" name="apiKey"  maxlength="50">
                   </div>
                 
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">create_ip</span></label>
                   <div class="col-sm-4">
                       <input type="text" class="form-control" id="createIp" name="createIp"  maxlength="50">
                   </div>
                 
               </div>
                          
           </form>
       </div>
    </div>
	<!------end------->
	
	<!--========添加弹出框=========---->
	<div id="updateDialog" style="display:none;">
		<div id="horizontal-form">
           <form class="form-horizontal">
               <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">设备编号</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="machineId" name="machineId" maxlength="50">
                   </div>
               </div>
              
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">设备名称</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="machineName" name="machineName" maxlength="50">
                   </div>
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">app_id</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="appId" name="appId" maxlength="50">
                   </div>
               </div>         
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">mch_id</span></label>
                   <div class="col-sm-6">
                       <input type="text" class="form-control" id="mchId" name="mchId" maxlength="50">
                   </div>
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">api_key</span></label>
                   <div class="col-sm-4">
                       <input type="text" class="form-control" id="apiKey" name="apiKey"  maxlength="50">
                   </div>
                 
               </div>
                <div class="form-group">
                   <label for="name" class="col-sm-2 control-label no-padding-right"><span class="label label-darkpink graded">create_ip</span></label>
                   <div class="col-sm-4">
                       <input type="text" class="form-control" id="createIp" name="createIp"  maxlength="50">
                   </div>
                 
               </div>
                          
           </form>
       </div>
    </div>
	<!------end------->
	
	
	<!------end------->
    <!--Basic Scripts-->
    <script src="../common/assets/js/jquery-2.0.3.min.js"></script>
    <script src="../common/assets/js/bootstrap.min.js"></script>
    <!--Beyond Scripts-->
    <script src="../common/assets/js/beyond.min.js"></script>  
    <!--Page Related Scripts-->
    <script src="../common/assets/js/datatable/jquery.dataTables.min.js"></script>
    <script src="../common/assets/js/datatable/ZeroClipboard.js"></script>
    <script src="../common/assets/js/datatable/dataTables.tableTools.min.js"></script>
    <script src="../common/assets/js/datatable/dataTables.bootstrap.min.js"></script>
    <!--  <script src="assets/js/datatable/datatables-init.js"></script> -->
    <script src="../common/assets/js/bootbox/bootbox.js"></script>
    <script src="../common/assets/js/toastr/toastr.js"></script>
    <script src="../common/assets/js/fuelux/treeview/tree-custom.min.js"></script>
    <script src="../common/assets/js/customer.js"></script>   
    <!--Jquery Select2-->
    <script src="../common/assets/js/select2/select2.js"></script>
    <!--Bootstrap Tags Input-->
    <script src="../common/assets/js/tagsinput/bootstrap-tagsinput.js"></script>
    <!--Bootstrap Date Picker-->
    <script src="../common/assets/js/datetime/bootstrap-datepicker.js"></script>
    <!--Bootstrap Time Picker-->
    <script src="../common/assets/js/datetime/bootstrap-timepicker.js"></script>
    <!--Bootstrap Date Range Picker-->
    <script src="../common/assets/js/datetime/moment.js"></script>
    <script src="../common/assets/js/datetime/daterangepicker.js"></script>
    <!--Jquery Autosize-->
    <script src="../common/assets/js/textarea/jquery.autosize.js"></script>
    <!--Fuelux Spinner-->
    <script src="../common/assets/js/fuelux/spinner/fuelux.spinner.min.js"></script>
    <!--jQUery MiniColors-->
    <script src="../common/assets/js/colorpicker/jquery.minicolors.js"></script>
    <!--jQUery Knob-->
    <script src="../common/assets/js/knob/jquery.knob.js"></script>
    <!--noUiSlider-->
    <script src="../common/assets/js/slider/jquery.nouislider.js"></script>
    <!--jQRangeSlider-->
    <script src="../common/assets/js/jquery-ui-1.10.4.custom.js"></script>
    <script src="../common/assets/js/slider/jQRangeSlider/jQAllRangeSliders-withRuler-min.js"></script>
    <script type="text/javascript" src="../common/assets/js/My97DatePicker/WdatePicker.js"></script>
    <script src="../js/machine.js"></script><!--  datatable -->
  	<script src="../common/assets/js/upload/jquery.form.min.js"  type="text/javascript"></script>
 	<script src="../common/assets/js/upload/fileinput.min.js"  type="text/javascript"></script>
    <script src="../common/assets/js/upload/fileinput_locale_zh.js" type="text/javascript"></script>
</body>
</html>
