package com.brian.ECPay.Runnable;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.DataBase;

public class MySQLRannable implements Runnable{

	@Override
	public void run() {
		if(DataBase.mysql.pushData.size() != 0) {
			if(DataBase.mysql.pushQueue())
				ECPay.plugin.getLogger().info("成功輸入至MySQL");
			else
				ECPay.plugin.getLogger().info("輸入至MySQL失敗");
		}
	}
	

}
