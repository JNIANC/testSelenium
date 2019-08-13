package com.thread;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class testThread extends JFrame{

	private Thread threadFirst = null;
	private Thread threadSecond = null;
	final JProgressBar jProgressBarFirst = new JProgressBar();
	final JProgressBar jProgressBarSecond = new JProgressBar();
	
	public static void init(JFrame frame,int width,int height) {
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
	public testThread() {
		getContentPane().add(jProgressBarFirst, BorderLayout.NORTH);
		getContentPane().add(jProgressBarSecond, BorderLayout.SOUTH);
		jProgressBarFirst.setStringPainted(true);
		jProgressBarSecond.setStringPainted(true);
		threadFirst = new Thread(new Runnable() {
			int count = 0;
			
			public void run() {
				while(true) {
					jProgressBarFirst.setValue(++count);
					try {
						Thread.sleep(100);
						threadSecond.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		threadFirst.start();
		threadSecond = new Thread(new Runnable() {
			int count = 0;
			public void run() {
				while(true) {
					jProgressBarSecond.setValue(++count);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (count == 100) {
						break;
					}
				}
			}
		});
		threadSecond.start();
	}
	
	public static void main(String[] args) {
		init(new testThread(), 100, 100);
	}

}
