package com.cjn.testSelenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;




public class testSelenium{

	public static WebDriver driver;
	public static WebElement uesernameElement = null;
	public static WebElement passwordElement = null;
	public static JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
	public static Actions action =null;
	public static String userCount = "13211373244";
	public static String password = "a1234567";
	public static Thread[] taskThread = null;
	public static int times=2;

	public static void main(String[] args) {
		//https://www.bbsmax.com/A/gVdnqVp5Wl/
		try {
			taskThread = new Thread[1];
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			for (int i = 0; i < taskThread.length; i++) {
				getWebIdsingle.startTask(i); 
			}
			//Thread.sleep(5000);
			/*do {
				try {
					for (int i = 0; i < taskThread.length; i++) {
						getWebIdsingle.startTask(i); 
						if (taskThread[i]==null) {
							registerPPTV.startTask(i);
						}
					}
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}while(true);*/
				
			
			
			
			
			/*System.setProperty("webdriver.chrome.driver", "C:/myData/tools/Selenium/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://www.miguvideo.com/mobiletv.jsp");
			driver.manage().window().maximize();
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			//driver.get("https://192.168.3.18/vsphere-client/?csp");
			WebElement loginButton = driver.findElement(By.className("loginButton"));
			loginButton.click();
			try {
				Thread.sleep(1000);  
				System.out.println("等待时间过了");
				driver.switchTo().frame("loginIframe");
				uesernameElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J_AccountPsd']")));
				uesernameElement.sendKeys(userCount);
				passwordElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J_PasswordPsd']")));
				passwordElement.sendKeys(password);
				new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='登录']"))).click();
				driver.switchTo().defaultContent();
				operator();
			} catch (TimeoutException | NoSuchElementException e) {
				System.out.println(e.toString());
			}
			*/
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
		}
	}
	
	public static void webvpn() throws InterruptedException {
		String userCount = "administrator@im.com";
		String password = "Im@1234567";
		//loginElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='J_AccountPsd']")));
		WebElement clickAlert = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='flashContent']/p[2]/a/img")));
		clickAlert.click();
		String currentWindow = driver.getWindowHandle();
	    Set<String> handles = driver.getWindowHandles();  
        Iterator<String> it = handles.iterator();  
        while(it.hasNext()){  
            String handle = it.next();  
            if(currentWindow.equals(handle)) continue;  
            WebDriver window = driver.switchTo().window(handle);  
            System.out.println("title,url = "+window.getTitle()+","+window.getCurrentUrl());  
        }  
		//afterClickOn(30, driver);
		driver.switchTo().frame("websso");
		uesernameElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='username']")));
		uesernameElement.sendKeys(userCount);
		passwordElement = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password']")));
		passwordElement.sendKeys(password);
		new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='submit']"))).click();
		operator();
	}
	
	private static void waitForAlertAndCloseAlert(WebDriver driver) throws InterruptedException
	  {
	     int i=0;
	     Alert alert =null;
	     while(i++<10)
	     {
	    	 
	          try
	          {
	        	  alert = driver.switchTo().alert();
	              System.out.println(alert.getText()+",i="+i);
	              alert.accept();
	              break;
	          }
	          catch(NoAlertPresentException e)
	          {
	        	Thread.sleep(1000);
	        	alert = driver.switchTo().alert();
	        	System.out.println(alert.getText()+",i="+i);
	            alert.accept();
	        	System.out.println("into NoAlertPresentException,i="+i+"\t"+alert.getText());
	            continue;
	          }
	     }
	     if(i==9){
	    	 throw new NoAlertPresentException();
	     }
	  }
	
	/*private static void acceptSecurityAlert(int secondes) { 
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(secondes, TimeUnit.SECONDS)   
	                  .pollingEvery(3, TimeUnit.SECONDS)   
	                  .ignoring(NoSuchElementException.class);  
	    Alert alert = null;
	    try {
	    	alert = wait.until(new Function<WebDriver, Alert>() {  
	    		
	    		public Alert apply(WebDriver driver) { 
	    			try { 
	    				return driver.switchTo().alert(); 
	    			} catch(NoAlertPresentException | TimeoutException e) { 
	    				return null; 
	    			} 
	    		} 
	    	}); 
	    	alert.accept(); 
		} catch (NullPointerException e) {
			System.out.println();
		}
	} */
	
	public static WebElement fluentWait(final By locator) {
	    @SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(5, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};
	
	public static void operator() throws InterruptedException {
		WebElement userNicknameElement;
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
			for (int i = 1; i <=maxIndex; i++) {
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
							data =data.format("%s,%s %n",userCount,data);
							System.out.print(data);
							try {
								File targetFile = new File("C:\\Users\\13995\\eclipse-workspace\\testSelenium\\src\\main\\resources\\tmp\\count.txt");
								BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile,true));
								bufferedWriter.write(data);
								bufferedWriter.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							//System.out.printf("%s,%s %n",userCount,data);
						}
						
					}
				}
				action = new Actions(driver);
				action.moveToElement(new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='next']")))).click().build().perform();
				Thread.sleep(2000);
				//driver.navigate().refresh();
				//Thread.sleep(1000);
				if(i==maxIndex) {
					System.out.println("~success~");
					//driver.quit();
					break;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void afterClickOn(int times, WebDriver arg1) {
        // TODO Auto-generated method stub
        boolean flag = false;
        Alert alert = null;
        try {

            new WebDriverWait(arg1, times).until(ExpectedConditions
                    .alertIsPresent());
            alert = arg1.switchTo().alert();
            flag = true;
            // alert.accept();
        } catch (NoAlertPresentException NofindAlert) {
            // TODO: handle exception

            NofindAlert.printStackTrace();
            // throw NofindAlert;
        }

        if (flag) {
            alert.accept();
        }

    }

}
