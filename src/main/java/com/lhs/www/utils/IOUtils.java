package com.lhs.www.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class IOUtils {
	
	private static String PMS_ORG_RL ="C:/Users/lhs/Desktop/PMS_ORG_RL_";

	@Test
	public void readTxt() throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
		PMS_ORG_RL = PMS_ORG_RL+simpleDateFormat.format(new Date())+".txt";
		FileInputStream inputStream = new FileInputStream(PMS_ORG_RL);
		System.out.println(PMS_ORG_RL);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String str = null;
		List<Dept> depts= new ArrayList<>();
		while((str = bufferedReader.readLine()) != null)
		{
			Dept dept = new Dept();
			String[] deptStr = str.split("\\|\\+\\|");
			dept.setId1(Long.valueOf(deptStr[0]));
			dept.setName1(deptStr[1]);
			dept.setId2(Long.valueOf(deptStr[2]));
			dept.setName2(deptStr[3]);
			depts.add(dept);
		}
		for(Dept dept:depts){
			System.out.println(dept.getId1());
			System.out.println(dept.getId2());
			System.out.println(dept.getName1());
			System.out.println(dept.getName2());
		}
			
		//close
		inputStream.close();
		bufferedReader.close();
	}
}