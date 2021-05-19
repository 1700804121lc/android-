package com.yy.entity;

public class Keshi {
	private int kid;
	private int yid;
	private String name;
	
	

	public int getYid() {
		return yid;
	}

	public void setYid(int yid) {
		this.yid = yid;
	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Keshi(int kid, int yid, String name) {
		super();
		this.kid = kid;
		this.yid = yid;
		this.name = name;
	}

	public Keshi() {
		super();
	}

}
