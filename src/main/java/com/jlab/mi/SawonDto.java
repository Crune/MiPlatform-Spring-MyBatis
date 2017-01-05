package com.jlab.mi;

import org.apache.ibatis.type.Alias;

@Alias("sdto")
public class SawonDto {
	
	private String name;
	private String sabun;
	private String dept;
	private String jikgup;
	private String sex;
	private String ipsa_date;
	private String marry;
	private String email;
	private String smemo;
	

	public SawonDto() {
	}
	
	public SawonDto(String name, String sabun, String dept, String jikgup, String sex, String ipsa_date, String marry,
			String email, String smemo) {
		super();
		this.name = name;
		this.sabun = sabun;
		this.dept = dept;
		this.jikgup = jikgup;
		this.sex = sex;
		this.ipsa_date = ipsa_date;
		this.marry = marry;
		this.email = email;
		this.smemo = smemo;
	}

	@Override
	public String toString() {
		return "Sawon [name=" + name + ", sabun=" + sabun + ", dept=" + dept + ", jikgup=" + jikgup + ", sex=" + sex
				+ ", ipsa_date=" + ipsa_date + ", marry=" + marry + ", email=" + email + ", smemo=" + smemo + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSabun() {
		return sabun;
	}
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJikgup() {
		return jikgup;
	}
	public void setJikgup(String jikgup) {
		this.jikgup = jikgup;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIpsa_date() {
		return ipsa_date;
	}
	public void setIpsa_date(String ipsa_date) {
		this.ipsa_date = ipsa_date;
	}
	public String getMarry() {
		return marry;
	}
	public void setMarry(String marry) {
		this.marry = marry;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSmemo() {
		return smemo;
	}
	public void setSmemo(String smemo) {
		this.smemo = smemo;
	}
	
}
