package com.oneapm.test;

import java.util.List;

import com.oneapm.dao.IJiraDataDao;
import com.oneapm.dao.impl.JiraDataDaoImpl;
import com.oneapm.model.JiraDataModel;
import com.oneapm.service.IGetJiraDataService;
import com.oneapm.service.impl.GetJiraDataService;
import com.oneapm.service.impl.GetJiraDataServiceImpl;

public class GetJiraDataServiceTest {
	public static void main(String[] args) {
		try {
			GetJiraDataService.class.newInstance().run();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	IJiraDataDao dao = new JiraDataDaoImpl();
		List<JiraDataModel> list = dao.selectJiraDataList();
		System.out.println(list.size());*/
	}
}
