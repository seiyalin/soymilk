package org.dzy.soymilk.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dzy.soymilk.mapping.Machine;
import org.dzy.soymilk.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/*@RequestMapping("/soymilk")*/
public class MachineController {
	
	@Resource
	private MachineService machineService;
	
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
	
	@RequestMapping(value="/saveMachine", method=RequestMethod.POST)
	@ResponseBody
	public String saveMachine(@RequestBody Machine machine){
		machineService.insertMachine(machine);
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

}
