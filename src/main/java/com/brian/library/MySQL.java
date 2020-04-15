package com.brian.library;

import java.sql.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySQL {
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private String DB_URL;
	
	//  Database credentials
	private String USER;
	private String PASS;
	private String db;
   
	private Connection conn = null;
	
//	/**
//	 * 分析DB_URL中的主連結
//	 * @return
//	 */
//	private String getMainDB_URL() {
//		String reg = "jdbc:mysql://[^/].+/";
//
//		//將規則封裝成物件
//		Pattern p = Pattern.compile(reg);
//		
//		//讓正則物件與要作用的字串相關聯
//		Matcher m = p.matcher(DB_URL);
//		  
//		//將規則作用到字串上, 並進行符合規則的子串查找
//		m.find();
//		//System.out.println("找到得連結是" + m.group());
//		return m.group();
//	}
	/**
	 * MySQL 基本資料設定(會順便開啟連結)
	 * @param USER 帳號
	 * @param PASS 密碼
	 * @param DB_URL 連結網址
	 * @param db 數據庫
	 */
	public MySQL(String USER,String PASS,String DB_URL,String db) {
		this.USER = USER;
		this.PASS = PASS;
		this.DB_URL = DB_URL;
		this.db = db;
		this.open();
	}
   
    /**
     * 開啟與MySQL連結
     * @return 有沒有成功
     */
	public boolean open() {
		try{
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//Open a connection
			//System.out.println("Connecting to database...");
			this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			return false;
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			return false;
		}
		return true;
	}
   /**
    * 關閉與MySQL連結
    * @return 有沒有成功
    */
	public boolean close(){
		try{
			if(conn!=null)
				conn.close();
			conn = null;
			return true;
		}catch(SQLException se){
			se.printStackTrace();
		}
		return false;
   }
   
    /**
     * 創建一個數據庫
     * @param DataBaseName 數據庫名稱
     */
	public void CreateDataBase(String DataBaseName) {
		Statement stmt = null;
		if(conn==null) open();
	   
		try{
			//Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE " + DataBaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
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
	}
	
	/**
     * 刪除一個數據庫
     * @param DataBaseName 數據庫名稱
     */
	public void DelectDataBase(String DataBaseName) {
		Statement stmt = null;
		if(conn==null) open();
	   
		try{
			//Execute a query
			System.out.println("Deleting database...");
			stmt = conn.createStatement();
			String sql = "DROP DATABASE " + DataBaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database deleted successfully...");
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
	}
	
	/**
	 * 選擇數據庫
	 */
	public boolean SelectDataBase() {
		Statement stmt = null;
		if(conn==null) open();
	   
		try{
			//Execute a query
			System.out.println("Deleting database...");
			stmt = conn.createStatement();
			String sql = "use " + db;
			System.out.println("SelectDataBase = " + stmt.executeUpdate(sql));
			System.out.println("Database deleted successfully...");
		}catch(SQLSyntaxErrorException mse) {
			return false;
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
		return true;
	}
	
	/**
	 * 創建一個資料表
	 * @param PRIMARY_KEY
	 * @param PRIMARY_key_Type
	 * @param table
	 */
	public void CreateTable(String PRIMARY_KEY,String PRIMARY_key_Type,String tableName,List<String> table) {
		Statement stmt = null;
		if(conn==null) open();
	   
		try{
			//Execute a query
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			
			String sql = "use " + db;
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE " + tableName + " (" + PRIMARY_KEY + " " + PRIMARY_key_Type + " not NULL, ";
			for(String value : table) sql = sql + value + ", ";
			sql = sql + "PRIMARY KEY ( " + PRIMARY_KEY +" ))";
			
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database successfully...");
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
	}
}
