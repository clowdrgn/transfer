package com.oneapm.work;

import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ScannerContextListener implements ServletContextListener{
	public static final Log logger = LogFactory.getLog(ScannerContextListener.class);
	public void contextInitialized(ServletContextEvent context) {
		try {
			Class.forName("com.oneapm.init.DataSourceManager", true, this.getClass().getClassLoader());
			Class.forName("com.oneapm.init.ListenerConfigManager", true, this.getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			logger.info("when to load Config Classes", e);
		}
		
		ThreadGroup threadGroup = new ThreadGroup("scanner");
		Thread startup = new Thread(threadGroup, "startuper"){
			@Override
			public void run() {
				Startup startup = new Startup();
				startup.scanAndUpdate();
 			}
		};
/*		Thread mail = new Thread(threadGroup, "mail"){
			@Override
			public void run() {
				Startup startup = new Startup();
				startup.mail();
 			}
		};
*/		startup.start();
//		mail.start();
		context.getServletContext().setAttribute("threadGroup", threadGroup);
	}
	
	public void contextDestroyed(ServletContextEvent context) {
		ServletContext servletContext = context.getServletContext();
		Timer timer = (Timer)servletContext.getAttribute("timer");
		timer.cancel();
		ThreadGroup threadGroup = (ThreadGroup)servletContext.getAttribute("threadGroup");
		threadGroup.stop();
	}
}
