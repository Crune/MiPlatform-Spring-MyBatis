package com.jlab.mi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import com.jlab.mi.PropDto;
import com.jlab.mi.SawonDto;

public interface EduMapper {

	@Select("select * from base_sawon where name like '%'||#{name, jdbcType=VARCHAR}||'%'")
	public List<SawonDto> findSawon(String name);
	
	@Select("select * from base_dept")
	public List<PropDto> readAllDepts();

	@Select("select * from base_jikgup")
	public List<PropDto> readAllJikgups();

	//@Insert("")
	public void insert(SawonDto dto);

	//@Update("")
	public void update(SawonDto dto);

	@Delete("delete from base_sawon where name = #{name}")
	public void delete(SawonDto dto);
}
