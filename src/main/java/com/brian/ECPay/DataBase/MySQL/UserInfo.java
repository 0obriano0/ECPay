package com.brian.ECPay.DataBase.MySQL;

public class UserInfo {
	private String UserName;
	private int TotalAmount;
	private String ItemName;
	private String TradeDesc;
	private boolean PaySuccess;
	
	/**
	 * 初始設定
	 * @param UserName 使用者名稱
	 * @param TotalAmount 金額
	 * @param ItemName 商品名稱
	 * @param TradeDesc 敘述
	 * @param PaySuccess 有沒有繳費成功
	 */
	public UserInfo(String UserName,int TotalAmount,String ItemName,String TradeDesc,boolean PaySuccess){
		this.UserName = UserName;
		this.TotalAmount = TotalAmount;
		this.ItemName = ItemName;
		this.TradeDesc = TradeDesc;
		this.PaySuccess = PaySuccess;
	}
	
	public String getUserName() {
		return this.UserName;
	}
	
	public int getTotalAmount() {
		return this.TotalAmount;
	}
	
	public String getItemName() {
		return this.ItemName;
	}
	
	public String getTradeDesc() {
		return this.TradeDesc;
	}
	
	public boolean getPaySuccess() {
		return this.PaySuccess;
	}
}
