package com.user.servlet;

public class Commodity {
	int shoppingCarID;// 购物蓝ID
	String imagePath; // 食物图片
	String foodInfo;// 食物信息
	String unitPrice;// 单价
	int foodCount;// 食物数量
	public Commodity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commodity(int shoppingCarID, String imagePath, String foodInfo,
			String unitPrice, int foodCount) {
		super();
		this.shoppingCarID = shoppingCarID;
		this.imagePath = imagePath;
		this.foodInfo = foodInfo;
		this.unitPrice = unitPrice;
		this.foodCount = foodCount;
	}
	@Override
	public String toString() {
		return "Commodit [购物蓝ID=" + shoppingCarID + ", 食物图片=" + imagePath
				+ ", 食物信息=" + foodInfo + ", 单价=" + unitPrice + ", 食物数量="
				+ foodCount + "]";
	}

	public int getShoppingCarID() {
		return shoppingCarID;
	}
	public void setShoppingCarID(int shoppingCarID) {
		this.shoppingCarID = shoppingCarID;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getFoodInfo() {
		return foodInfo;
	}
	public void setFoodInfo(String foodInfo) {
		this.foodInfo = foodInfo;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	
}
