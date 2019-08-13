package com.thread;

import java.util.Optional;

public class testJava8 {

	public static void main(String[] args) {
		/*Optional<Emp> optional = Optional.ofNullable(new Emp());
		boolean isnull = optional.isPresent();
		System.out.println(isnull);
		if (isnull) {
			Emp emp =optional.get();
			System.out.println(emp.getId()+emp.getName()+emp.getAge());
		}else
			System.out.println("emp is null");*/
		/*String a ="444879,3337873";
		String b =a.substring(a.indexOf(",")+1);
		int lenght = b.length();
		System.out.println("b:"+b+"b.length:"+lenght);
		String[] idArr = a.split(",");
		System.out.println(idArr.length);*/
		int j= 3;
		int x =1;
		for (int i = 0; i < 5; i++) {
			System.out.println("i:"+i);
			if (j>x) {
				if(j>i) {
					System.out.println("жуж╧");
					break;
				}
			}
		}
	}
}
