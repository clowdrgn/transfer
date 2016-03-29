package com.oneapm.util.common;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.oneapm.work.Startup;

public class Listener implements ServletContextListener {

        private static final Log LOG = LogFactory.getLog(Listener.class);

        public void contextDestroyed(ServletContextEvent arg0) {
              
                LOG.info("error..........................................");
        }

        public void contextInitialized(ServletContextEvent arg0) {
                LOG.info("init timer..........................................");
               
                        init(arg0);
               
        }

        public void init(ServletContextEvent event)  {
        	
                try {
					Startup.class.newInstance().startup();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
        }

}
