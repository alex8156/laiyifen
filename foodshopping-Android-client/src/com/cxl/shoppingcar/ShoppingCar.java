package com.cxl.shoppingcar;

public class ShoppingCar {
	private	int shoppingCarID;//购物蓝ID
	private boolean isSelected; //删除
	private String imagePath; //食物图片
	private String foodInfo;//食物信息
	private String unitPrice;//单价
	private String decrease;//数量减少
	private int foodCount;//食物数量
	private	String increase;//数量增加
	public int getShoppingCarID() {
		return shoppingCarID;
	}
	public void setShoppingCarID(int shoppingCarID) {
		this.shoppingCarID = shoppingCarID;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
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
	public String getDecrease() {
		return decrease;
	}
	public void setDecrease(String decrease) {
		this.decrease = decrease;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	public ShoppingCar(int shoppingCarID, boolean isSelected, String imagePath,
			String foodInfo, String unitPrice, String decrease, int foodCount,
			String increase) {
		super();
		this.shoppingCarID = shoppingCarID;
		this.isSelected = isSelected;
		this.imagePath = imagePath;
		this.foodInfo = foodInfo;
		this.unitPrice = unitPrice;
		this.decrease = decrease;
		this.foodCount = foodCount;
		this.increase = increase;
	}
	@Override
	public String toString() {
		return "ShoppingCar [shoppingCarID=" + shoppingCarID + ", isSelected="
				+ isSelected + ", imagePath=" + imagePath + ", foodInfo="
				+ foodInfo + ", unitPrice=" + unitPrice + ", decrease="
				+ decrease + ", foodCount=" + foodCount + ", increase="
				+ increase + "]";
	}	
}
