package com.brian.ECPay.ECPayTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.Runnable.httpPostRunnable;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutCVS;
import ecpay.payment.integration.ecpayOperator.EcpayFunction;

public class ECPaySystem {
	public static AllInOne all;
	
	/**
	 * ECPaySystem
	 * 參數帶入log4j.properties的路徑，若帶入空字串則預設不產生log
	 * @param log4jPropertiesPath
	 */
	public ECPaySystem(String log4jURL) {
		all = new AllInOne(log4jURL);
	}
	
	/**
	 * createCVSPaymentNO
	 * 創建一個超商代碼
	 * @param log4jPropertiesPath
	 */
	public static void createCVSPaymentNO(String UserName,String TotalAmount,String ItemName){
		AioCheckOutCVS obj = new AioCheckOutCVS();
		//InvoiceObj invoice = new InvoiceObj();
		UUID uid = UUID.randomUUID();
		obj.setMerchantTradeNo(uid.toString().replaceAll("-", "").substring(0, 20));
		Date date_now = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		obj.setMerchantTradeDate(sdFormat.format(date_now));
		obj.setTotalAmount(TotalAmount);
		obj.setTradeDesc("test Description");
		obj.setItemName(ItemName);
		obj.setReturnURL("http://" + ECPay.ServerIP + ":80/return.php");
		obj.setNeedExtraPaidInfo("Y");
		obj.setStoreExpireDate("1440");
		obj.setInvoiceMark("N");
		obj.setPaymentInfoURL("http://" + ECPay.ServerIP + ":80/php_post_test.php");
		obj.setClientRedirectURL("http://" + ECPay.ServerIP + ":" + ECPay.port + "/ClientRedirectURL");
		obj.setChooseSubPayment("CVS");
		
		//產生一個html 來執行
		//String form = all.aioCheckOut(obj, null);
		//產生一個 CheckMacValue 檢查碼
		String CheckMacValue =  all.createCheckMacValue(obj, null);
		//將資料轉換成httpValue 的資料方便 post
		String httpValue = EcpayFunction.genHttpValue(obj, CheckMacValue);
		//取得綠界科技的網址 即將資料post 給他
		String createServerOrderUrl = all.getAioCheckOutUrl();
		httpPostRunnable h2 = new httpPostRunnable(createServerOrderUrl,httpValue,"UTF-8");
        Thread thr = new Thread(h2);
        thr.start();
	}
}
