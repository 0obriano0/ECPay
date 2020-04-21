package com.brian.ECPay.DataBase.MySQL;

import java.util.ArrayList;
import java.util.Arrays;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.Stack;

public class MySQLBase {
	public Stack<UserInfo> data = new Stack<UserInfo>();
	
	public static void runDefaultMySQL(){
		DataBase.mysql.CreateDataBase(ECPay.plugin.getConfig().getString("MySQL.db"));
		DataBase.mysql.CreateTable("MerchantTradeNo", "INTEGER","Payinfo",		//我的編號
				new ArrayList<String>(Arrays.asList("UserName VARCHAR(255)",	//玩家名稱
													"TradeNo INTEGER",			//綠界編號
													"TradeAmount INTEGER",		//消費金額
													"ItemName VARCHAR(255)",	//物品名稱
													"PaymentType VARCHAR(255)",	//付款類別
													"PaymentNo VARCHAR(255)",	//繳費代碼
													"TradeDate TIMESTAMP",		//產生日期
													"ExpireDate TIMESTAMP",		//截止日期
													"CustomField1 VARCHAR(255)",//說明
													"PaySuccess TINYINT(1)"))); //繳費成功與否
	}
}
