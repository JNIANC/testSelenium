package com.cjn.task.video.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class CommonUtil {

	public static String result = null;
	
	/**
	 * 通过接口，下载json文件
	 * @param url
	 * @param filePath
	 * @throws IOException 
	 */
	public static void download(String url,String filePath){
		HttpURLConnection httpURLConnection = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			URL httpUrl = new URL(url);
			URLConnection urlConnection = httpUrl.openConnection();
			if(urlConnection instanceof HttpURLConnection)
				httpURLConnection = (HttpURLConnection) urlConnection;
			else {
				System.out.println("请输入url");
				return;
			}
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.connect();
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			String line = null;
			while((line = bufferedReader.readLine())!=null) {
				result = line;
				bufferedWriter.write(line);
				
			}
			System.out.println("文件下载完成");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件下载失败: \n"+e.getMessage());
		}finally {
			if(bufferedWriter!=null) {
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(httpURLConnection!=null) {
				httpURLConnection.disconnect();
			}
		}
	}
}
