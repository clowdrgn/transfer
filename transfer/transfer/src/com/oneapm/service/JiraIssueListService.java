package com.oneapm.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Issue;
import com.oneapm.init.ConfigInfoDepository;
import com.oneapm.jira.client.JiraIssueClient;
import com.oneapm.model.Kf5Model;

public class JiraIssueListService {
	static String userName = ConfigInfoDepository.WorkTime.USERNAME;
	static String password = ConfigInfoDepository.WorkTime.PASSWORD;
	public static List<Issue> getJiraIssueList(List<Kf5Model> list) throws URISyntaxException{
		List<Issue> issueList = new ArrayList<Issue>();
		for(int i = 0; i < list.size(); i++){
		Issue issue = JiraIssueClient.getIssue(list.get(i).getJiraNum(), userName, password);
		issueList.add(issue);
		}
		return issueList;
	
	}
	}
