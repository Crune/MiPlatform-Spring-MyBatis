package com.jlab.mi;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jlab.mi.mapper.EduMapper;
import com.jlab.mi.platform.MiDTO;

@Service
@SuppressWarnings({"unchecked","rawtypes"})
public class EduService {

	@Autowired
	private EduMapper mapper;
	
	public List<SawonDto> findSawon(String name) {
		return mapper.findSawon(name);
	}
	
	public Map getInitCode() {
		Map rst = new HashMap();
		rst.put("ds_dept", mapper.readAllDepts());
		rst.put("ds_jikgup", mapper.readAllJikgups());
		return rst;
	}

	@Transactional
	public void modifySawon(MiDTO in) {
		in.exe("input", mapper, SawonDto.class, "insert", "update", "delete");
	}
}