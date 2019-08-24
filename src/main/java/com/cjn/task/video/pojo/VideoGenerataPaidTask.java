package com.cjn.task.video.pojo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cjn.task.video.util.CommonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.json.JSONObject;




public class VideoGenerataPaidTask {

	private static String downloadPath = "D:\\myData\\paidTask\\videoTask\\download.json";
	private static String ablumListPath = "D:\\myData\\paidTask\\videoTask\\ablumList.json";
	private static String ablumInterfaceUrl = "https://c.musicapp.migu.cn/MIGUM2.0/v1.0/content/querycontentbyId.do?ua=Android_migu&version=5.0&needAll=0&columnId=15037157";
	private static String resultPath = "D:\\myData\\paidTask\\videoTask\\result.txt";
	//private static Map<String, MusicAlbumBean> albumMap = new HashMap<String, MusicAlbumBean>();
	private static List<VideoBean> alList = new ArrayList<VideoBean>();
	
	public static void main(String[] args){
		CommonUtil.download(ablumInterfaceUrl,downloadPath);
		getAlumList(CommonUtil.result,ablumListPath);
		setPay(80,5.2f);
		//getJson("https://c.musicapp.migu.cn/MIGUM2.0/v1.0/content/querycontentbyId.do?ua=Android_migu&version=5.0&needSimple=1&columnId=22685686");
	}
	
	public static void test() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("cat", "it");
		JsonArray array = new JsonArray();
		JsonObject lan1 = new JsonObject();
		lan1.addProperty("id", 1);
		lan1.addProperty("ide","elipse");
		lan1.addProperty("name","Java");
		array.add(lan1);
		JsonObject lan2 = new JsonObject();
		lan2.addProperty("id", 2);
		lan2.addProperty("ide","XCode");
		lan2.addProperty("name","Swift");
		array.add(lan2);
		jsonObject.add("languages", array);
		jsonObject.addProperty("pop", true);
		System.out.println(jsonObject.toString());
	}
	
	/**
	 * 获取音乐专辑需要的json
	 * @param str
	 * @param filePath
	 */
	public static void getAlumList(String str,String filePath) {
		JsonArray contentsObject = null;
		BufferedWriter bufferedWriter = null;
		try {
			JsonParser jsonParser = new JsonParser();
			JsonObject object =(JsonObject) jsonParser.parse(str);
			object = (JsonObject) object.get("columnInfo");
			contentsObject = object.get("contents").getAsJsonArray();
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
			VideoBean videoBean = null;
			ObjectInfo objectInfo = null;
			int dataCount = 0;
			System.out.println("-------albumList.json:-----");
			for (int i = 0; i < contentsObject.size(); i++) {
				JsonObject subObject = contentsObject.get(i).getAsJsonObject();
				if(subObject.toString().contains("singer") && subObject.toString().contains("itemId") && subObject.toString().contains("price") && subObject.toString().contains("title")) {
					videoBean =new VideoBean();
					objectInfo = new ObjectInfo();
					object =(JsonObject) new JsonParser().parse(subObject.toString());
					object = (JsonObject) object.get("objectInfo");
					objectInfo.setPay_content_id(object.get("itemId").getAsString());
//					objectInfo.setPay_price(Float.parseFloat(object.get("price").getAsString())/100+"");
					videoBean.setObjectInfo(objectInfo);
					//albumMap.put(objectInfo.getItemId(), uAlbumBean);
					alList.add(videoBean);
					//System.out.println("objectInfo: "+objectInfo.getItemId());
					bufferedWriter.write(JSONObject.fromObject(objectInfo)+"\n");
					dataCount++;
					System.out.println("subObject: "+JSONObject.fromObject(objectInfo));
				}
			}
			System.out.printf("----albumList.dataCount=%d---",dataCount);
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (bufferedWriter!=null) {
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 设置付费数量及金额
	 * @param num 需要付费的数量
	 * @param averagePrice 单个付费的平均价格
	 */
	public static void setPay(int num,float averagePrice) {
		BufferedWriter bufferedWriter = null;
		int count = 0;
		VideoBean videoBean = null;
		float alumPrice = 0f,sum = 0f;
		int dataCount = 0;
		System.out.println("------result.txt:-----");
		try {
			File file = new File(resultPath);
			if(file.exists())
				file.delete();
			file.createNewFile();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			for (int i = 1; i < 20000; i++) {
				if (i==19999) {
					System.out.println("-------没拿到想要的数据------");
					System.exit(-1);
				}
				if(i==1)
					bufferedWriter.write("[");
				for (int j = 0; j < alList.size(); j++) {
					videoBean = alList.get(new Random().nextInt(alList.size()));
//					alumPrice =Float.parseFloat(videoBean.getObjectInfo().getPay_price());
					try {
						bufferedWriter.write(JSONObject.fromObject(videoBean.getObjectInfo())+(i<num?",":""));
						dataCount++;
						System.out.println(JSONObject.fromObject(videoBean.getObjectInfo()));
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(i==num) {
					bufferedWriter.write("]");
					System.out.printf("------success|dataCount=%d|sum=%f------",dataCount,sum);
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (bufferedWriter!=null) {
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void getJson(String url) {
		BufferedReader bufferedReader = null;
		HttpURLConnection httpURLConnection = null;
		JsonParser jsonParser = new JsonParser();
		try {
			URL httpUrl = new URL(url);
			URLConnection urlConnection = httpUrl.openConnection();
			if (urlConnection instanceof HttpURLConnection) {
				httpURLConnection = (HttpURLConnection) urlConnection;
			}else {
				System.out.println("请输入url");
				return;
			}
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.connect();
			String line = null;
			StringBuffer sb = new StringBuffer();
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
			while((line = bufferedReader.readLine())!=null) {
				sb.append(line);
			}
			JsonObject object = (JsonObject) jsonParser.parse(sb.toString());
			JsonObject columnInfoObject = object.get("columnInfo").getAsJsonObject();
			JsonArray contents = columnInfoObject.get("contents").getAsJsonArray();
			
			JsonElement price = null;
			for (int i = 0; i <contents.size(); i++) {
				price = contents.get(i).getAsJsonObject().get("objectInfo").getAsJsonObject().get("price");
				if (price!=null) {
					System.out.println(price.getAsString());
				}
			}
			//System.out.println(sb);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (httpURLConnection!=null) {
				httpURLConnection.disconnect();
			}
		}
	}
}
