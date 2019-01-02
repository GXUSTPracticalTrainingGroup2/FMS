package com.ss.vv.ss.domain;

public class Restaurant implements java.io.Serializable {
	private int rId, rPhone,stars;
	private String address;
	public int getRId() {
		return rId;
	}
	public void setRId(int rId) {
		this.rId = rId;
	}
	public int getRPhone() {
		return rPhone;
	}
	public void setRPhone(int rPhone) {
		this.rPhone = rPhone;
	}
	public int getStar() {
		return stars;
	}
	public void setStar(int stars) {
		this.stars = stars;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}