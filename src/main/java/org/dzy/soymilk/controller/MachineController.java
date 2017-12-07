package org.dzy.soymilk.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dzy.soymilk.mapping.Machine;
import org.dzy.soymilk.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Controller
/*@RequestMapping("/soymilk")*/
@EnableWebMvc
public class MachineController {
	
	@Resource
	private MachineService machineService;
/*	private Machine machine;*/
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){   //返回主页
		System.out.println("test home page");
		return "home";
	}
	
	@RequestMapping(value="/machineManage",method=RequestMethod.GET)
	public String manageMachine(HttpServletRequest req, Model model){
		List<Machine> list = machineService.getAllMachine();
		model.addAttribute("list",list);
		return "AllMachine";
	}
	
	//test
	@RequestMapping(value="/machineManage1",method=RequestMethod.GET)
	public @ResponseBody List<Machine> manageMachine1(HttpServletRequest req, Model model){
		List<Machine> list = machineService.getAllMachine();
		model.addAttribute("list",list);
		return list;
	}
	//test
	@RequestMapping(value = "/jsonsource", method=RequestMethod.POST)
	public String jsonSource(Machine device){
		System.out.println(device);
        return "success";
	}
	
	@RequestMapping(value="/saveMachine", method=RequestMethod.POST)
	public String saveMachine(Machine mach){
		machineService.insertMachine(mach);
		return "success";
	}
	
	@RequestMapping("/showMachine")
	public String getMachine(HttpServletRequest req, Model model){
		String id = req.getParameter("id");
		Machine machine = machineService.getMachineById(id);
		model.addAttribute("machine", machine);
		System.out.println(model);
		return "showMachine";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(HttpServletRequest req){
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		JSONObject result = new JSONObject();
		if(username.equals("18817583610") && password.equals("123456"))
			result.put("success", true);
		else
			result.put("success", false);
		return result;
	}
	
	@RequestMapping("/jsp/machine_List.do")
	@ResponseBody
	public JSONObject getMachineList(HttpServletRequest req, Model model){
		List<Machine> list = machineService.getAllMachine();
		/*model.addAttribute("list",list);
		System.out.println(model);*/
		String jsonString = JSON.toJSONString(list);
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		JSONObject result = new JSONObject();
		result.put("aaData", jsonArray);
		/*System.out.println(result);*/
		return result;
	}
	
	@RequestMapping("/jsp/machine_Save.do")
	@ResponseBody
	public JSONObject MachineSave(Machine machine){
		machineService.insertMachine(machine);
		System.out.println(machine);
		JSONObject result = new JSONObject();
		result.put("success", true);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/jsp/machine_Update.do")
	@ResponseBody
	public JSONObject MachineUpdate(Machine machine){
		machineService.updateMachine(machine);
		System.out.println(machine);
		JSONObject result = new JSONObject();
		result.put("success", true);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("/jsp/machine_Delete.do")
	@ResponseBody
	public JSONObject MachineDelete(String machineIds){
		String[] ids = machineIds.split(",");
		for(String id: ids)
			machineService.deleteMachine(id);
		JSONObject result = new JSONObject();
		result.put("success", true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/qrcode.do")
	public void qrcode(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
	        String machineId = request.getParameter("machineId");
	        int productId = Integer.parseInt(request.getParameter("productId"));
	        int num = Integer.parseInt(request.getParameter("num"));
	        String text = machineService.weixinPay(machineId, productId, num); 
	        
	        model.addAttribute("wxURL", text);
	       /* int width = 300; 
	        int height = 300; 
	        //二维码的图片格式 
	        String format = "gif"; 
	        Hashtable hints = new Hashtable(); 
	        //内容所使用编码 
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	        BitMatrix bitMatrix;
			try {
				bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
				QRUtil.writeToStream(bitMatrix, format, response.getOutputStream());
			} catch (WriterException e) {
				e.printStackTrace();*/
			/*}*/
			
		} catch (Exception e) {
		}
	}

	/*public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}*/

}
