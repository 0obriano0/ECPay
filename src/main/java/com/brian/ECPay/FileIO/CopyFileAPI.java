﻿package com.brian.ECPay.FileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.brian.ECPay.AnsiColor;
import com.brian.ECPay.DataBase.DataBase;

public class CopyFileAPI {
	public static void copyFile(InputStream in, File out) throws Exception {
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
            	fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
	
	public static boolean createFile(String Dir,String Filename,String JarURL) {
		Path p = Paths.get(Dir);    //路徑設定
        /*確認資料夾是否存在*/
        if (!Files.exists(p)) {
            /*不存在的話,直接建立資料夾*/
            try {
				Files.createDirectory(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DataBase.plugin.getLogger().info(AnsiColor.GREEN + "[DirCreate] " + AnsiColor.GREEN +  "資料夾創建成功" + AnsiColor.RESET);
        }
		File QuestMaker = new File(Dir + Filename);
        if (!QuestMaker.exists()) {
            InputStream jarURL = DataBase.plugin.getClass().getResourceAsStream(JarURL);
            try {
            	copyFile(jarURL, new File(Dir + Filename));
            	return true;
            } catch (Exception ex) {
            	ex.printStackTrace();
            	return false;
            }
        }
        return false;
	}
}
