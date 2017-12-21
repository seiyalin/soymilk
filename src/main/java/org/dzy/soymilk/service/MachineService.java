package org.dzy.soymilk.service;

import java.io.IOException;
import java.util.List;

import org.dzy.soymilk.mapping.Machine;
import org.jdom.JDOMException;

public interface MachineService {
	
	public void insertMachine(Machine machine);
	
	public void updateMachine(Machine machine);
	
	public void deleteMachine(String machineId);
	
	public Machine getMachineById(String id);
	
	public List<Machine> getAllMachine();
	
	public List<Machine> getAllMachineByLimit(int start, int limit);
	
	int getCount();
	
	public String weixinPay(String machineId, int productId, int num) throws JDOMException, IOException;

}
