package com.yy.entity;

public class YiSheng {
	private int ysid;
	private int yid;
	private int kid;
	private int gua;
	private int statu;
	private String yname;
	private String kname;
	private String name;
	private String icon;
	private int isgua;// 是否已挂号 0没挂号 1已挂号
	String time; //挂号时间
	String jianjie;//简介
	String techang;//特长
	String user,pswd;
	
	
	
	

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

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}

	public String getTechang() {
		return techang;
	}

	public void setTechang(String techang) {
		this.techang = techang;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	public int getIsgua() {
		return isgua;
	}

	public void setIsgua(int isgua) {
		this.isgua = isgua;
	}

	public int getYsid() {
		return ysid;
	}

	public void setYsid(int ysid) {
		this.ysid = ysid;
	}

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

	public int getGua() {
		return gua;
	}

	public void setGua(int gua) {
		this.gua = gua;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public YiSheng(int ysid, int yid, int kid, int gua, String name, String icon,String jianjie,String techang) {
		super();
		this.ysid = ysid;
		this.yid = yid;
		this.kid = kid;
		this.gua = gua;
		this.name = name;
		this.icon = icon;
		this.jianjie=jianjie;
		this.techang=techang;
	}

	public YiSheng() {
		super();
	}

}
