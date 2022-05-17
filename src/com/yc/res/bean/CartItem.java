package com.yc.res.bean;

import java.io.Serializable;

public class CartItem implements Serializable {  //序列化

	private static final long serialVersionUID = 5289673702604344227L;

	private Resfood food;
	private int num;
	private double smallCount;

	public double getSmallCount() {
		this.smallCount = num * food.getRealprice();
		return smallCount;
	}

	public Resfood getFood() {
		return food;
	}

	public void setFood(Resfood food) {
		this.food = food;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
