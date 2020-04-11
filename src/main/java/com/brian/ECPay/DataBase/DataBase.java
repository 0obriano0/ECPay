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

import org.bukkit.plugin.Plugin;

public class DataBase {
	
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
	private static List<String> Commands = null;
	
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
	
	/**
	 * 抓取指令列表(/ecpay <列表資料>)
	 * @return
	 */
	public static List<String> getCommands(Plugin plugin){
		if(Commands == null) {
			Commands = new ArrayList<String>();
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
		        for (Iterator<Path> it = Files.walk(myPath, 1).iterator(); it.hasNext();){
		        	String[] path = it.next().toString().split("/");
		        	
		        	String file = path[path.length - 1];
		        	if(file.matches("(.*)class$")) {
		        		file = file.split("\\.")[0];
		        		if(file.matches("^Command.*")) {
			        		String filename = file.split("Command")[1];
			        		Commands.add(filename);
			        	}
		        	}
		            //System.out.println(it.next());
		        	Collections.sort(Commands);
		        }
		        fileSystem.close();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Commands;
        
    }
}
