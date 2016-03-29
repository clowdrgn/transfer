package com.oneapm.dao;

import java.util.List;

import com.oneapm.model.JiraDataModel;

public interface IJiraDataDao {
	/**
	 * @return
	 */
	public List<JiraDataModel> selectJiraDataList();

	/**
	 * @param kf5Model
	 */
	public void insertJiraData(JiraDataModel jira);
	
	
	public JiraDataModel selectJiraData(String issuekey);
	
	public void updateJiraData(JiraDataModel jira);
	
	public void deleteRepeatData();
	
	public void updateJiraStatusByIssueKey(JiraDataModel jira);

	public void updateJiraDevByIssueKey(JiraDataModel jira);
}
