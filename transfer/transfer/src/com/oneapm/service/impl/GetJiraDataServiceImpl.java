package com.oneapm.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.atlassian.jira.rest.client.domain.Comment;
import com.oneapm.init.ConfigInfoDepository;
import com.oneapm.jira.client.JiraIssueClient;
import com.oneapm.model.CommentModel;
import com.oneapm.model.JiraDataModel;
import com.oneapm.model.Kf5Model;
import com.oneapm.service.CheckJiraStatus;
import com.oneapm.service.IGetJiraDataService;

public class GetJiraDataServiceImpl implements IGetJiraDataService {
	static String userName = ConfigInfoDepository.WorkTime.USERNAME;
	static String password = ConfigInfoDepository.WorkTime.PASSWORD;
	public List<JiraDataModel> getJiraDataList(){
		List<Kf5Model> list = new ArrayList<Kf5Model>();
		List<JiraDataModel> jiraList = new ArrayList<JiraDataModel>();
		try {
			list = CheckJiraStatus.setJiraNumsAndStatus(list);
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getJiraNum()==null || list.get(i).getJiraNum().length()==0){
					break;
				}
			JiraDataModel jira = new JiraDataModel();
			List<CommentModel> commentList = new ArrayList<CommentModel>();
			StringBuffer sbuffer = new StringBuffer();
			String repoter = JiraIssueClient.getIssueAuthor(list.get(i).getJiraNum(), userName, password);
			String develop = JiraIssueClient.getIssueDevloper(list.get(i).getJiraNum(), userName, password);
			String status = JiraIssueClient.getIssueStatus(list.get(i).getJiraNum(), userName, password);
			DateTime createTime = JiraIssueClient.getIssueDate(list.get(i).getJiraNum(), userName, password);
			String timeStr = createTime.toString();
			List<Comment> comment = JiraIssueClient.getIssueComment(list.get(i).getJiraNum(), userName, password);
			for(int l = 0; l < comment.size(); l++){
				CommentModel commentModel = new CommentModel();
				sbuffer.append(comment.get(l).getBody().toString());
				commentModel.setBody(comment.get(l).getBody());
				commentModel.setDisplayName(comment.get(l).getAuthor().getDisplayName());
				commentModel.setId(comment.get(l).getId().toString());
				commentModel.setName(comment.get(l).getAuthor().getName());
				commentModel.setIssueKey(list.get(i).getJiraNum());
				commentList.add(commentModel);
			}
			jira.setCommentModel(commentList);
			jira.setIssueKey(list.get(i).getJiraNum());
			jira.setComment(sbuffer.toString());
			jira.setCreateTime(timeStr);
			jira.setRepoter(repoter);
			jira.setStatus(status);
			jira.setCreator(develop);
			jiraList.add(jira);
		}
		
		
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jiraList;
		
		
	}
}
