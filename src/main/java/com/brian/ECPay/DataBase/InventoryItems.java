package com.brian.ECPay.DataBase;

import com.brian.ECPay.Exception.ItemSettingfailException;

public class InventoryItems extends Items{
	
	private transient int x;
	private transient int y;
	
	/**
	 * 創建基本資料
	 * @param x 座標x
	 * @param y 座標y
	 * @param ItemName 物品名稱
	 * @param ItemRealname 物品的typename
	 * @throws ItemSettingfailException 物品設定錯誤
	 */
	public InventoryItems(int x,int y,String ItemName, String ItemRealname) throws ItemSettingfailException {
		super(ItemName, ItemRealname);
		setx(x);
		sety(y);
	}
	
	/**
	 * 設定 X
	 * @param x 位置
	 * @throws ItemSettingfailException 設定失敗
	 */
	public void setx(int x) throws ItemSettingfailException{
		if(x < 0) throw new ItemSettingfailException("x need >= 0");
		this.x = x;
	}
	
	/**
	 * 拿取座標 X
	 * @return x
	 */
	public int getx(){
		return x;
	}
	
	/**
	 * 設定 Y
	 * @param y 位置
	 * @throws ItemSettingfailException 設定失敗
	 */
	public void sety(int y) throws ItemSettingfailException{
		if(y < 0) throw new ItemSettingfailException("y need >= 0");
		this.y = y;
	}
	
	/**
	 * 拿取座標 Y
	 * @return y
	 */
	public int gety() {
		return y;
	}
	
}
