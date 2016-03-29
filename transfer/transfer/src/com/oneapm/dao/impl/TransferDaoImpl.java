package com.oneapm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.util.db.DBUtil;
import com.oneapm.dao.ITransferDao;
import com.oneapm.model.Kf5Model;


public class TransferDaoImpl implements ITransferDao{

	@Override
	public List<Kf5Model> selectTransferList() {
		Connection connection = DBUtil.getConnection();
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("select * from transfer t  ");				
		List<Kf5Model> Kf5ModelList = new ArrayList<Kf5Model>();
		
		PreparedStatement ps = null;  
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sbuffer.toString());  
			rs = ps.executeQuery();			
			while(rs.next()) {
				Kf5Model kf5 = new Kf5Model();
				kf5.setJiraNum(rs.getString("jiraNum"));
				kf5.setReply(rs.getString("reply"));
				kf5.setReplyTime(rs.getString("replyTime"));
				kf5.setStatus(rs.getString("status"));
				Kf5ModelList.add(kf5);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.closeConnection(connection, ps, rs);
		}		
		return Kf5ModelList;
	}


	@Override
	public void insertTransferFromKf5(Kf5Model kf5) {
		Connection connection = DBUtil.getConnection();			
		PreparedStatement ps = null;  
		ResultSet rs = null;
		
		try {
			
			String sql="insert into transfer(jiraNum,reply,replyTime,status)"
					+ "values('"+kf5.getJiraNum()+"','"+kf5.getReply()+"','"+kf5.getReplyTime()+"','"+kf5.getStatus()+"')";
			System.out.println("SQL:"+sql);
			ps=connection.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(connection, ps, rs);
		}
}
	}
