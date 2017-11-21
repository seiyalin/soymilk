package org.dzy.soymilk.service;

import java.util.List;

import javax.annotation.Resource;

import org.dzy.soymilk.dao.MachineMapper;
import org.dzy.soymilk.mapping.Machine;
import org.springframework.stereotype.Service;

@Service("machineService")
public class MachineServiceImpl implements MachineService {
	
	@Resource
	private MachineMapper machineDao;
	
	public void insertMachine(Machine machine){
		machineDao.insert(machine);		
	}
	
	public Machine getMachineById(String id){
		return machineDao.selectByPrimaryKey(id);
	}
	
	public List<Machine> getAllMachine(){
		return machineDao.getAllMachine();
	}

	
	public MachineMapper getMachineDao() {
		return machineDao;
	}

	public void setMachineDao(MachineMapper machineDao) {
		this.machineDao = machineDao;
	}

}
