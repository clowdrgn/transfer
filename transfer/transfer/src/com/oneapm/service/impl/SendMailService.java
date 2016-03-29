package com.oneapm.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.mysql.jdbc.log.Log;
import com.oneapm.kf5.client.Kf5ApiData;

public class SendMailService extends TimerTask{
	static JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
	 static NullProgressMonitor pm = new NullProgressMonitor();
	 static String uri="http://jira.oneapm.me/";
	 static String user="lijiang";
	 static String pwd="19881225";
	public static List<String> SendMailUnFormUser() throws IOException, JSONException  {
		  
		 List<String> strList = new ArrayList<String>();
		 URI jiraServerUri = null;
		 Issue issue;
		try {
			jiraServerUri = new URI(uri);
		} catch (URISyntaxException e) {
		}
		 IssueRestClient restClient = factory.createWithBasicHttpAuthentication(
				 jiraServerUri, user, pwd).getIssueClient();
		 for(int i = 1; i < 500; i++){
			 try{
				 issue = restClient.getIssue("CS-"+i+"", pm);
			 }catch(Exception e){
				 issue = null;
			 }
			 if(issue!=null){
				 DateTime createTime = issue.getCreationDate();
				 String str2 = createTime.toString("yyyy-MM-dd HH:mm");
				 if(issue.getStatus()!=null){
				 if(issue.getStatus().getName()!="Resolved"&&issue.getStatus().getName()!="Closed"){
				 Field userInfo = issue.getFieldByName("用户信息");
				 if(userInfo!=null){
					 if(userInfo.getValue()!=null){
						 String str = userInfo.getValue().toString();
						 String[] s = str.split("[^\\d]");
						 if(s!=null&&s.length!=0&&str.length()!=0){
							 boolean flag = false;
						 for(String str1:s){
							 if(str1!=null&&str1.length()!=0){
							 Long id = Long.parseLong(str1);
							 String result = Kf5ApiData.findByGroupId(id);
							 JSONObject a = new JSONObject(result);  
							 if(a.getInt("status")==0 ){
								
							 }else{
								 flag = true;
								 break;
							 }
							 }
						 }
						 	if(!flag){
						 		 String html = "";
								 if(issue.getReporter()!=null&&issue.getReporter().getDisplayName()!=null){
								       html = "创建时间:"+str2+"&nbsp链接:<a href='http://jira.oneapm.me/browse/"+issue.getKey()+"'>http://jira.oneapm.me/browse/"+issue.getKey()+"</a>\r\n报告人："+issue.getReporter().getDisplayName()+"\r\n";
								 }else{
									   html = "创建时间:"+str2+"&nbsp链接:<a href='http://jira.oneapm.me/browse/"+issue.getKey()+"'>http://jira.oneapm.me/browse/"+issue.getKey()+"</a>\r\n报告人：无\r\n";
								 }
								 if(issue.getAssignee()!=null&&issue.getAssignee().getDisplayName()!=null){
									 html += "开发者："+issue.getAssignee().getDisplayName()+"\r\n";
								 }else{
									 html += "开发者：无\r\n";
								 }
								 html += "用户信息："+str+"<br>\r\n";
								 strList.add(html);
						 	}
						 }else{
							 String html = "创建时间:"+str2+"&nbsp链接:<a href='http://jira.oneapm.me/browse/"+issue.getKey()+"'>http://jira.oneapm.me/browse/"+issue.getKey()+"</a>\r\n报告人："+issue.getReporter().getDisplayName()+"\r\n";
							 if(issue.getAssignee()!=null){
								 if(issue.getAssignee().getDisplayName()!=null){
									 html += "开发者："+issue.getAssignee().getDisplayName()+"\r\n";
								 }
							 }
							 html += "用户信息："+str+"<br>\r\n";
							 strList.add(html);
						 }
					 }
				 }
				 }
			 }
			 }
		 }
		 Collections.reverse(strList);
		return strList;
	 }
	@Override
	public void run() {
		try {
			List<String> hts = SendMailUnFormUser();
			 if(hts.size()!=0){
				 String ht = hts.toString();
				 SendCloudService.sendCloud("fanjinlong@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
				 SendCloudService.sendCloud("duhuaxing@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
				 SendCloudService.sendCloud("lijiang@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
				 SendCloudService.sendCloud("liuyuanli@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
				 SendCloudService.sendCloud("geliang@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
				 SendCloudService.sendCloud("ruirui@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
				 SendCloudService.sendCloud("jiayuqian@oneapm.com", ht, "Jira-用户信息不合格列表", 0L);
			 }
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	/*public static void main(String[] args) throws IOException, JSONException {
		 List<String> strList = new ArrayList<String>();
		 URI jiraServerUri = null;
		 Issue issue = null;
		try {
			jiraServerUri = new URI(uri);
		} catch (URISyntaxException e) {
		}
		 IssueRestClient restClient = factory.createWithBasicHttpAuthentication(
				 jiraServerUri, user, pwd).getIssueClient();
//		issue = restClient.getIssue("CS-303", pm);
		 for(int i = 300; i < 310; i++){
			 try{
				 issue = restClient.getIssue("CS-"+i+"", pm);
			 }catch(Exception e){
				 issue = null;
			 }
		if(issue!=null){
			 DateTime createTime = issue.getCreationDate();
			  String str6 = createTime.toString("yyyy-MM-dd HH:mm");
			 if(issue.getStatus()!=null){
			 if(issue.getStatus().getName()!="Resolved"&&issue.getStatus().getName()!="Closed"){
			 Field userInfo = issue.getFieldByName("用户信息");
			 if(userInfo!=null){
				 if(userInfo.getValue()!=null){
					 String str = userInfo.getValue().toString();
					 String[] s = str.split("[^\\d]");
					 if(s!=null&&s.length!=0&&str.length()!=0){
					 for(String str1:s){
						 if(str1!=null&&str1.length()!=0){
						 Long id = Long.parseLong(str1);
						 String result = Kf5ApiData.findByGroupId(739L);
						 JSONObject a = new JSONObject(result);  
						 if(a.getInt("status")==0){
							
							 String html = "";
							 if(issue.getReporter()!=null&&issue.getReporter().getDisplayName()!=null){
							       html = "链接:<a href='http://jira.oneapm.me/browse/"+issue.getKey()+"'>http://jira.oneapm.me/browse/"+issue.getKey()+"</a>\r\n报告人："+issue.getReporter().getDisplayName()+"\r\n";
							 }else{
								   html = "链接:<a href='http://jira.oneapm.me/browse/"+issue.getKey()+"'>http://jira.oneapm.me/browse/"+issue.getKey()+"</a>\r\n报告人：无\r\n";
							 }
							 if(issue.getAssignee()!=null&&issue.getAssignee().getDisplayName()!=null){
								 html += "开发者："+issue.getAssignee().getDisplayName()+"\r\n";
							 }else{
								 html += "开发者：无\r\n";
							 }
							 html += "用户信息："+str+"&nbsp创建时间:"+str6+"<br>\r\n";
							 strList.add(html);
						 }
						 }
					 }
					 }else{
						 String html = "链接:<a href='http://jira.oneapm.me/browse/"+issue.getKey()+"'>http://jira.oneapm.me/browse/"+issue.getKey()+"</a>\r\n报告人："+issue.getReporter().getDisplayName()+"\r\n";
						 html += "开发者："+issue.getAssignee().getDisplayName()+"\r\n";
						 html += "用户信息："+str+"&nbsp创建时间:"+str6+"<br>\r\n";
						 strList.add(html);
					 }
				 }
			 }
			 }
		 }
		 }
		 }	
		 Collections.reverse(strList);
			SendCloudService.sendCloud("fanjinlong@oneapm.com", strList.toString(), "Jira-用户信息不合格列表", 0L);
			// TODO Auto-generated catch block
	}*/
}
