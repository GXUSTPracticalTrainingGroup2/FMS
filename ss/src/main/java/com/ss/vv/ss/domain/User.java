package com.ss.vv.ss.domain;

public class User implements java.io.Serializable {
	private int u_id,u_phone;
	private String u_name,u_password,u_sex,u_mail,u_level;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getU_phone() {
		return u_phone;
	}
	public void setU_phone(int u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_mail() {
		return u_mail;
	}
	public void setU_mail(String u_mail) {
		this.u_mail = u_mail;
	}
	public String getU_level() {
		return u_level;
	}
	public void setU_level(String u_level) {
		this.u_level = u_level;
	}

}
