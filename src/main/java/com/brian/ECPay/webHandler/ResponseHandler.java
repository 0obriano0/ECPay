package com.brian.ECPay.webHandler;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.brian.ECPay.DataBase.DataBase;

import ecpay.payment.integration.domain.CVSOrBARCODEClientRequestObj;
import ecpay.payment.integration.domain.CVSOrBARCODEPaymentInfoURLObj;
import example.ExampleAllInOne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    		System.out.println("取得回饋，拿取交易代碼(PaymentInfoURL)");
    		CVSOrBARCODEPaymentInfoURLObj obj = (CVSOrBARCODEPaymentInfoURLObj) DataBase.ecpaySystem.getAll().aioCheckOutFeedback(httpServletRequest);
    		System.out.println("交易代碼: " + obj.getPaymentNo());
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
