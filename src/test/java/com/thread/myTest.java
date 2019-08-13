package com.thread;

import java.util.Optional;

import com.cjn.testSelenium.readExcelUtil;

public class myTest {

	public static Thread[] taskThread = null;
	public static void main(String[] args) {
		//splitString();
		//readExcelUtil.readExcel(1);
		/*taskThread = new Thread[2];
		try {
			do {
				for (int i = 0; i < taskThread.length; i++) {
					if (taskThread[i]==null) {
						readExcelUtil.readSingleLine(i);
					}
				}
				Thread.sleep(5000);
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public static void  splitString(){
		int num =2;
		for (int i = 0; i < 5; i++) {
			System.out.println("i="+i);
			if(i==5-1) {
				System.out.println("i=5ак"+i);
			}
		}
		
	}	
}
