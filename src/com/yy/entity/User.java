package com.yy.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/23.
 */
public class User implements Serializable {
	private int uid, qid;
	private String user;// 用户名
	private String pswd;// 密码

	private String name;// 姓名

	private String phone;// 电话

	private String years;// 年龄

	private String sex;// 性别

	private String sumary, type;// 
	private String qname = "";
	 private String icon = "";

	    public String getIcon() {
	        return icon;
	    }

	    public void setIcon(String icon) {
	        this.icon = icon;
	    }
	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getSumary() {
		return sumary;
	}

	public void setSumary(String sumary) {
		this.sumary = sumary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public User(int uid, int qid, String user, String pswd, String name,
			String phone, String years, String sex, String sumary, String type) {
		super();
		this.uid = uid;
		this.qid = qid;
		this.user = user;
		this.pswd = pswd;
		this.name = name;
		this.phone = phone;
		this.years = years;
		this.sex = sex;
		this.sumary = sumary;
		this.type = type;
	}

	

}
