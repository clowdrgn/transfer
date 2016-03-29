package com.oneapm.test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.dao.IJiraDataDao;
import com.oneapm.dao.impl.JiraDataDaoImpl;
import com.oneapm.model.JiraDataModel;
import com.oneapm.model.Kf5Model;
import com.oneapm.service.IGetJiraDataService;
import com.oneapm.service.IInsertTransferService;
import com.oneapm.service.impl.GetJiraDataServiceImpl;
import com.oneapm.service.impl.InsertTransferServiceImpl;

public class insertTest {

	public static void main(String[] args) {
/*		IInsertTransferService s = new InsertTransferServiceImpl();
		List<Kf5Model> list = new ArrayList<Kf5Model>();
		try {
			s.insertKf5(list);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*IJiraDataDao dao = new JiraDataDaoImpl();
		//dao.selectJiraDataList();
		JiraDataModel jira = new JiraDataModel();
		jira.setIssueKey("PHP-144");
		jira.setComment("11111111111111");
		dao.updateJiraData(jira);*/
		IGetJiraDataService s = new GetJiraDataServiceImpl();
		s.getJiraDataList();
	}

}
