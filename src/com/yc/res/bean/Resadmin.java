package com.yc.res.bean;

import java.io.Serializable;


public class Resadmin implements Serializable {

	private int raid;
	private String raname;
	private String rapwd;

	public int getRaid() {
		return raid;
	}

	public void setRaid(int raid) {
		this.raid = raid;
	}

	public String getRaname() {
		return raname;
	}

	@Override
	public String toString() {
		return "Resadmin [raid=" + raid + ", raname=" + raname + ", rapwd=" + rapwd + "]";
	}

	public void setRaname(String raname) {
		this.raname = raname;
	}

	public String getRapwd() {
		return rapwd;
	}

	public void setRapwd(String rapwd) {
		this.rapwd = rapwd;
	}

}
