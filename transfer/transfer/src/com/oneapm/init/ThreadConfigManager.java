package com.oneapm.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadConfigManager {
	public static final Log logger = LogFactory.getLog(ThreadConfigManager.class);
	private static final String CONFIG_PATH = "/config/threadctr-config.properties";
	private static final Properties properties = new Properties();
	
	static {
		InputStream configFileIStream = ListenerConfigManager.class
				.getResourceAsStream(CONFIG_PATH);
		try {
			properties.load(configFileIStream);
		} catch (IOException e) {
			logger.info("when load " + CONFIG_PATH, e);
			throw new RuntimeException("please check the file " + CONFIG_PATH 
					+ ", it can't be loaded into Properties Object");
		}finally{
			try {
				if(configFileIStream != null){
					configFileIStream.close();
				}
			} catch (IOException e) {
				logger.info("when close " + CONFIG_PATH + " file stream", e);
			}
		}
		
		logger.info(" loaded " + CONFIG_PATH + " OK");
	}
	
	public static String getValue(String key){
		String value = null;
		try{
			value = ((String)properties.getProperty(key)).trim();
		}catch(Exception e){
			logger.info("when try to get the properties in " + CONFIG_PATH, e);
		}
		return value;
	}
}
