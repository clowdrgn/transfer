package com.oneapm.jira.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;

import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Issue;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String issueKey = request.getParameter("key");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			Map map = new HashMap();
			Issue issue = JiraIssueClient.getIssue(issueKey,username,password);
			String Author = JiraIssueClient.getIssueAuthor(issueKey,username,password);
			map.put("author", Author);
			List<Comment> Comment = JiraIssueClient.getIssueComment(issueKey,username,password);
			JSONArray new_ja = new JSONArray();
			DateTime date = JiraIssueClient.getIssueDate(issueKey,username,password);
			map.put("dateTime", date);
			String descri = JiraIssueClient.getIssueDescribe(issueKey,username,password);
			map.put("describe", descri);
			String dev = JiraIssueClient.getIssueDevloper(issueKey,username,password);
			map.put("developer", dev);
			String status = JiraIssueClient.getIssueStatus(issueKey,username,password);
			map.put("status", status);
			String uri = JiraIssueClient.getIssueUri(issueKey,username,password);
			map.put("uri", uri);
			map.put("comment", Comment);
			new_ja.put(map);
			response.getWriter().println(new_ja);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
