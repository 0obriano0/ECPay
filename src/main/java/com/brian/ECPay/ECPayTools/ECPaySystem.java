package com.brian.ECPay.ECPayTools;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.MySQL.UserInfoWaitPost;
import com.brian.ECPay.Runnable.httpPostRunnable;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutCVS;
import ecpay.payment.integration.ecpayOperator.EcpayFunction;

public class ECPaySystem {
	public static AllInOne all;
	
	/**
	 * ECPaySystem
	 * 參數帶入log4j.properties的路徑，若帶入空字串則預設不產生log
	 * @param log4jURL log4j.properties的路徑
	 */
	public ECPaySystem(String log4jURL) {
		all = new AllInOne(log4jURL);
	}
	
	public void createCVSPaymentNO(String UserName,int TotalAmount,String ItemName,String TradeDesc ,String setCustomField){
		createCVSPaymentNO(DataBase.mysql.getnextNewMerchantTradeNo(), UserName, TotalAmount, ItemName, TradeDesc,setCustomField);
	}
	
	/**
	 * createCVSPaymentNO
	 * 創建一個超商代碼
	 * @param TradeNo 商品序號
	 * @param UserName (minecraft username)使用者名稱
	 * @param TotalAmount 總金額
	 * @param ItemName 物品名稱
	 * @param TradeDesc 交易描述
	 * @param setCustomField 客製化欄位
	 */
	public void createCVSPaymentNO(long TradeNo,String UserName,int TotalAmount,String ItemName,String TradeDesc ,String setCustomField){
		AioCheckOutCVS obj = new AioCheckOutCVS();
		//InvoiceObj invoice = new InvoiceObj();
		System.out.println("TradeNo = " + TradeNo+"");
		obj.setMerchantTradeNo(TradeNo+"");
		Date date_now = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		obj.setMerchantTradeDate(sdFormat.format(date_now));
		obj.setTotalAmount(TotalAmount + "");
		obj.setTradeDesc(TradeDesc);
		obj.setItemName(ItemName);
		obj.setReturnURL("http://" + ECPay.ServerIP + ":80/return.php");
		obj.setNeedExtraPaidInfo("Y");
		obj.setStoreExpireDate("1440");
		obj.setInvoiceMark("N");
		obj.setPaymentInfoURL("http://" + ECPay.ServerIP + ":80/php_post_test.php");
		obj.setChooseSubPayment("CVS");
		obj.setCustomField1(setCustomField);
		obj.setDesc_1("交易文字二");
		
		//產生一個html 來執行
		String form = getAll().aioCheckOut(obj, null);
		//產生一個 CheckMacValue 檢查碼
		String CheckMacValue =  getAll().createCheckMacValue(obj, null);
		//將資料轉換成httpValue 的資料方便 post
		String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
		//取得綠界科技的網址 以及將資料post 給他
		String createServerOrderUrl = getAll().getAioCheckOutUrl();
		System.out.println("createServerOrderUrl = " + createServerOrderUrl + "\n");
		httpPostRunnable h2 = new httpPostRunnable(createServerOrderUrl,httpValue,"UTF-8");
        Thread thr = new Thread(h2);
        thr.start();
        
        DataBase.mysql.UserInfoWaitPost.put(TradeNo, new UserInfoWaitPost(TradeNo, UserName, TotalAmount, ItemName, "CVS_CVS", setCustomField));
        
	}

	public AllInOne getAll() {
		return all;
	}
}
