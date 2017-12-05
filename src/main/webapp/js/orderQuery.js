var selectInit = function(_producerId, _channelId, _machineId, _year, _month, _day){
	var producerId = document.getElementById(_producerId);
	var channelId = document.getElementById(_channelId);
	var machineId = document.getElementById(_machineId);
	var year = document.getElementById(_year);
	var month = document.getElementById(_month);
	var day = document.getElementById(_day);
	
	function cmbSelect(cmb, str)
    {
        for(var i=0; i<cmb.options.length; i++)
        {
            if(cmb.options[i].value == str)
            {
                cmb.selectedIndex = i;
                return;
            }
        }
    }
	
	function cmbAddOption(cmb, str, obj)
    {
        var option = document.createElement("OPTION");
        cmb.options.add(option);
        option.innerHTML = str;
        option.value = str;
        option.obj = obj;
    }
	
	function changeChannel()
    {
        machineId.options.length = 0;
        if(channelId.selectedIndex == -1)return;
        var item = channelId.options[channelId.selectedIndex].obj;
        for(var i=0; i<item.machineList.length; i++)
        {
            cmbAddOption(machineId, item.machineList[i], null);
        }
        cmbSelect(machineId, "HD001");
    }
	
	 function changeProducer()
	    {
		 channelId.options.length = 0;
	        channelId.onchange = null;
	        if(producerId.selectedIndex == -1)return;
	        var item = producerId.options[producerId.selectedIndex].obj;
	        for(var i=0; i<item.channelList.length; i++)
	        {
	            cmbAddOption(channelId, item.channelList[i].name, item.channelList[i]);
	        }
	        cmbSelect(channelId, "YP");
	        changeChannel();
	        channelId.onchange = changeChannel;
	    }
	 
	 function changeMonth()
	    {
	        day.options.length = 0;
	        if(month.selectedIndex == -1)return;
	        var item = month.options[month.selectedIndex].obj;
	        for(var i=0; i<item.dayList.length; i++)
	        {
	            cmbAddOption(day, item.dayList[i], null);
	        }
	        cmbSelect(day, "--");
	    }
		
		 function changeYear()
		    {
			 month.options.length = 0;
		        month.onchange = null;
		        if(year.selectedIndex == -1)return;
		        var item = year.options[year.selectedIndex].obj;
		        for(var i=0; i<item.monthList.length; i++)
		        {
		            cmbAddOption(month, item.monthList[i].name, item.monthList[i]);
		        }
		        cmbSelect(month, "--");
		        changeMonth();
		        month.onchange = changeMonth;
		    }
		 
		 for(var i=0; i<yearList.length; i++)
		    {
		        cmbAddOption(year, yearList[i].name, yearList[i]);
		    }
	 
	 for(var i=0; i<producerList.length; i++)
	    {
	        cmbAddOption(producerId, producerList[i].name, producerList[i]);
	    }
	    cmbSelect(producerId, 'SH');
	    changeProducer();
	    producerId.onchange = changeProducer;
	    cmbSelect(year, '--');
	    changeYear();
	    year.onchange = changeYear;
};

	var producerList = [
	{name:'BJ', channelList:[         
		{name:'HD', machineList:['QH001','QH002']},
		{name:'all', machineList:['--']},
		{name:'--', machineList:['--']}
	]},
	{name:'SH', channelList:[         
		{name:'YP', machineList:['HD001','HD002']},   
		{name:'HK', machineList:['SHJ001','SHJ002']},
		{name:'all', machineList:['--']},
		{name:'--', machineList:['--']}
	]},
	{name:'all', channelList:[         
	 	{name:'all', machineList:['all','--']},   
	 	{name:'--', machineList:['--']}
 	]},
 	{name:'--', channelList:[         
 	    {name:'--', machineList:['--']}
  	]}
	];
	
	var yearList = [
	{name:'2017', monthList:[
	    {name:'12',dayList:['1','2','3','4','5','--','all']},
	    {name:'11',dayList:['30','29','28','--','all']},
	    {name:'all',dayList:['--']},
	    {name:'--',dayList:['--']}
	    ]},
	{name:'all', monthList:[
        {name:'all', dayList:['all','--']},   
        {name:'--', dayList:['--']}
	    ]},
    {name:'--', monthList:[         
  	    {name:'--', dayList:['--']}
   	]}
	];
