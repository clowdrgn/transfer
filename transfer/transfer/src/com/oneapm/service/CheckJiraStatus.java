package com.oneapm.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.rest.client.domain.Comment;
import com.oneapm.init.ConfigInfoDepository;
import com.oneapm.jira.client.JiraIssueClient;
import com.oneapm.model.CommentModel;
import com.oneapm.model.Kf5Model;


public class CheckJiraStatus {
	static String userName = ConfigInfoDepository.WorkTime.USERNAME;
	static String password = ConfigInfoDepository.WorkTime.PASSWORD;
	public static List<String> getJiraNums() throws URISyntaxException {
		Map map = Kf5DataClean.checkGongdan();	
		List<String> list= null;
		for(int i=1;i<=map.size();i++){
		String key = (String)map.get(i);
		list.add(key);
		}	
		return list;
		
	}
	
	public static List<Kf5Model> setJiraNumsAndStatus(List<Kf5Model> list) throws URISyntaxException {
		Map map = Kf5DataClean.checkGongdan();	
		
		for(int i=0;i<map.size()-1;i++){
		Kf5Model kf5 = new Kf5Model();
		String keyStr = (String)map.get(i);
		if(keyStr!=null&&keyStr.length()!=0){
			if(keyStr.contains("?")){
				int q =  keyStr.indexOf("?"); 
				keyStr = keyStr.substring(0, q);
			}
			kf5.setJiraNum(keyStr);
			String status = JiraIssueClient.getIssueStatus(keyStr, userName, password);
			
			kf5.setStatus(status);
		}
		if(kf5.getStatus()!=null){
			list.add(kf5);
		}
		if(list.size()%20==0){
			System.out.println(list.size());
		}
		}	
		return list;
		
	}
	
	public static List<Kf5Model> setJiraStatus(List<Kf5Model> list) throws URISyntaxException {
		Map map = Kf5DataClean.checkGongdan();	
		Kf5Model kf5 = new Kf5Model();
		for(int i=1;i<=map.size();i++){
		String key = (String)map.get(i);
		String keyStr = key.substring(29,key.length()).replace("/", "");
		if(keyStr.contains("?")){
			int q =  keyStr.indexOf("?"); 
			keyStr = keyStr.substring(0, q);
		}
		String status = JiraIssueClient.getIssueStatus(keyStr, userName, password);
		
		kf5.setStatus(status);
		list.add(kf5);
		}	
		return list;
		
	}
	
	public static List<CommentModel> setComment(List<CommentModel> list) throws URISyntaxException {
		Map map = Kf5DataClean.checkGongdan();	
		list = new ArrayList<CommentModel>();
		for(int i=1;i<=map.size();i++){
		
		String keyStr = (String)map.get(i);
		
		if(keyStr.contains("?")){
			int q =  keyStr.indexOf("?"); 
			keyStr = keyStr.substring(0, q);
		}
		
		List<Comment> commentList = JiraIssueClient.getIssueComment(keyStr, userName, password);
		for(int l = 0; l < commentList.size(); i++){
			CommentModel comment = new CommentModel();
			comment.setId(commentList.get(l).getId().toString());
			comment.setBody(commentList.get(l).getBody());
			comment.setIssueKey(keyStr);
			comment.setDisplayName(commentList.get(l).getAuthor().getDisplayName());
			list.add(comment);
		}
		
		
		
		}	
		return list;
		
	}
	public static void main(String[] args) {
}
	}
