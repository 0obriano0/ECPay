package com.brian.ECPay.webHandler;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.MySQL.UserInfo;

import ecpay.payment.integration.domain.CVSOrBARCODEClientRequestObj;
import ecpay.payment.integration.domain.CVSOrBARCODEPaymentInfoURLObj;
import example.ExampleAllInOne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class ResponseHandler extends AbstractHandler {

    public ResponseHandler(){

    }

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
    	response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");

        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();
    	if(s.equals("/PaymentInfoURL")) {
    		ECPay.plugin.getLogger().info("取得回饋，拿取交易代碼(PaymentInfoURL)");
    		CVSOrBARCODEPaymentInfoURLObj obj = (CVSOrBARCODEPaymentInfoURLObj) DataBase.ecpaySystem.getAll().aioCheckOutFeedback(httpServletRequest);
    		long getkey = Long.valueOf(obj.getMerchantTradeNo());
    		if(DataBase.mysql.UserInfoWaitPost.get(getkey).isSame(obj)) {
    			DataBase.mysql.pushData.offer(new UserInfo(DataBase.mysql.UserInfoWaitPost.get(getkey),Long.valueOf(obj.getTradeNo()),obj.getPaymentNo(),Timestamp.valueOf(obj.getTradeDate().replaceAll("/", "-")), Timestamp.valueOf(obj.getExpireDate().replaceAll("/", "-")),false));
    			DataBase.mysql.UserInfoWaitPost.remove(getkey);
    			ECPay.plugin.getLogger().info("交易代碼: " + obj.getPaymentNo());
    		}else {
    			ECPay.plugin.getLogger().info("PaymentInfo get fail [Trade id = ]" + obj.getMerchantTradeNo());
    		}
    		
    	}else if(s.equals("/ReturnURL")){
    		System.out.println("取得回饋，繳費完成");
    	}else if(s.equals("/ClientRedirectURL")) {
    		System.out.println("取得回饋，拿取交易代碼(/ClientRedirectUR)");
    		CVSOrBARCODEClientRequestObj obj = (CVSOrBARCODEClientRequestObj) DataBase.ecpaySystem.getAll().aioCheckOutFeedback(httpServletRequest);
        	System.out.println("交易代碼: " + obj.getPaymentNo());
    	}else {
    		System.out.println("接收到莫名的資訊");
    		System.out.println("s = " + s);
    		System.out.println("httpServletRequest.toString() = " + httpServletRequest.toString());
    	}
        
    	baseRequest.setHandled(true);
    }
}
