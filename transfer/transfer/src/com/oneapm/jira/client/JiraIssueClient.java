package com.oneapm.jira.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.BasicStatus;
import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.input.FieldInput;
import com.atlassian.jira.rest.client.domain.input.IssueInput;
import com.atlassian.jira.rest.client.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;



public class JiraIssueClient {
	 static JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
	 static NullProgressMonitor pm = new NullProgressMonitor();
	 static String uri="http://jira.oneapm.me/";
	 static String user="lijiang";
	 static String pwd="19881225";
	 /**
	 * @param args
	 */
	 public static void main(String[] args) throws URISyntaxException {

		// getIssueStatus("USER-150");
		// createIssue("USER", "2", "test", "manage dev test");
		System.out.println(getIssueStatus("USER-150",user,pwd));
		 }
	 /**
	 * @param issueKey
	 * @throws URISyntaxException
	 */
	public static Issue getIssue(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
	 URI jiraServerUri = new URI(uri);
	 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
	 jiraServerUri, userName, password);
	 //get issue through issueKey
	 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
	 //grab user info
	 if(issue!=null){
	 	return issue;
	 }
	 return null;
	 }
	public static List<Comment> getIssueComment(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 Iterable<Comment> iter = issue.getComments();
		 Iterator<Comment> it = iter.iterator();
		 List<Comment> commentList = new ArrayList<Comment>();
		 while(it.hasNext()){
			 // String str = it.next().getAuthor().toString();
			 Comment str1 = it.next();
			 commentList.add(str1);
			
			 }
		return commentList;
		
		 }
	
	public static String getIssueAuthor(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 Iterable<Comment> iter = issue.getComments();
		 Iterator<Comment> it = iter.iterator();
		 while(it.hasNext()){
			  String str = it.next().getAuthor().getDisplayName();
			 
			
			 }
		 if(issue.getReporter()!=null&&issue.getReporter().getDisplayName()!=null){
		return issue.getReporter().getDisplayName();
		 }
		 return null;
		
		 }
	public static boolean judgeIssueKey(String issueKey){
		if (issueKey == null) {
			return false;
		}
		boolean isDigit = false;
		boolean isLetter = false;
		for (int i = 0; i < issueKey.length(); i++) {
			if (Character.isDigit(issueKey.charAt(i))) {
				isDigit = true;
			}
			if (Character.isLetter(issueKey.charAt(i))) {
				isLetter = true;
			}
		}
		return isDigit&&isLetter;
	}
	public static String getIssueStatus(String issueKey, String userName, String password) throws URISyntaxException{
		 Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 if(issue!=null){
			 BasicStatus status = issue.getStatus();
			 // System.out.println(status.toString());
			 String s = status.getName();
			 // System.out.println(s);
			 return s;
		 }
		return null;
		 }
	
	public static String getIssueDevloper(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 BasicUser de = issue.getAssignee();
		 if(de!=null){
		 return de.getDisplayName();
		 }
		return null;
		
		
		
		 }
	public static String getIssueDescribe(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 if(issue!=null){
		 String de = issue.getDescription();
		return de;
		 }
		 return null;
		 }
	
	public static DateTime getIssueDate(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 if(issue!=null){
		 DateTime de = issue.getCreationDate();
		 return de;
		 }
		return null;
		 }
	
	public static String getIssueUri(String issueKey, String userName, String password) throws URISyntaxException{
		Boolean flag = judgeIssueKey(issueKey);
		 if(!flag){
			 return null;
		 }
		 URI jiraServerUri = new URI(uri);
		 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
		 jiraServerUri, userName, password);
		 //get issue through issueKey
		 Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
		 //grab user info
		 String key = issue.getKey();
		 String uri = "http://jira.oneapm.me/browse/"+key;
		System.out.println(uri);
		return uri;
		 }

	 //
	 /**
	 * create the issue
	 * @param Issue type :  1L:bug; 2L:new requirement; 3L:task; 4L.improvement
	 * @param projectName:such as "GTP",the project short name
	 * @param Longid:such as 10000
	 * @param Summary:topic
	 * @param Description:
	 */
	public static String createIssue(String projectName,String assigneeName, String issueType,String description,String summary, String userName, String password) throws URISyntaxException{
	 //JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
	 URI jiraServerUri = new URI(uri);
	 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
	 jiraServerUri, userName, password);
	 IssueInputBuilder issueBuilder = new IssueInputBuilder("ProjectKey",
	 Long.valueOf(issueType));
	 
	 
	 final URI projectUri = new URI(
	 "http://jira.oneapm.me/rest/api/2/project/"+projectName);
	 BasicProject bporject = new BasicProject(projectUri, projectName, projectName);
	 
	 issueBuilder.setProject(bporject);
	 issueBuilder.setDescription(description);
	 issueBuilder.setSummary(summary);
	 issueBuilder.setAssigneeName(assigneeName);
	 
	 IssueInput issueInput = issueBuilder.build();
	 BasicIssue bIssue = restClient.getIssueClient().createIssue(issueInput,
	 pm);
	 // print the newly created issuekey
	 System.out.println(bIssue.getKey());
	return bIssue.getKey();
	 } 
	public static void updateIssueField(String key,String field,String value, String userName, String password) throws URISyntaxException{
	 
	 
	 URI jiraServerUri = new URI(uri);
	 JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
	 jiraServerUri, userName, password);
	 //get the issue through issuekey
	 Issue issue = restClient.getIssueClient().getIssue(key, pm);
	 //update the default field environment,if the field is defined by self,pls use the filed<span></span> 
	 FieldInput fieldinput = new FieldInput("environment", value);
	 List<FieldInput> fields = new ArrayList<FieldInput>();
	 fields.add(fieldinput);
	 //restClient.getIssueClient().update(issue, fields, pm);
	 }
	 
	 
	public static void changeIssueStatus(String issuekey, String userName, String password) throws URISyntaxException
	 {
	 
	 
	 URI jiraServerUri = new URI(uri);
	 JiraRestClient restClient = factory
	 .createWithBasicHttpAuthentication(jiraServerUri, userName,
			 password);               
	 Issue issue = restClient.getIssueClient().getIssue(issuekey, pm);
	//the number 3 is involed below picture,you can focus on the red rectangular
	 TransitionInput tinput= new TransitionInput(3);
	 restClient.getIssueClient().transition(issue,
	 tinput, pm);
	 }  }
