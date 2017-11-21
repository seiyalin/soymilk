package org.dzy.soymilk.dao;

import java.util.List;

import org.dzy.soymilk.mapping.Machine;

public interface MachineMapper {
    int deleteByPrimaryKey(String machineId);

    int insert(Machine record);

    int insertSelective(Machine record);
    
    List<Machine> getAllMachine();

    Machine selectByPrimaryKey(String machineId);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);
}