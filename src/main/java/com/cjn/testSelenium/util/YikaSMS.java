package com.cjn.testSelenium.util;

import java.util.ArrayList;


public class YikaSMS{
	public static String LoginUser="imsdk88";
	public static String LoginPwd="imsdk13579";
	public static String Token=null;
	public static String PID="21783";
	public static ArrayList<String> PhoneList=new ArrayList<String>();
	
	
	
	public void setLoginUser(String user) {
		LoginUser=user;
	}

	
	public void setLoginPwd(String pwd) {
		LoginPwd=pwd;
		
	}

	public void setPID(String pid) {
		PID=pid;		
	}

	public static boolean login() {
		String url="http://xapi.yika66.com/User/login?uName=%s&pWord=%s";
		String request = String.format(url,LoginUser,LoginPwd);
		String result = HttpConn.URLContent(request);	
		//Log.WriteFile("["+getPlatform()+"][login]"+result);
		if (result!=null) {
			if (result.indexOf("&")>0) {
				String[] strings = result.split("&");
				Token=strings[0];
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	
	public static String getPhone() {
		String output=null;
		
		if(PhoneList.size()==0){
			Boolean loginBoolean = login();
			if (loginBoolean) {
				String url="http://xapi.yika66.com/User/getPhone?ItemId="+PID+"&token="+Token+"&Count=1";
				String result = HttpConn.URLContent(url);
				
				if(result!=null && result.length()>=11){
				
						String[] list=result.split(";");
						for(String phone:list){
							PhoneList.add(phone);						
						}
						if(PhoneList.size()>0){
							output=PhoneList.get(0);
							PhoneList.remove(0);
						}
					
				}				
			}
			else {
				System.out.println("登陆失败");
			}
		}
		else{
			output=PhoneList.get(0);
			PhoneList.remove(0);
		}
		
		
		return output;
	}

	
	public static String getCode(String phone) {
		String url="http://xapi.yika66.com/User/getMessage?token="+Token+"&ItemId="+PID+"&Phone="+phone;
		String result = HttpConn.URLContent(url);
		if (result!=null) {
			//Log.WriteFile("---result captcha="+result);
			if(result.indexOf("验证码")>0){
				int startIdx=result.indexOf("验证码")+3;
				result=result.substring(startIdx, result.indexOf(", ",startIdx));
				if(result.length()==6){
					return result;	
				}
				else{
					result=null;
				}
			}
			else{
				result=null;
			}			
			
			return result;			
		}		
		return null;
	}

	
	public static String addBlackList(String phone) {
		String url="http://xapi.yika66.com/User/addBlack?token="+Token+"&phoneList="+PID+"-"+phone;
		String result = HttpConn.URLContent(url);
		if (result!=null) {
			return result;			
		}		
		return null;
	}

	
	public String getPlatform() {		
		return "yika";
	}

	
	public static void releasePhone(String phone,int type) {
		String url="http://xapi.yika66.com/User/releasePhone?token="+Token+"&phoneList="+PID+"-"+phone;
		String result = HttpConn.URLContent(url);
		
	}
	
	public static void releasePhone(String phone) {
		String url="http://xapi.yika66.com/User/releasePhone?token="+Token+"&phoneList="+PID+"-"+phone;
		String result = HttpConn.URLContent(url);
		
	}
	
	public void syncSuccess(String phone,String pwd) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean checkPhone(String phone) {	
		
		return true;
		
	}
	

}
