package com.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.google.gson.JsonObject;

public class test {
	public static void main(String[] args) {
		/*float price = 0;
		price = Float.parseFloat("￥2".replace("￥", ""));
		System.out.println(price);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("pay_content_id",'1');
		jsonObject.addProperty("song_name","12");
		jsonObject.addProperty("actual_price",'1');
		jsonObject.addProperty("buy_times",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println(jsonObject.toString());*/
		//int a = -35;
		/*System.out.println("{\"status:\":\"购买失败\",\"result:\":\"++\"}");
		System.out.println("立即支付5元".substring(4, 6).replace("元",""));*/
		/*int a = new Random().nextInt(5);
		int b = 4;
		for (int i = 0; i < 10; i++) {
			if(i>b) {
				if(i>a) {
					if(i>5) {
						System.out.println("a:"+a+",i:"+i);
						break;
					}
				}
			}
			System.out.println("1");
		}*/
		if("用具粤语".contains("用具")) {
			System.out.println("相同");
		}
	}
}
