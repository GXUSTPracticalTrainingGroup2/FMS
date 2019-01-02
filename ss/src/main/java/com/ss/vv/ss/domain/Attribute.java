package com.ss.vv.ss.domain;

import java.util.Date;

public class Attribute implements java.io.Serializable {
	private int aId, count;
	private float price, weight;
	private String aName;
	private Date expDate, proDate;
	public int getAId() {
		return aId;
	}
	public void setAId(int aId) {
		this.aId = aId;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void seteExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public Date getProDate() {
		return proDate;
	}
	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}
	public String getAName() {
		return aName;
	}
	public void setAName(String aName) {
		this.aName = aName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
}