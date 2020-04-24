package com.brian.ECPay.DataBase.MySQL;

import java.util.HashMap;
import java.util.Map;

import ecpay.payment.integration.domain.CVSOrBARCODEPaymentInfoURLObj;

public class UserInfoWaitPost {
	private long MerchantTradeNo;	//我的編號
	private String UserName;		//玩家名稱
	private int TradeAmount;		//消費金額
	private String ItemName;		//物品名稱
	private String PaymentType;		//付款類別
	private String CustomField1;	//說明
	private int count = 60;			//計數

	/**
	 * 初始設定
	 * @param merchantTradeNo 我的編號
	 * @param userName 玩家名稱
	 * @param tradeAmount 消費金額
	 * @param itemName 物品名稱
	 * @param paymentType 付款類別
	 * @param customField1 說明
	 */
	public UserInfoWaitPost(long merchantTradeNo, String userName, int tradeAmount, String itemName, String paymentType, String customField1) {
		super();
		MerchantTradeNo = merchantTradeNo;
		UserName = userName;
		TradeAmount = tradeAmount;
		ItemName = itemName;
		PaymentType = paymentType;
		CustomField1 = customField1;
	}
	
	/**
	 * 檢查回傳的資料友們有跟系統一樣
	 * @param obj
	 * @return true or false
	 */
	public boolean isSame(CVSOrBARCODEPaymentInfoURLObj obj) {
		return obj.getMerchantTradeNo().equals(MerchantTradeNo+"") && obj.getTradeAmt().equals(TradeAmount+"") &&
				obj.getPaymentType().equals(PaymentType) && obj.getCustomField1().equals(CustomField1);
	}
	
	/**
	 * 將資料以map型態輸出
	 * @return 以map型態輸出
	 */
	public Map<String,String> toMap(){
		Map<String,String> data = new HashMap<String,String>();
		data.put("MerchantTradeNo", MerchantTradeNo+"");
		data.put("userName", UserName);
		data.put("TradeAmount", TradeAmount+"");
		data.put("ItemName", ItemName);
		data.put("PaymentType", PaymentType);
		data.put("CustomField1", CustomField1);
		data.put("count", count+"");
		return data;
	}
	
	/**
	 * 取得自產生的編號
	 * @return 自產生的編號
	 */
	public long getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	
	/**
	 * 取得玩家名稱
	 * @return 玩家名稱
	 */
	public String getUserName() {
		return UserName;
	}
	
	/**
	 * 取得消費金額
	 * @return 消費金額
	 */
	public int getTradeAmount() {
		return TradeAmount;
	}
	
	/**
	 * 取得物品(商品)名稱
	 * @return 物品(商品)名稱
	 */
	public String getItemName() {
		return ItemName;
	}
	
	/**
	 * 取得付款類別
	 * @return 付款類別
	 */
	public String getPaymentType() {
		return PaymentType;
	}
	
	/**
	 * 取得說明
	 * @return 說明
	 */
	public String getCustomField1() {
		return CustomField1;
	}
	
	/**
	 * 取得計數
	 * @return 取得計數
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * 設定計數
	 * @param count 計數值
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
