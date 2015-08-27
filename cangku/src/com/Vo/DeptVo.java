package com.Vo;

public class DeptVo {
	public int id;
	public String deptname;
	public String deptmaster;
	public String desc;
	public String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptmaster() {
		return deptmaster;
	}
	public void setDeptmaster(String deptmaster) {
		this.deptmaster = deptmaster;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DeptVo [id=" + id + ", deptname=" + deptname + ", deptmaster="
				+ deptmaster + ", desc=" + desc + ", status=" + status + "]";
	}
	

}
