package com.cjn.testSelenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cjn.testSelenium.common.commonTools;

public class autoStudy_ZK {
	private static int writeIdNum =0;
	public static Thread[] taskThread = null;
	
	//By.id("").findElement(By.xpath(""))
	public static void startTask(final int idx) {
		
		if(testAutoStudy.taskThread[idx]!=null && testAutoStudy.taskThread[idx].isAlive()) {
			testAutoStudy.taskThread[idx].interrupt();
			testAutoStudy.taskThread[idx] = null;
		}
		
		testAutoStudy.taskThread[idx] = new Thread(new Runnable() {
			public  void run() {
				WebDriver driver=null;
				Actions action = null;
				int[] randReadTimes = new int[]{313000,304030,305333,305060,306091,310903,307903};
				try {
					ChromeOptions options = new ChromeOptions();
					boolean go = false;
					String chromeData="C:\\work1\\chrome\\data" + idx + "\\";
					File dataFile = new File(chromeData);
					if (!dataFile.exists()) {
						dataFile.mkdirs();
					}
					else {
						go=commonTools.resetChrome(chromeData+"Default\\");
						System.out.println("[" + idx + "][resetChrome]"+go);
						Thread.sleep(1000*2);
					}
					
					if(go) {
						//options.addArguments("user-agent=" + ua);
						options.addArguments("--disable-infobars");
						System.setProperty("webdriver.chrome.driver","D:/myData/tools/Selenium/chromedriver/chromedriver.exe");		
						options.addArguments("--user-data-dir="+chromeData);
						options.addArguments("--disable-features=EnableEphemeralFlashPermission");
						Map<String, Object> prefs = new HashMap<String, Object>();
						/*prefs.put("profile.managed_default_content_settings.images", 1);
						prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);*/
						prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player",1);
						options.setExperimentalOption("prefs", prefs);
			    		//options.addArguments("--proxy-server=" + proxyItem.getString("host") + ":"+ proxyItem.getString("port"));
			    		//File plugPath = new File("C:\\work\\web\\HttpRequest.crx");
			    		//options.addExtensions(plugPath);
						driver = new ChromeDriver(options);		
					}
					/*int ran;
					if(idx<=0) {
						ran = 1000*5+ (int)(Math.random()*10);
					}else
						ran = 1000*4 + (int)(Math.random()*10);
					driver.manage().timeouts().pageLoadTimeout(ran, TimeUnit.SECONDS);*/
					
					//System.setProperty("webdriver.chrome.driver", "C:/myData/tools/Selenium/chromedriver/chromedriver.exe");
					//driver = new ChromeDriver();
					//driver.manage().deleteAllCookies();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					//driver.get("http://202.192.88.103/");
					//driver.get("http://px.yanxiu.com/2019nh_5003/index.html");
					driver.get("http://www.yanxiu.com/");
					driver.manage().window().maximize();
					//driver.navigate().refresh();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					action = new Actions(driver);
					try {
						Thread.sleep(1000*5);
						//WebElement uesernameElement = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='formExam']/div[1]/div/input")));
						WebElement uesernameElement = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='user']")));
						Thread.sleep(800);
						//uesernameElement.sendKeys("201721114133");
						uesernameElement.sendKeys("441721199203084544");
						Thread.sleep(500);
						/*WebElement passwordElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='formExam']/div[2]/div/input")));
						passwordElement.sendKeys("123456");*/
						WebElement passwordElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pass']")));
						passwordElement.sendKeys("084544");
						Thread.sleep(800);
						new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='submit']"))).click();
						Thread.sleep(1000*5);
						/*new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.linkText("个人中心"))).click();
						Thread.sleep(1000*8);*/
						System.out.println("等待时间过了");
						//new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.className("folded"))).click();
						((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
						Thread.sleep(1000*5);																	  
						new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='timeline']/div[7]/div[2]/div[3]"))).click();
						Thread.sleep(1000*3); ////*[@id="timeline"]/div[7]/ul/li[1]/text()
						new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='timeline']/div[7]/ul/li[1]"))).click();;
						Thread.sleep(1000*3); ////*[@id="module-201"]/div[3]/ul/li[1]/a/div/p
						new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='module-201']/div[3]/ul/li[1]/a/div/p"))).click();
						Thread.sleep(1000*3);
						//Screen screen = new Screen();
						for (int i = 0; i < 100000000; i++) {
							new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/ul/li[2]/span/em"))).click();
							Thread.sleep(1000*3); //微课学习
							new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/ul/li[2]/a"))).click();
							Thread.sleep(1000*3); ////*[@id="vjs-container-flash-train-player"]/div/div[2]/div[2]/div[5]/span/label[2]
							WebElement videoTime = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='vjs-container-flash-train-player']/div/div[2]/div[2]/div[5]/span/label[2]")));
							System.out.println("videoTime"+videoTime);
							String videoDurationString = videoTime+"";
							String[] videoBeforeTime = videoDurationString.split("\\:");
							int videoBeforeDuration = Integer.parseInt(videoBeforeTime[0])* 60000;
							Thread.sleep(videoBeforeDuration);
							System.out.println("看了"+i+"个视频，它的时间为："+videoBeforeDuration);
							new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='vjs-container-flash-train-player']/div/div[2]/div[2]/div[3]/a/span[2]"))).click();
							Thread.sleep(3000);
						}
					} catch (Exception e) {
						System.out.println(" [" + idx + "][Exception]" + e.getMessage());
					}
					
					//operator();
					/*driver.findElement(By.id("J_AccountPsd")).sendKeys("14799372068");
					new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("J_AccountPsd")));
					driver.findElement(By.id("J_PasswordPsd")).sendKeys("a1234567");
					new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("J_AccountPsd")));*/
					
					//driver.navigate().to("http://baidu.com");
					
					/*driver.findElement(By.id("kw")).sendKeys("周树人");
					
					driver.findElement(By.id("su")).click();*/
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					/*if (driver != null) {
						driver.close();
						driver.quit();
					}*/
				}
				//终止线程
				if(testAutoStudy.taskThread[idx]!=null){
					try {	
						Thread.currentThread().interrupt();
					} 
					catch (Exception e) {										
						e.printStackTrace();
					}
					testAutoStudy.taskThread[idx]=null;
				}	
			}
		});
		testAutoStudy.taskThread[idx].start();
	}

		
		
		public static void operator(WebDriver driver) throws InterruptedException {
			WebElement userNicknameElement;
			Actions action =null;
			try {
				Thread.sleep(3000);
				//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				//commonTools.screenShoot("登陆", driver);
				userNicknameElement = new WebDriverWait(driver,35).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='userNickname']")));
				userNicknameElement.click();
				Thread.sleep(2000);
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='J-mygk']"))).click();
				Thread.sleep(4000);
				driver.findElement(By.linkText("视频管理")).click();
				Thread.sleep(500);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				driver.navigate().refresh();
				Thread.sleep(1500);
				////*[@id="c01"]/div[2]/div[2]/ul/li[2]
				//WebElement nextPagElement = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='next']")));
				//javascriptExecutor.executeScript("arguments[0].scrollIntoView();", nextPagElement);//*[@id="c01"]/div[2]/div[2]/ul/li[1]
				WebElement maxIndexElement = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='next']/preceding-sibling::li[1]")));
				System.out.println("该账号的视频页数： "+maxIndexElement.getText());
				int maxIndex = Integer.parseInt(maxIndexElement.getText());
				Thread.sleep(3000);
				List<WebElement> elements = null;
				WebElement liElement =null;
				int numbers = 0;
				loop:for (int i = 1; i <=maxIndex; i++) {
					Thread.sleep(1000*2);
					elements = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='c01']/div[2]/div[2]/ul/li")));
					numbers = elements.size();
					System.out.println("该页视频数: "+ numbers);
					for(int j=1;j<=numbers;j++) {
						if(j>numbers)
							break;
						liElement = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='c01']/div[2]/div[2]/ul/li"+"["+j+"]")));
						if(liElement !=null) {
							String data = liElement.getAttribute("data-id");
							if( data !=null && !"null".equals(data)) {
								data =data.format("%s,%s %n",testAutoStudy.userCount,data);
								System.out.print(data);
								try {
									File targetFile = new File("C:\\Users\\13995\\eclipse-workspace\\testSelenium\\src\\main\\resources\\tmp\\count.txt");
									BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile,true));
									bufferedWriter.write(data);
									bufferedWriter.close();
									writeIdNum++;
								} catch (Exception e) {
									e.printStackTrace();
								}
								//System.out.printf("%s,%s %n",userCount,data);
							}
							
						}
						if(writeIdNum>=30) {
							System.out.println("~success~writeIdNum="+writeIdNum);
							//driver.quit();
							break loop;
						}
					}
					action = new Actions(driver);
					action.moveToElement(new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='next']")))).click().build().perform();
					Thread.sleep(2000);
					//driver.navigate().refresh();
					//Thread.sleep(1000);
					if(i==maxIndex ) {
						System.out.println("~success~writeIdNum="+writeIdNum);
						//driver.quit();
						break;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}
