package org.dzy.soymilk.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.dzy.soymilk.dao.MachineMapper;
import org.dzy.soymilk.mapping.Machine;
import org.dzy.soymilk.utils.HttpUtil;
import org.dzy.soymilk.utils.PayConfigUtil;
import org.dzy.soymilk.utils.PayToolUtil;
import org.dzy.soymilk.utils.XMLUtil4jdom;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;


@Service("machineService")
public class MachineServiceImpl implements MachineService {
	
	@Resource
	private MachineMapper machineDao;
	String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	String[] product = new String[]{"undefined","原味热豆浆中杯","红枣味热豆浆中杯","无糖热豆浆中杯","原味冷豆浆中杯","红枣味冷豆浆中杯",
			"无糖冷豆浆中杯","原味热豆浆大杯","红枣味热豆浆大杯","无糖热豆浆大杯","原味冷豆浆大杯","红枣味冷豆浆大杯","无糖冷豆浆大杯"};
	int[] price = new int[]{0, 300, 300, 300, 300, 300, 300, 400, 400, 400, 400, 400, 400};
	
	public void insertMachine(Machine machine){
		machineDao.insert(machine);		
	}
	
	public void updateMachine(Machine machine){
		machineDao.updateByPrimaryKey(machine);
	}
	
	public void deleteMachine(String machineId){
		machineDao.deleteByPrimaryKey(machineId);
	}
	
	public Machine getMachineById(String id){
		return machineDao.selectByPrimaryKey(id);
	}
	
	public List<Machine> getAllMachine(){
		return machineDao.getAllMachine();
	}
	
	public List<Machine> getAllMachineByLimit(int start, int limit){
		return machineDao.getAllMachineByLimit(start, limit);
	}
	
	public int getCount(){
		return machineDao.getCount();
	}

	public String weixinPay(String machineId, int productId, int num) throws JDOMException, IOException{
		 String out_trade_no = "" + System.currentTimeMillis(); //订单号
		// 账号信息 
		Machine machine = machineDao.selectByPrimaryKey(machineId);
	        String appid = machine.getAppId();  // appid  
	        //String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
	        String mch_id = machine.getMchId(); // 商业号  
	        String key = machine.getApiKey(); // key  
	        
	        String currTime = PayToolUtil.getCurrTime();  
	        String strTime = currTime.substring(8, currTime.length());  
	        String strRandom = PayToolUtil.buildRandom(4) + "";  
	        String nonce_str = strTime + strRandom;  
	        String total_fee = String.valueOf(num*price[productId]);
	        
	        // 获取发起电脑 ip
	        String spbill_create_ip = machine.getCreateIp();
	        // 回调接口   
	      //  String notify_url = PayConfigUtil.NOTIFY_URL;
	        String trade_type = "NATIVE";
	          
	        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
	        packageParams.put("appid", appid);  
	        packageParams.put("mch_id", mch_id);  
	        packageParams.put("nonce_str", nonce_str);  
	        packageParams.put("body", product[productId]);  //（调整为自己的名称）
	        packageParams.put("out_trade_no", out_trade_no);  
	        packageParams.put("total_fee", total_fee); //价格的单位为分  
	        packageParams.put("spbill_create_ip", spbill_create_ip);  
	       // packageParams.put("notify_url", notify_url);  
	        packageParams.put("trade_type", trade_type);  
	  
	        String sign = PayToolUtil.createSign("UTF-8", packageParams,key);  
	        packageParams.put("sign", sign);
	          
	        String requestXML = PayToolUtil.getRequestXml(packageParams);  
	        System.out.println(requestXML);  
	   
	        String resXml = HttpUtil.postData(UFDODER_URL, requestXML);  
	        System.out.println("resXml:"+resXml);
	  
	        Map map;
			
			map = XMLUtil4jdom.doXMLParse(resXml);
							
			String urlCode = (String) map.get("code_url");
	        
			System.out.println("urlCode:"+urlCode);
	        return urlCode;  
	}
	
	public MachineMapper getMachineDao() {
		return machineDao;
	}

	public void setMachineDao(MachineMapper machineDao) {
		this.machineDao = machineDao;
	}

}
