package com.oneapm.util.common;

import com.oneapm.init.DataSourceManager;

public class Constants {
	public static class DataSource{
		public static final String DRIVER = DataSourceManager.getValue("source.driverClassName");
		public static final String URL = DataSourceManager.getValue("source.url");
		public static final String USERNAME = DataSourceManager.getValue("source.username");
		public static final String PASSWORD = DataSourceManager.getValue("source.password");
	}
	
	
}
