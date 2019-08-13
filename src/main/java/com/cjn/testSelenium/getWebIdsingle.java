package com.cjn.testSelenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cjn.testSelenium.common.commonTools;

public class getWebIdsingle {
	private static int writeIdNum =0;

	public static void startTask(final int idx) {
		
		if(testSelenium.taskThread[idx]!=null && testSelenium.taskThread[idx].isAlive()) {
			testSelenium.taskThread[idx].interrupt();
			testSelenium.taskThread[idx] = null;
		}
		
		testSelenium.taskThread[idx] = new Thread(new Runnable() {
			public  void run() {
				WebDriver driver=null;
				Actions action = null;
				try {
					ChromeOptions options = new ChromeOptions();
					boolean go = false;
					String chromeData="C:\\work\\chrome\\data" + idx + "\\";
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
						System.setProperty("webdriver.chrome.driver","C:/myData/tools/Selenium/chromedriver/chromedriver.exe");		
						options.addArguments("--user-data-dir="+chromeData);	
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
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.get("http://www.miguvideo.com/mobiletv.jsp");
					driver.manage().window().maximize();
					//driver.navigate().refresh();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					action = new Actions(driver);
					//driver.get("https://192.168.3.18/vsphere-client/?csp");
					//WebElement loginButton = driver.findElement(By.className("loginButton"));
					WebElement loginButton = new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'登录')]")));
					Thread.sleep(2000);
					//loginButton.click();
					//action.moveToElement(loginButton).click().perform();
					commonTools.retryingFindClick(loginButton,action);
					try {
						Thread.sleep(1000*5);
						System.out.println("等待时间过了");
						WebElement iframeElement = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='loginIframe']")));
						try {
							driver.switchTo().frame("loginIframe");
							System.out.println("第一种");
						}catch (StaleElementReferenceException e) {
							iframeElement = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='loginIframe']")));
							driver.switchTo().frame(iframeElement);//*[@id="loginIframe"]
							System.out.println("第二种");
						}
						Thread.sleep(1000*3);
						testSelenium.uesernameElement = new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J_AccountPsd']")));
						testSelenium.uesernameElement.sendKeys(testSelenium.userCount);
						Thread.sleep(500);
						testSelenium.passwordElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J_PasswordPsd']")));
						testSelenium.passwordElement.sendKeys(testSelenium.password);
						Thread.sleep(500);
						new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='登录']"))).click();
						driver.switchTo().defaultContent();
						operator(driver);
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
					if (driver != null) {
						driver.close();
						driver.quit();
					}
				}
				//终止线程
				if(testSelenium.taskThread[idx]!=null){
					try {	
						Thread.currentThread().interrupt();
					} 
					catch (Exception e) {										
						e.printStackTrace();
					}
					testSelenium.taskThread[idx]=null;
				}	
			}
		});
		testSelenium.taskThread[idx].start();
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
								data =data.format("%s,%s %n",testSelenium.userCount,data);
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
