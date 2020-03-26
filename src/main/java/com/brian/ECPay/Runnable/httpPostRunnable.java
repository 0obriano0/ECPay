package com.brian.ECPay.Runnable;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;

public class httpPostRunnable implements Runnable{
	
	String url;
	String urlParameters;
	String encoding;
	
	public httpPostRunnable(String url, String urlParameters, String encoding){
		this.url = url;
		this.urlParameters = urlParameters;
		this.encoding = encoding;
		
	}
	
	public void run(){
		String result = EcpayFunction.httpPost(url, urlParameters, encoding);
		//System.out.println("result = " + result);
		/*result = result.substring(1,result.length()-1);
		System.out.println("get result = " + result);
		String[] datalist = result.replaceAll("\"", "").split(",");
		String urlpostvalue = "PaymentName=超商代碼&CustomerBtn=1&PaymentType=CVS";
		for(String value : datalist) {
			String[] data = value.split(":");
			System.out.println("get " + data[0] + " = " + data[1]);
			urlpostvalue = urlpostvalue + "&" + data[0] + "=" + data[1];
		}
		result = EcpayFunction.httpPost("https://payment-stage.ecpay.com.tw/Scripts/SP/ECPayPayment_1.0.0.js", urlpostvalue, encoding);
		*/
    }
}
