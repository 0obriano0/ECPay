package com.brian.ECPay.DataBase.MySQL;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class UserInfo {
	private long MerchantTradeNo;	//我的編號
	private String UserName;		//玩家名稱
	private long TradeNo;			//綠界編號
	private int TradeAmount;		//消費金額
	private String ItemName;		//物品名稱
	private String PaymentType;		//付款類別
	private String PaymentNo;		//繳費代碼
	private Timestamp TradeDate;	//產生日期
	private Timestamp ExpireDate;	//截止日期
	private String CustomField1;	//說明
	private boolean PaySuccess;		//繳費成功與否
	
	/**
	 * 初始設定
	 * @param merchantTradeNo 我的編號
	 * @param userName 玩家名稱
	 * @param tradeNo 綠界編號
	 * @param tradeAmount 消費金額
	 * @param itemName 物品名稱
	 * @param paymentType 付款類別
	 * @param paymentNo 繳費代碼
	 * @param tradeDate 產生日期
	 * @param expireDate 截止日期
	 * @param customField1 說明
	 * @param paySuccess 繳費成功與否
	 */
	public UserInfo(long merchantTradeNo, String userName, long tradeNo, int tradeAmount, String itemName,
			String paymentType, String paymentNo, Timestamp tradeDate, Timestamp expireDate, String customField1,
			boolean paySuccess) {
		MerchantTradeNo = merchantTradeNo;
		UserName = userName;
		TradeNo = tradeNo;
		TradeAmount = tradeAmount;
		ItemName = itemName;
		PaymentType = paymentType;
		PaymentNo = paymentNo;
		TradeDate = tradeDate;
		ExpireDate = expireDate;
		CustomField1 = customField1;
		PaySuccess = paySuccess;
	}
	/**
	 * 初始設定
	 * @param uiwp UserInfoWaitPost 資料
	 * @param tradeNo 綠界編號
	 * @param paymentNo 繳費代碼
	 * @param tradeDate 產生日期
	 * @param expireDate 截止日期
	 * @param paySuccess 繳費成功與否
	 */
	public UserInfo(UserInfoWaitPost uiwp,long tradeNo,String paymentNo,Timestamp tradeDate, Timestamp expireDate,boolean paySuccess) {
		MerchantTradeNo = uiwp.getMerchantTradeNo();
		UserName = uiwp.getUserName();
		TradeNo = tradeNo;
		TradeAmount = uiwp.getTradeAmount();
		ItemName = uiwp.getItemName();
		PaymentType = uiwp.getPaymentType();
		PaymentNo = paymentNo;
		TradeDate = tradeDate;
		ExpireDate = expireDate;
		CustomField1 = uiwp.getCustomField1();
		PaySuccess = paySuccess;
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
	 * 取得綠界編號
	 * @return 綠界編號
	 */
	public long getTradeNo() {
		return TradeNo;
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
	 * 取得繳費代碼
	 * @return 繳費代碼
	 */
	public String getPaymentNo() {
		return PaymentNo;
	}
	
	/**
	 * 取得產生日期
	 * @return 產生日期
	 */
	public Timestamp getTradeDate() {
		return TradeDate;
	}
	
	/**
	 * 取得截止日期
	 * @return 截止日期
	 */
	public Timestamp getExpireDate() {
		return ExpireDate;
	}
	
	/**
	 * 取得說明
	 * @return 說明
	 */
	public String getCustomField1() {
		return CustomField1;
	}
	
	/**
	 * 取得繳費成功與否
	 * @return 繳費成功與否
	 */
	public boolean isPaySuccess() {
		return PaySuccess;
	}
	
	/**
	 * 將資料轉成Map
	 * @return Map資料
	 */
	public Map<String,String> getMap(){
		Map<String,String> data = new HashMap<String,String>();
		data.put("MerchantTradeNo",MerchantTradeNo + "");
		data.put("UserName",UserName);
		data.put("TradeNo",TradeNo + "");
		data.put("TradeAmount",TradeAmount + "");
		data.put("ItemName",ItemName);
		data.put("PaymentType",PaymentType);
		data.put("PaymentNo",PaymentNo);
		data.put("TradeDate",TradeDate.toString());
		data.put("ExpireDate",ExpireDate.toString());
		data.put("CustomField1",CustomField1);
		data.put("PaySuccess",PaySuccess? "1" : "0");
		return data;
	}
}
