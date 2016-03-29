package com.oneapm.init;

import com.oneapm.init.ThreadConfigManager;

public class ConfigInfoDepository {
	public static class WorkTime {
		public static final String USERNAME = ListenerConfigManager.getValue("username");
		public static final String PASSWORD = ListenerConfigManager.getValue("password");
		public static final Long SCAN_INTERVAL = Long.parseLong(ListenerConfigManager.getValue("listenInterval"));
		public static final Long DELAY_INTERVAL = Long.parseLong(ListenerConfigManager.getValue("delayInterval"));
	}
	

	public static class ThreadCtr{
		public static final int THREAD_COUNT = Integer.parseInt(ThreadConfigManager.getValue("threadCount"));
	}
}
