package com.brian.ECPay.DataBase.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.bukkit.entity.Player;

import com.brian.ECPay.ECPay;
import com.brian.library.MySQL;

public class MySQLBase extends MySQL{
	
	//public Queue<UserInfo> pushData = new LinkedList<UserInfo>(); 
	//https://stackoverflow.com/questions/17705658/nosuchelementexception-even-after-check
	//https://www.twle.cn/c/yufei/javatm/javatm-basic-blockingqueue.html
	public BlockingQueue<UserInfo> pushData = new LinkedBlockingDeque<UserInfo>();
	
	public Map<Long,UserInfoWaitPost> UserInfoWaitPost = new HashMap<Long,UserInfoWaitPost>();
	
	private Long NewMerchantTradeNo = (long) -1;
	
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
	
	/**
	 * 查詢 最後一筆 MerchantTradeNo
	 * db 使用內部設定好的
	 * 如果要更改請使用 setdb("database")
	 * @return 回傳查詢資料(-1 代表取得失敗)
	 */
	public long SelectLastTradeNo() {
		Statement stmt = null;
		ResultSet rs = null;
		long MerchantTradeNo = -1;
		
		if(conn==null) open();
		
		try{
			//Execute a query
			log("getlast MerchantTradeNo");
			stmt = conn.createStatement();
			
			String sql = "use " + db;
			stmt.executeUpdate(sql);
			
			rs = stmt.executeQuery("SELECT * FROM `payinfo` ORDER BY `payinfo`.`MerchantTradeNo` DESC limit 0,1");
			
			while(rs.next()){
		         //Retrieve by column name
				MerchantTradeNo  = rs.getLong("MerchantTradeNo");
		    }
			rs.close();
			log("command run successfully...");
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
				stmt.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return MerchantTradeNo;
	}
	
	private final String defaultnum = "0000001";
	
	/**
	 * 確保序號不重複
	 * @param checkdata 要檢查的資料
	 * @return 處理好的序號
	 */
	private long checkTradeNo(long checkdata) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String time = df.format(now);
		Long TradeNo = Long.valueOf(time.substring(2,time.length()) + defaultnum);
		return checkdata >= TradeNo ? checkdata+1 : TradeNo;
	}
	
	/**
	 * 拿取當前序號
	 * @return 序號
	 */
	public long getNewMerchantTradeNo() {
		if(NewMerchantTradeNo == -1) {
			long Selectdata = SelectLastTradeNo();
			ECPay.plugin.getLogger().info("Selectdata = " + Selectdata);
			if(Selectdata == -1) {//參考 https://blog.csdn.net/u013758116/article/details/46636609
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				Timestamp now = new Timestamp(System.currentTimeMillis());
				String time = df.format(now);
				ECPay.plugin.getLogger().info("time = " + time);
				NewMerchantTradeNo = Long.valueOf(time.substring(2,time.length()) + defaultnum);
			}else
				NewMerchantTradeNo = checkTradeNo(Selectdata);
		}
		return NewMerchantTradeNo;
	}
	
	/**
	 * 拿取當前未使用的序號
	 * @return 序號
	 */
	public long getnextNewMerchantTradeNo() {
		if(NewMerchantTradeNo == -1) {
			return getNewMerchantTradeNo();
		}
		NewMerchantTradeNo = checkTradeNo(NewMerchantTradeNo);
		return NewMerchantTradeNo;
	}
	
	/**
	 * 將資料 push 至 MySQL
	 * @return 有沒有成功
	 */
	public boolean pushQueue() {
		boolean success = false;
		//try {
			UserInfo data = pushData.poll();
			success = this.Insert("payinfo", data.getMap());
			if(success && ECPay.server.matchPlayer(data.getUserName()).size() == 1) {
				Player player = ECPay.server.getPlayer(data.getUserName());
				player.sendMessage("資料成功創建 請打開系統查看");
			}
		//}catch(Exception e) {
		//	ECPay.plugin.getLogger().info("data create fail before pushData size = " + pushData.size());
		//	pushData.remove();
		//	ECPay.plugin.getLogger().info("data create fail after pushData size = " + pushData.size());
		//	return success;
		//}
		return success;
	}
}
