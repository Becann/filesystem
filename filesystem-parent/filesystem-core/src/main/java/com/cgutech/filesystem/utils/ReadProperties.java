package com.cgutech.filesystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *ReadProperties.java
 *@author Becan E-mail:becan@cgutech.com
 *@date 2016年9月5日 下午3:11:39
 */
public class ReadProperties {
	private static final Log LOG = LogFactory.getLog(ReadProperties.class);
	public static String FILE_COMMON="/config.properties";
	public static Properties loadSetting(String uri){
		Properties properties = new Properties();
		InputStream inputStream = ReadProperties.class.getResourceAsStream(uri);
		if(inputStream!=null){
			try {
				properties.load(inputStream);
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				LOG.error(e);
			}finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LOG.error(e);
				}
			}
		}else {
			return null;
		}
		return properties;
	}
	
	public static String read(String key){
		String value=null;
		Properties properties = loadSetting(FILE_COMMON);
		if(properties!=null){
			value=properties.getProperty(key);
		}
		return value;
	}
	
	public static void main(String[] args) {
		
	}
}

