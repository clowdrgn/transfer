package com.oneapm.work;

import java.util.Calendar;
import java.util.Timer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oneapm.dao.impl.JiraDataDaoImpl;
import com.oneapm.init.ConfigInfoDepository;
import com.oneapm.model.JiraDataModel;
import com.oneapm.service.impl.GetJiraDataService;
import com.oneapm.service.impl.SendMailService;
import com.oneapm.util.common.*;

import ch.qos.logback.core.net.SyslogOutputStream;

public class Startup {
	private static final Log logger = LogFactory.getLog(Startup.class);
	private static final Long SCAN_INTERVAL = ConfigInfoDepository.WorkTime.SCAN_INTERVAL;
	private static final Long DELAY_INTERVAL = ConfigInfoDepository.WorkTime.DELAY_INTERVAL;
	private static final String TIMER_NAME = "TransferScanTimer";
	private static Timer timer = new Timer(TIMER_NAME);
	private static GetJiraDataService compareService = new GetJiraDataService();
	private static SendMailService sendMailService = new SendMailService();
	public void startup() {
		while(true){
			try {
				String now = DateUtil.getTimeStr(Calendar.getInstance());			
					this.scanAndUpdate();			
			} catch (Exception e){
				logger.info("the outermost loop", e);
			}
		}
	}
	
	public void scanAndUpdate(){	
			logger.info("scanner start work, the interval is " + SCAN_INTERVAL + " seconds");
			timer.schedule(sendMailService, 1000 * 60* 60*DELAY_INTERVAL,  1000 * 60* 60*24);
			timer.schedule(compareService, 0,  1000 * SCAN_INTERVAL);
			
	}
	public static void main(String[] args) {
//		timer.schedule(compareService, 0,  100000 * SCAN_INTERVAL);
		timer.schedule(sendMailService, 1000 * 60* 60*0,  1000 * 60* 60*24);
	}
}
