package com.ss.vv.ss.domain;

public class Restaurant implements java.io.Serializable {
	private int rid, stars, uid;
	private String address, rphone, rname;
	
	Attribute attribute;
	
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	public int getrid() {
		return rid;
	}
	public void setrid(int rid) {
		this.rid = rid;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getuid() {
		return uid;
	}
	public void setuid(int uid) {
		this.uid = uid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	public String getrname() {
		return rname;
	}
	public void setrname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "Restaurant [rid=" + rid + ", stars=" + stars + ", uid=" + uid + ", address=" + address + ", rphone="
				+ rphone + ", rname=" + rname + "]";
	}
	
	


}