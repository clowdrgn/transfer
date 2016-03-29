package com.oneapm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.dao.ICommentDao;
import com.oneapm.model.CommentModel;
import com.oneapm.model.JiraDataModel;
import com.oneapm.util.db.DBUtil;

public class CommentDaoImpl implements ICommentDao{

	@Override
	public List<CommentModel> selectCommentByKey(String issuekey) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("select * from comment t where issueKey='"+issuekey+"' ");				
		List<CommentModel> commentList = new ArrayList<CommentModel>();
		
		PreparedStatement ps = null;  
		ResultSet rs = null;
		try {
			ps=connection.prepareStatement(sbuffer.toString());
			rs = ps.executeQuery();
			while(rs.next()) {
				CommentModel comment = new CommentModel();
				comment.setIssueKey(rs.getString("issueKey"));
				comment.setBody(rs.getString("body"));
				comment.setDisplayName(rs.getString("displayName"));
				comment.setId(rs.getString("commentId"));
				commentList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.closeConnection(connection, ps, rs);
		}		
		return commentList;
	}

	@Override
	public int countComment(String issuekey) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("select count(*) from comment t where issueKey='"+issuekey+"' ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
		int rowCount = 0;
		try {
			ps = connection.prepareStatement(sbuffer.toString());  
			rs = ps.executeQuery();
			    
			while(rs.next())    
			{    
			    rowCount++;    
			}  
		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.closeConnection(connection, ps, rs);
		}		
		return rowCount;
	}

	@Override
	public void insertComment(CommentModel comment) {
		Connection connection = DBUtil.getConnection();			
		PreparedStatement ps = null;  
		ResultSet rs = null;
		
		try {
			if(comment.getBody().contains("\"")||comment.getBody().contains("\'")){
				comment.getBody().replaceAll("\'","");
				comment.getBody().replaceAll("\"", "");
			}
			String sql="insert into comment(commentId,body,displayName,issueKey,name)"
					+ "values('"+comment.getId()+"','"+comment.getBody()+"','"+comment.getDisplayName()+"','"+comment.getIssueKey()+"'"
							+ ",'"+comment.getName()+"')";
			System.out.println("SQL:"+sql);
			
			ps=connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			String sql="insert into comment(commentId,body,displayName,issueKey,name)"
					+ "values('"+comment.getId()+"','无法获取，请在jira系统查看','"+comment.getDisplayName()+"','"+comment.getIssueKey()+"'"
							+ ",'"+comment.getName()+"')";
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
	public boolean isexists(String commentId) {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("select * from comment t where commentId='"+commentId+"' ");				
		PreparedStatement ps = null;  
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sbuffer.toString());  
			rs = ps.executeQuery();
			    
			while(rs.next())    
			{    
			    return true;   
			}  
		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.closeConnection(connection, ps, rs);
		}		
		
		return false;
	}

}
