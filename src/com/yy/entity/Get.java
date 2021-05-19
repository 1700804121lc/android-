package com.yy.entity;

public class Get {
	private int gid;
	private int uid;
	private int yid;
	private int num;
	private Yao yao;

	public Yao getYao() {
		return yao;
	}

	public void setYao(Yao yao) {
		this.yao = yao;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getYid() {
		return yid;
	}

	public void setYid(int yid) {
		this.yid = yid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Get(int gid, int uid, int yid, int num) {
		super();
		this.gid = gid;
		this.uid = uid;
		this.yid = yid;
		this.num = num;
	}

	public Get() {
		// TODO Auto-generated constructor stub
	}

}
