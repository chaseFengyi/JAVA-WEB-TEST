package com.mymuji.model;

public class MujiInfoBean {
	private int mujiID;
	private String accountNum;
	private String password;
	public int getMujiID() {
		return mujiID;
	}
	public void setMujiID(int mujiID) {
		this.mujiID = mujiID;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "MujiInfoBean [mujiID=" + mujiID + ", accountNum=" + accountNum
				+ ", password=" + password + "]";
	}
	
}
