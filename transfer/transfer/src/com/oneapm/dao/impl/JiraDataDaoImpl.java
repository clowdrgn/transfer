package com.oneapm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.dao.IJiraDataDao;
import com.oneapm.model.JiraDataModel;
import com.oneapm.model.Kf5Model;
import com.oneapm.service.impl.GetJiraDataService;
import com.oneapm.util.db.DBUtil;

public class JiraDataDaoImpl implements IJiraDataDao{

	@Override
	public List<JiraDataModel> selectJiraDataList() {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("select * from jiradata t  ");				
		List<JiraDataModel> jiraList = new ArrayList<JiraDataModel>();
		
		PreparedStatement ps = null;  
		ResultSet rs = null;
		try {
			ps=connection.prepareStatement(sbuffer.toString());
			rs = ps.executeQuery();
			while(rs.next()) {
				JiraDataModel jira = new JiraDataModel();
				jira.setIssueKey(rs.getString("issueKey"));
				jira.setComment(rs.getString("comment"));
				jira.setCreateTime(rs.getString("createTime"));
				jira.setStatus(rs.getString("status"));
				jira.setRepoter(rs.getString("reporter"));
				jira.setCreator(rs.getString("creator"));
				jiraList.add(jira);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.closeConnection(connection, ps, rs);
		}		
		return jiraList;
	}

	@Override
	public void insertJiraData(JiraDataModel jira) {
		Connection connection = DBUtil.getConnection();			
		PreparedStatement ps = null;  
		ResultSet rs = null;
		
		try {
			
			String sql="insert into jiradata(issueKey,creator,reporter,comment,status,createTime)"
					+ "values('"+jira.getIssueKey()+"','"+jira.getCreator()+"','"+jira.getRepoter()+"','"+jira.getComment()
					+"','"+jira.getStatus()+"','"+jira.getCreateTime()+"')";
			System.out.println("SQL:"+sql);
			ps=connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(connection, ps, rs);
		}		
	}

	@Override
	public JiraDataModel selectJiraData(String issuekey) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("select * from jiradata t where issueKey='"+issuekey+"' ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
		JiraDataModel jira = new JiraDataModel();
		try {
			ps = connection.prepareStatement(sbuffer.toString());  
			rs = ps.executeQuery();
			while(rs.next()){
			jira.setIssueKey(rs.getString("issueKey"));
			jira.setComment(rs.getString("comment"));
			jira.setCreateTime(rs.getString("createTime"));
			jira.setStatus(rs.getString("status"));
			jira.setRepoter(rs.getString("reporter"));
			jira.setCreator(rs.getString("creator"));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.closeConnection(connection, ps, rs);
		}		
		return jira;
	}

	@Override
	public void updateJiraData(JiraDataModel jira) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		String comment = GetJiraDataService.escapeExprSpecialWord(jira.getComment());
		jira.setComment(comment);
		sbuffer.append("update  jiradata set comment='"+jira.getComment()+"' where issueKey='"+jira.getIssueKey()+"' ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
		try {
			
			ps = connection.prepareStatement(sbuffer.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			String sql="update  jiradata set comment='jira存在不能插入的注释，请在jira系统查看' where issueKey='"+jira.getIssueKey()+"' ";
			System.out.println("SQL:"+sql);
			
			try {
				
				ps=connection.prepareStatement(sql);
				ps.executeUpdate();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
		
		DBUtil.closeConnection(connection, ps, rs);
		}	
	}

	
	@Override
	public void updateJiraStatusByIssueKey(JiraDataModel jira) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("update  jiradata set status='"+jira.getStatus()+"' where issueKey='"+jira.getIssueKey()+"' ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
			try {
				
				ps=connection.prepareStatement(sbuffer.toString());
				ps.executeUpdate();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		finally{
		
		DBUtil.closeConnection(connection, ps, rs);
		}
		}	
	
	@Override
	public void updateJiraDevByIssueKey(JiraDataModel jira) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("update  jiradata set creator='"+jira.getCreator()+"' where issueKey='"+jira.getIssueKey()+"' ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
			try {
				
				ps=connection.prepareStatement(sbuffer.toString());
				ps.executeUpdate();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		finally{
		
		DBUtil.closeConnection(connection, ps, rs);
		}
		}	
	@Override
	public void deleteRepeatData() {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("delete from jiradata  where jiradata.id in (select * from (select max(jiradata.id) from jiradata group by issueKey having count(issueKey) > 1) as b); ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sbuffer.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
		DBUtil.closeConnection(connection, ps, rs);
		}	
	}
}
