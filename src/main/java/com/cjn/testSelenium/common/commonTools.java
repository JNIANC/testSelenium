package com.cjn.testSelenium.common;

import java.awt.dnd.DragGestureRecognizer;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class commonTools {

	/*
	 * 截图
	 */
	public static void screenShoot(String fileName,WebDriver driver) {
		System.out.println("-----into screenShoot----");
		String dirName = "C:\\myData\\tmp\\screenshot";
		File dirFile = new File(dirName);
		if(!dirFile.exists() && !dirFile.isDirectory()) {
			dirFile.mkdirs();
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TakesScreenshot sfDriver = (TakesScreenshot) driver;
		File imageFile = new File(dirName + File.separator+ sf.format(new Date()) + "_" + fileName==null?"":fileName + ".png");
		sfDriver.getScreenshotAs(OutputType.FILE).renameTo(imageFile);
		System.out.println("-----screenShoot end-----");
	}
	
	 public static boolean resetChrome(String path){	
		    boolean result=true;
			File dir=new File(path);
	        if(dir.exists()){
	            File[] tmp=dir.listFiles();
	            for(int i=0;i<tmp.length;i++){
	            	if(!"Cache".equals(tmp[i].getName())) {
	            		 if(tmp[i].isDirectory()){
	 	                	resetChrome(path+"/"+tmp[i].getName());
	 	                }
	 	                else{
	 	                	result=tmp[i].delete();	  
	 	                	if(result==false) {
	 	                		System.out.println("[resetChrome][error]"+tmp[i].getPath()+"/"+tmp[i].getName());
	 	                		return false;            		
	 	                	}
	 	                }
	            	}
	            }
	            dir.delete();
	        }
	        return true; 
	 }
	 
	 public static boolean retryingFindClick(WebElement element,Actions action) {
		    boolean result = false;
		    int attempts = 0;
		    while(attempts < 2) {
		        try {
		        	action.moveToElement(element).click().perform();
		        	System.out.println("点击成功");
		            result = true;
		            break;
		        } catch(StaleElementReferenceException e) {
		        	System.out.println("点击失败"+attempts);
		        }
		        attempts++;
		    }
		    return result;
		}
	 
	 public static boolean isElementExit(WebDriver driver,By locator,int timeoutSeconds) {
		 driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
		 try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException  e) {
			return false;
	  }
	}	
	
	 public WebElement getElement(WebDriver driver,Locator locator,String text)
	            throws IOException {
	        locator = new Locator();
	        WebElement element;
	        switch (locator.getByType()) {
	        case xpath:
	        	element = driver.findElement(By.xpath(text));
	            break;
	        case id:
	        	element = driver.findElement(By.id(text));
	            break;
	        case name:
	        	element = driver.findElement(By.name(text));
	            break;
	        case cssSelector:
	        	element = driver.findElement(By.cssSelector(text));
	            break;
	        case className:
	        	element = driver.findElement(By.className(text));
	            break;
	        case tagName:
	        	element = driver.findElement(By.tagName(text));
	            break;
	        case linkText:
	        	element = driver.findElement(By.linkText(text));
	            break;
	        case partialLinkText:
	        	element = driver.findElement(By.partialLinkText(text));
	            break;
	        default:
	        	element = driver.findElement(By.id(text));
	        }
	        return element;
	    }
	
}
