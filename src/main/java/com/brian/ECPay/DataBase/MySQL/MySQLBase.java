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
		DataBase.mysql.CreateTable("TradeNo", "INTEGER","Payinfo",
				new ArrayList<String>(Arrays.asList("UserName VARCHAR(255)",
													"TotalAmount INTEGER",
													"ItemName VARCHAR(255)",
													"TradeDesc VARCHAR(255)",
													"PaySuccess TINYINT(1)")));
	}
}
