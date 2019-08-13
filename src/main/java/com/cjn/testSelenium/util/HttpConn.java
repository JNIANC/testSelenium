package com.cjn.testSelenium.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpConn {
	public static String UA="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
	public static String Accept="image/gif, image/x-xbitmap, image/jpeg,application/json, text/javascript, */*";
	public static String Referer=null;
	public static String Cookies=null;
	public static HashMap<String,String> CookiesMap=new HashMap<String,String>();

	public static String sendGet(String path){
		return sendGet(path,false);
	}
	public static String sendGet(String path,boolean p){
		String content=null;
		HttpURLConnection urlcon;
		try{
			URL url = new URL(path);
			if(p){
				InetSocketAddress addr = new InetSocketAddress("10.0.0.172",80);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
				urlcon = (HttpURLConnection)url.openConnection(proxy);
			}
			else{
				urlcon = (HttpURLConnection)url.openConnection();
			}

	        try{
	        	String cookies=Cookies==null?getCookies():Cookies;
        		urlcon.setRequestProperty("User-Agent",UA);
	        	urlcon.setRequestProperty("Accept",Accept);
	        	urlcon.setRequestProperty("Accept-Language", "zh-cn");
	            urlcon.setRequestProperty("Content-type", "text/html");
	            urlcon.setRequestProperty("Cookie", cookies);
	            if(Referer!=null){
	            	urlcon.setRequestProperty("Referer", Referer);
	            }
	            
		        urlcon.setConnectTimeout(10000);
		        urlcon.setReadTimeout(10000);
		        urlcon.connect();         //获取连接
		        InputStream is = urlcon.getInputStream();
		        
		        
		      //获取Cookies
		        Map<String,List<String>> map=urlcon.getHeaderFields();
		        for (Entry<String, List<String>> entry : map.entrySet()) {  
		        	if(("Set-Cookie").equalsIgnoreCase(entry.getKey())){
		        		List<String> list=entry.getValue();
			        	 for(String value:list){
			        		 String cookie=value.split(";")[0];
			        		 CookiesMap.put(cookie.split("=")[0], cookie.split("=")[1]);
			        	 }
		        	}
		        }  
		        
		        if(path.indexOf("/migugraph?")>0){
		        	int BUFFER_SIZE = 1024;  
			        byte[] buf = new byte[BUFFER_SIZE];  
			        int size = 0;  
			        BufferedInputStream bis = new BufferedInputStream(is);  
			        String savePath="/sdcard/yzm.jpeg";
			        FileOutputStream fos = new FileOutputStream(savePath);
			        while ((size = bis.read(buf)) != -1) {  
			        fos.write(buf, 0, size);  
			        }  
			        fos.flush(); 
			        fos.close();
		        }
		        else{
		        	BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"utf-8"));
			        StringBuffer bs = new StringBuffer();
			        String l = null;
			        while((l=buffer.readLine())!=null){
			            bs.append(l);
			        }
			        content=bs.toString();
			        buffer.close();
		        }
	        }
	        catch(Exception ex){ 
				ex.printStackTrace(); 				
				content=null;
			} 
	        finally{				
				urlcon.disconnect();				
			}
	        
		}
		catch(Exception e){ 
			e.printStackTrace(); 			
			content=null;
		} 
		
		
		return content;
	}
	
	  public static String sendPost(String url, Map<String, String> paramMap) {  
	        PrintWriter out = null;  
	        BufferedReader in = null;  
	        String result = "";  
	        try {  
	        	String cookies=Cookies==null?getCookies():Cookies;
	            URL realUrl = new URL(url);  
	            // 打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            // 设置通用的请求属性 
	            conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");  
	            conn.setRequestProperty("Connection", "Keep-Alive"); 
	            conn.setRequestProperty("User-Agent",UA);  
	            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
	            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
	            conn.setRequestProperty("Origin", "https://passport.migu.cn");
	            conn.setRequestProperty("Pragma", "no-cache");
	            conn.setRequestProperty("Accept-Encoding", "deflate");
	            conn.setRequestProperty("Cookie", cookies);
	            if(Referer!=null){
	            	conn.setRequestProperty("Referer", Referer);
	            }	            
	            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
	            
	            // 发送POST请求必须设置如下两行    
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            //获取URLConnection对象对应的输出流 
	            out = new PrintWriter(conn.getOutputStream());  
	  
	            // 设置请求属性
	            String param = "";  
	            if (paramMap != null && paramMap.size() > 0) {  
	                Iterator<String> ite = paramMap.keySet().iterator();  
	                while (ite.hasNext()) {  
	                    String key = ite.next();// key  
	                    String value = paramMap.get(key);  
	                    param += key + "=" + value + "&";  
	                }  
	                param = param.substring(0, param.length() - 1);  
	            }  
	            // 发送请求参数   
	            out.print(param);  
	            // flush输出流的缓冲    
	            out.flush();  
	            // 定义BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader( new InputStreamReader(conn.getInputStream()));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	            
	            //获取Cookies
		        Map<String,List<String>> map=conn.getHeaderFields();
		        for (Entry<String, List<String>> entry : map.entrySet()) {  
		        	if(("Set-Cookie").equalsIgnoreCase(entry.getKey())){
		        		List<String> list=entry.getValue();
			        	 for(String value:list){
			        		 String cookie=value.split(";")[0];
			        		 CookiesMap.put(cookie.split("=")[0], cookie.split("=")[1]);
			        	 }
		        	}		          
		        }  
	            
	            
	        } catch (Exception e) {  
	            System.err.println("发送 POST 请求出现异常！" + e);  
	            e.printStackTrace();  
	        }  
	        // 使用finally块来关闭输出流、输入流  
	        finally {  
	            try {  
	                if (out != null) {  
	                    out.close();  
	                }  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (IOException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	  
	  
	  public static String getCookies(){
		  String output=null;
		  for (Entry<String, String> entry : CookiesMap.entrySet()) { 	
			  if(output==null)output="";
			  output+=entry.getKey().trim()+"="+entry.getValue().trim()+";";
		  }
	     return output;
	  }
	  
		public static String URLContent(String path){
			String content=null;
			HttpURLConnection urlcon;
			try{
				URL url = new URL(path);
		        urlcon = (HttpURLConnection)url.openConnection();
		        try{
		        		        	
			        urlcon.setConnectTimeout(3000);
			        urlcon.setReadTimeout(3000);
			        urlcon.setRequestProperty("accept", "*/*");
			        urlcon.setRequestProperty("connection", "Keep-Alive");
			        urlcon.addRequestProperty("Content-Type", "text/html;charset=UTF-8");
			        urlcon.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			        urlcon.connect();         //获取连接
			        InputStream is = urlcon.getInputStream();
			        BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"utf-8"));
			        StringBuffer bs = new StringBuffer();
			        String l = null;
			        while((l=buffer.readLine())!=null){
			            bs.append(l);
			        }
			        content=bs.toString();
			        buffer.close();
			      
		        }
		        catch(Exception ex){ 
					ex.printStackTrace(); 				
					content=null;
				} 
		        finally{				
					urlcon.disconnect();				
				}
		        
			}
			catch(Exception e){ 
				e.printStackTrace(); 			
				content=null;
			} 
			
			
			return content;
		}
	public static String getRandomUA(){

		String tempate="Mozilla/5.0 (Linux; U; Android %s; en-us; %s Build/%s) AppleWebKit/%s (KHTML, like Gecko) Version/4.0 Chrome/%s  Mobile Safari/%s";
		String[] release={"4.4.2","5.1.1","5.1.2","6.0.1","4.4.4","5.2.1","5.0.1","4.2.2","4.4.3"};
		String[] product={"HUAWEI VIE-AL10","SM-G9350","Redmi","SM-A9000","MI 4LTE","OPPO r9","OPPO r11","M040","MI 3LTE","MI 5","MI 4S","SM-G9250","GT-I9500","MX5","OPPO R7","LG-D857","max1"};
		String[] version={"MMB29M","MMB29K","LMY47V","KTU84M","KTU84P","LMY47V","LRX21T","KOT49H","JRO03H"};
		String[] code1={"53.0.2785.146","50.0.2661.89","40.0.2214.89","32.0.1700.99","53.0.1879.86","48.0.2564.116","53.0.2785.49","55.0.2883.91","31.0.1650.59"};
		String[] code2={"537.36","537.36","537.36","537.36","534.30","537.36","534.30","537.36","537.36"};
		int ran=(int)(Math.random()*version.length);

		String userAgent = String.format(tempate,
				release[(int)(Math.random()*release.length)],
				product[(int)(Math.random()*product.length)],
				version[(int)(Math.random()*version.length)],
				code2[ran],
				code1[(int)(Math.random()*code1.length)],
				code2[ran]);

		return userAgent;
	}
}
