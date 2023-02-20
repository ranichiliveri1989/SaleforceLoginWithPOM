package com.jan23.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

	public static String readPropertyData(String key) throws IOException {
		FileInputStream fis=new FileInputStream(Constants.TEST_DATA_PROPERTIES);
		Properties ob=new Properties();
		ob.load(fis);
		String value=ob.getProperty(key);
		return value;
	}
}
