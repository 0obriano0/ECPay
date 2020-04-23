package com.brian.ECPay.DataBase.MySQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.Stack;
import com.brian.library.MySQL;

public class MySQLBase extends MySQL{
	
	public Stack<UserInfo> pushData = new Stack<UserInfo>();
	
	public Map<Integer,UserInfoWaitPost> UserInfoWaitPost = new HashMap<Integer,UserInfoWaitPost>();
	
	public MySQLBase(String USER, String PASS, String DB_URL, String db) {
		super(USER, PASS, DB_URL, db);
		if(!SelectDataBase()) {
        	runDefaultMySQL();
        }
	}
	/**
	 * 初始設定資料庫基本結構
	 */
	public void runDefaultMySQL(){
		this.CreateDataBase(ECPay.plugin.getConfig().getString("MySQL.db"));
		this.CreateTable("MerchantTradeNo", "BIGINT","Payinfo",					//我的編號
				new ArrayList<String>(Arrays.asList("UserName VARCHAR(255)",	//玩家名稱
													"TradeNo BIGINT",			//綠界編號
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
