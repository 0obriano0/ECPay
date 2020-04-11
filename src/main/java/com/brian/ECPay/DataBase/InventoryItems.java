package com.brian.ECPay.DataBase;

import com.brian.ECPay.Exception.ItemSettingfailException;

public class InventoryItems extends Items{
	
	private transient int x;
	private transient int y;
	
	public InventoryItems(int x,int y,String ItemName, String ItemRealname) throws ItemSettingfailException {
		super(ItemName, ItemRealname);
		setx(x);
		sety(y);
	}
	
	public void setx(int x) throws ItemSettingfailException{
		if(x < 0) throw new ItemSettingfailException("x need >= 0");
		this.x = x;
	}
	
	public int getx(){
		return x;
	}
	
	public void sety(int y) throws ItemSettingfailException{
		if(y < 0) throw new ItemSettingfailException("y need >= 0");
		this.y = y;
	}
	
	public int gety() {
		return y;
	}
	
}
