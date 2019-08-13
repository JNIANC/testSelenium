package com.cjn.testSelenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cjn.testSelenium.common.commonTools;
import com.cjn.testSelenium.util.YikaSMS;

public class registerPPTV {

	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	public static boolean isMoblie = false;
	public static void startTask(final int idx) {
		if(testSelenium.taskThread[idx]!=null && testSelenium.taskThread[idx].isAlive()) {
			testSelenium.taskThread[idx].interrupt();
			testSelenium.taskThread[idx] = null;
		}
		
		testSelenium.taskThread[idx] = new Thread(new Runnable() {
			
			public void run() {
				WebDriver driver=null;
				//Actions action = null;
				try {
					ChromeOptions options = new ChromeOptions();
					boolean go = false;
					String chromeData = "C:\\work\\registerPPTV\\chrome\\data"+ idx +"\\";
					File dataFile = new File(chromeData);
					if (!dataFile.exists()) {
						dataFile.mkdirs();
					}else {
						go = commonTools.resetChrome(chromeData+"Default\\");
						System.out.println("[" + idx + "][resetChrome]"+go);
						Thread.sleep(1000*2);
					}
					if(go) {
						options.addArguments("--disable-infobars");
						System.setProperty("webdriver.chrome.driver","C:/myData/tools/Selenium/chromedriver/chromedriver.exe");		
						options.addArguments("--user-data-dir="+chromeData);	
						driver = new ChromeDriver(options);		
					}
					regiterAccount(driver);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if (driver!=null) {
						//testSelenium.times--;
						//System.out.println("times:"+testSelenium.times);
						driver.close();
						driver.quit();
					}
				}
				if (testSelenium.taskThread[idx]!=null) {
					try {
						testSelenium.taskThread[idx].interrupt();
					} catch (Exception e) {
						e.printStackTrace();
					}
					testSelenium.taskThread[idx] =null;
				}
			}
		});
		testSelenium.taskThread[idx].start();
	}
	
	public static boolean isMobile(String mobile) {
		boolean flag = false;
		if (mobile!=null && !"".equals(mobile)) {
			flag = Pattern.matches(REGEX_MOBILE, mobile);
		}
		return flag;
    }
	
	public static void regiterAccount(WebDriver driver) throws InterruptedException {
		boolean go = false;
		boolean isLogin = false;
		String code = "";
		isLogin = YikaSMS.login();
		Thread.sleep(1000*5 * (int)(Math.random()*3));
		String phoneNum = "";
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://www.pptv.com/");
			driver.manage().window().maximize();
			//driver.manage().window().setSize(new Dimension(500, 150));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
			WebElement registerButton = new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'注册')]")));
			Thread.sleep(1000);
			commonTools.retryingFindClick(registerButton, action);
			driver.switchTo().frame("newIframeLogin");//newIframeLogin
			phoneNum = YikaSMS.getPhone();
			Thread.sleep(1000*2);//
			WebElement inputPhoneNumberelement = new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='请输入手机号']")));
			Thread.sleep(1500);
			isMoblie = isMobile(phoneNum);
			if (isMoblie) {
				System.out.println(phoneNum+"是手机号码");
				inputPhoneNumberelement.sendKeys(phoneNum);
			}else
				throw new RuntimeException("不是手机号码");
			Thread.sleep(1000*2);
			WebElement inputCheckNumElement = null;
			WebElement clickCheckNumElement = new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".get-verify-code.gray")));
			go = commonTools.retryingFindClick(clickCheckNumElement, action); 
			
			//验证号码是否已经注册
			if(go) {//span[contains(text(),'该账号已注册为苏宁易购用户']
				go = commonTools.isElementExit(driver, By.cssSelector(".tip-text"), 10);//tip-text
				Thread.sleep(1000*4);
				if(go) {
					//该手机已经注册
					YikaSMS.addBlackList(phoneNum);
					System.out.println("已将["+phoneNum+"]加入黑名单");
				}else {
					if(isLogin) {
						System.out.println("platformLogin success");
						code = YikaSMS.getCode(phoneNum);
						Thread.sleep(1000*5 * (int)(Math.random()*4));
						if(code!=null && !"".equals(code)) {
							inputCheckNumElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='请输入验证码']")));
							Thread.sleep(1000*2);
							inputCheckNumElement.sendKeys(code);
							Thread.sleep(1000*2);
							WebElement pwdEelement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
							Thread.sleep(1000*2);
							pwdEelement.sendKeys("a1234567");
							Thread.sleep(1000*3);
							WebElement click = new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'注册,领新人礼包']")));
							go = commonTools.retryingFindClick(click, action);
							if(go) {//availableAcount
								File file = new File("C:\\Users\\13995\\eclipse-workspace\\testSelenium\\src\\main\\resources\\tmp\\availableAcount.txt");
								BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
								bufferedWriter.write(phoneNum);
								bufferedWriter.flush();
								bufferedWriter.close();
							}
						}else {
							YikaSMS.releasePhone(phoneNum);
							System.out.println("已将["+phoneNum+"]释放");
						}
					}else
						throw new RuntimeException("platformLogin fail"); 
				}
			}else {
				System.out.println("点击验证码按钮失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
