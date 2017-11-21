package org.dzy.soymilk.service;

import java.util.List;

import org.dzy.soymilk.mapping.Machine;

public interface MachineService {
	
	public void insertMachine(Machine machine);
	
	public Machine getMachineById(String id);
	
	public List<Machine> getAllMachine();

}
