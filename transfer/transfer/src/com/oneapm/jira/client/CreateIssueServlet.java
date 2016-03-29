package com.oneapm.jira.client;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateIssueServlet
 */
@WebServlet("/CreateIssueServlet")
public class CreateIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateIssueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectName = request.getParameter("projectname");
		String issueType = request.getParameter("issuetype");
		String description = request.getParameter("description");
		String summary =request.getParameter("summary");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String assigneeName = request.getParameter("assigneename");
		try {
			String issueKey = JiraIssueClient.createIssue(projectName,assigneeName, issueType, description, summary, userName, password);
			response.getWriter().println(issueKey);
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
