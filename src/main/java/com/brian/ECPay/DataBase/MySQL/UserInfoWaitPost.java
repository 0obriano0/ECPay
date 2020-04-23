package com.brian.ECPay.DataBase.MySQL;

public class UserInfoWaitPost {
	private int MerchantTradeNo;	//我的編號
	private String UserName;		//玩家名稱
	private int TradeAmount;		//消費金額
	private String ItemName;		//物品名稱
	private String PaymentType;		//付款類別
	private String CustomField1;	//說明
	
	/**
	 * 初始設定
	 * @param merchantTradeNo 我的編號
	 * @param userName 玩家名稱
	 * @param tradeAmount 消費金額
	 * @param itemName 物品名稱
	 * @param paymentType 付款類別
	 * @param customField1 說明
	 */
	public UserInfoWaitPost(int merchantTradeNo, String userName, int tradeAmount, String itemName, String paymentType,
			String paymentNo, String customField1) {
		super();
		MerchantTradeNo = merchantTradeNo;
		UserName = userName;
		TradeAmount = tradeAmount;
		ItemName = itemName;
		PaymentType = paymentType;
		CustomField1 = customField1;
	}

	/**
	 * 取得自產生的編號
	 * @return 自產生的編號
	 */
	public int getMerchantTradeNo() {
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
	
}
