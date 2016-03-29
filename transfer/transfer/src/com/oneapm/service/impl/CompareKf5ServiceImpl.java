package com.oneapm.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Issue;
import com.oneapm.dao.ITransferDao;
import com.oneapm.dao.impl.TransferDaoImpl;
import com.oneapm.model.Kf5Model;
import com.oneapm.service.CheckJiraStatus;
import com.oneapm.service.ICompareKf5Service;
import com.oneapm.service.JiraIssueListService;

public class CompareKf5ServiceImpl implements ICompareKf5Service {

	@Override
	public List<Kf5Model> selectTable(String jiraNum) throws URISyntaxException {
		ITransferDao dao = new TransferDaoImpl();
		List<Kf5Model> list = new ArrayList<Kf5Model>();
		list = CheckJiraStatus.setJiraNumsAndStatus(list);

		List<Issue> issueList = JiraIssueListService.getJiraIssueList(list);
		for(int i = 0; i < issueList.size(); i++){
			Iterable<Comment> iter = issueList.get(i).getComments();
			Iterator it = iter.iterator();
			while(it.hasNext()){
				it.next().toString();
			}
		}
		return list;
	}

}
