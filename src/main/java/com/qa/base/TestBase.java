package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public static Properties props;
	
	public static int RESPONSE_STATUS_CODE_200=200;
	public static int RESPONSE_STATUS_CODE_500=500;
	public static int RESPONSE_STATUS_CODE_400=400;
	public static int RESPONSE_STATUS_CODE_401=401;
	public static int RESPONSE_STATUS_CODE_201=201;
	
	public TestBase() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties"));
			props=new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
