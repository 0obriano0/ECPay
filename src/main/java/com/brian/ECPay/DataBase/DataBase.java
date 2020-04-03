package com.brian.ECPay.DataBase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class DataBase {
	/**
	 * 主插件類別
	 * @info 使用以此插件來做事
	 */
	public static Plugin plugin;
	
	// 設定檔
	//public static LoadConfig LoadConfig;
	
	/**
	 * 伺服器類別
	 * @info 使用以此來讀取伺服器資料
	 */
	public static Server server;
	
	/**
	 * 語言包
	 * @info 語言設定檔
	 */
	public static language language = new language();
	
	/**
	 * 插件目錄
	 * @info 插件附屬檔案的存放路徑
	 */
	public static String pluginMainDir = "./plugins/MobDrop/";
	
	//指令目錄
	//public static MainList CommandsList = new MainList();
	
	//公開顯示訊息
	//public static Config Config;
	
	/**
	 * 顯示訊息
	 * @param msg 要顯示的文字
	 * @info 在cmd 裡顯示 "[ECPay] " + msg
	 */
	public static void Print(String msg)
	{
		System.out.print("[ECPay] " + msg);
	}
	
	public static List<String> getCommands(){
    	URL jarURL = plugin.getClass().getResource("/com/brian/ECPay/Command");
    	URI uri;
		try {
			FileSystem fileSystem = null;
			uri = jarURL.toURI();
			Path myPath;
	        if (uri.getScheme().equals("jar")) {
	            fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
	            myPath = fileSystem.getPath("/com/brian/ECPay/Command");
	            
	        } else {
	            myPath = Paths.get(uri);
	        }
	        List<String> Commands = new ArrayList<String>();
	        for (Iterator<Path> it = Files.walk(myPath, 1).iterator(); it.hasNext();){
	        	String[] path = it.next().toString().split("/");
	        	
	        	String file = path[path.length - 1];
	        	if(file.matches("(.*)class$")) {
	        		file = file.split("\\.")[0];
	        		if(file.matches("^Command.*")) {
		        		String filename = file.split("Command")[1];
		        		System.out.println("get Command = " + filename);
		        		Commands.add(filename);
		        	}
	        	}
	            //System.out.println(it.next());
	        }
	        fileSystem.close();
	        return Commands;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        
    }
}
