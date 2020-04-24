package com.brian.ECPay.Runnable;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.brian.ECPay.ECPay;
import com.brian.ECPay.DataBase.DataBase;
import com.brian.ECPay.DataBase.MySQL.UserInfoWaitPost;

public class MySQLRannable implements Runnable{

	@Override
	public void run() {
		if(DataBase.mysql.pushData.size() != 0) {
			if(DataBase.mysql.pushQueue())
				ECPay.plugin.getLogger().info("成功輸入至MySQL");
			else
				ECPay.plugin.getLogger().info("輸入至MySQL失敗");
		}
		//會產生 ConcurrentModificationException 參考資料https://blog.csdn.net/baidu_37107022/article/details/73555034
//		for(Entry<Long, UserInfoWaitPost> data:DataBase.mysql.UserInfoWaitPost.entrySet()) {
//			int count = data.getValue().getCount();
//			if(data.getValue().getCount() <= 1)
//				DataBase.mysql.UserInfoWaitPost.remove(data.getKey());
//			else
//				data.getValue().setCount(count-1);
//		}
		
		Set<Entry<Long, UserInfoWaitPost>> set = DataBase.mysql.UserInfoWaitPost.entrySet();
		Iterator<Entry<Long, UserInfoWaitPost>> iterator=set.iterator();
		
        while(iterator.hasNext()){
            Entry<Long, UserInfoWaitPost> entry=iterator.next();
            
            int count = entry.getValue().getCount();
            
			if(entry.getValue().getCount() <= 1) {
				ECPay.plugin.getLogger().info("超時刪除 id： " + entry.getValue().getMerchantTradeNo());
				iterator.remove();
			}else
				entry.getValue().setCount(count-1);
        }
		
	}
	

}
