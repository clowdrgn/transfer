package com.oneapm.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.oneapm.dao.ICommentDao;
import com.oneapm.dao.IJiraDataDao;
import com.oneapm.dao.impl.CommentDaoImpl;
import com.oneapm.dao.impl.JiraDataDaoImpl;
import com.oneapm.init.ConfigInfoDepository;
import com.oneapm.jira.client.JiraIssueClient;
import com.oneapm.kf5.client.Kf5ApiData;
import com.oneapm.model.CommentModel;
import com.oneapm.model.JiraDataModel;
import com.oneapm.service.IGetJiraDataService;
import com.oneapm.service.Kf5DataClean;
public class GetJiraDataService extends TimerTask{
	String userName = ConfigInfoDepository.WorkTime.USERNAME+"@oneapm.com";
	String password = ConfigInfoDepository.WorkTime.PASSWORD;
	  public static String escapeExprSpecialWord(String keyword) {
          if (keyword != null && !keyword.equals("")) {
                  String[] fbsArr = { "\\", "$", "'", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|","'","\""};
                  for (String key : fbsArr) {
                          if (keyword.contains(key)) {
                                  keyword = keyword.replaceAll("\\"+key, "");
                          }
                  }
          }
          return keyword;
	  }
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		IGetJiraDataService service = new GetJiraDataServiceImpl();
		List<JiraDataModel> jiraList = service.getJiraDataList();
		IJiraDataDao dao = new JiraDataDaoImpl();
		ICommentDao commentdao = new CommentDaoImpl();
		List<JiraDataModel> list = dao.selectJiraDataList();//jiraList为新获取到的数据，与数据库中的list对比。
		Map json = null;
		try {
			json = Kf5DataClean.getGongdanId();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		if(list.size()<jiraList.size()){
			int newNum = jiraList.size()-list.size();
			for(int i = 0; i < newNum; i++){
				JiraDataModel j  = jiraList.get(list.size()+i);
				String comment = escapeExprSpecialWord(j.getComment());
				j.setComment(comment);
				dao.insertJiraData(j);
			}
			dao.deleteRepeatData();
			list = dao.selectJiraDataList();
			
	 			for(int i = 0; i < list.size(); i++){
	 			List<CommentModel> commentlist = commentdao.selectCommentByKey(list.get(i).getIssueKey());
				JiraDataModel jira = dao.selectJiraData(list.get(i).getIssueKey());
				for(int j = 0; j < jiraList.size(); j++){
				if(jira.getIssueKey().equals(jiraList.get(j).getIssueKey())){
					if(jira.getComment().length()!=jiraList.get(j).getComment().length()){
						Map<String, Object> map;
						try {
							map = Kf5DataClean.getGongdanId();
							String gongdanId = (String)map.get(jira.getIssueKey());
							StringBuffer replySb = new StringBuffer();
							if(!jiraList.get(j).getStatus().equals(jira.getStatus())){
								Kf5ApiData.gongdanReply(gongdanId, "jira问题状态发生了变化：由"+jira.getStatus()+"变为"+jiraList.get(j).getStatus()+"(来自jira系统自动添加)", userName);
								dao.updateJiraStatusByIssueKey(jiraList.get(j));
								System.out.println("发生了回复事件：issueKey------------"+jiraList.get(j).getIssueKey());
								System.out.println("工单ID为"+json.get(jiraList.get(j).getIssueKey()));
							}
							if(!jiraList.get(j).getCreator().equals(jira.getCreator())){
								Kf5ApiData.gongdanReply(gongdanId, "jira问题开发者发生了变化：由"+jira.getCreator()+"变为"+jiraList.get(j).getCreator()+"(来自jira系统自动添加)", userName);
								dao.updateJiraDevByIssueKey(jiraList.get(j));
								System.out.println("发生了回复事件：issueKey------------"+jiraList.get(j).getIssueKey());
								System.out.println("工单ID为"+json.get(jiraList.get(j).getIssueKey()));
							}
							for(int l = commentlist.size(); l < jiraList.get(j).getCommentModel().size(); l++){
							//String userName = jiraList.get(i).getCommentModel().get(l).getName()+"@oneapm.com";
							String reply = jiraList.get(j).getCommentModel().get(l).getBody()+"by"+jiraList.get(j).getCommentModel().get(l).getDisplayName();
							replySb.append(reply+"\r\n");
							if(!commentdao.isexists(jiraList.get(j).getCommentModel().get(l).getId())){
								commentdao.insertComment(jiraList.get(j).getCommentModel().get(l));
								String status = jiraList.get(j).getStatus();
								String develop = jiraList.get(j).getCreator();
								int num = l+1;
								if(num == jiraList.get(j).getCommentModel().size()){
									Kf5ApiData.gongdanReply(gongdanId, replySb+"\r\n status:"+status+"\r\n develop:"+develop+"(来自jira系统自动添加)", userName);
									System.out.println("发生了回复事件：issueKey------------"+jiraList.get(j).getIssueKey());
									System.out.println("工单ID为"+json.get(jiraList.get(j).getIssueKey()));
									}
							}
							}
						} catch (URISyntaxException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dao.updateJiraData(jiraList.get(j));
						break;
					}
					break;
				}
					}
				
				}
		}else{
			dao.deleteRepeatData();
			list = dao.selectJiraDataList();
 			for(int i = 0; i < list.size(); i++){
 			List<CommentModel> commentlist = commentdao.selectCommentByKey(list.get(i).getIssueKey());
			JiraDataModel jira = dao.selectJiraData(list.get(i).getIssueKey());
			for(int j = 0; j < jiraList.size(); j++){
			if(jira.getIssueKey().equals(jiraList.get(j).getIssueKey())){
				if(jira.getComment().length()!=jiraList.get(j).getComment().length()){
					Map<String, Object> map;
					try {
						map = Kf5DataClean.getGongdanId();
						String gongdanId = (String)map.get(jira.getIssueKey());
						StringBuffer replySb = new StringBuffer();
						if(!jiraList.get(j).getStatus().equals(jira.getStatus())){
							dao.updateJiraStatusByIssueKey(jiraList.get(j));
							Kf5ApiData.gongdanReply(gongdanId, "jira问题状态发生了变化：由"+jira.getStatus()+"变为"+jiraList.get(j).getStatus()+"(来自jira系统自动添加)", userName);
						}
						if(!jiraList.get(j).getCreator().equals(jira.getCreator())){
							dao.updateJiraDevByIssueKey(jiraList.get(j));
							Kf5ApiData.gongdanReply(gongdanId, "jira问题开发者发生了变化：由"+jira.getCreator()+"变为"+jiraList.get(j).getCreator()+"(来自jira系统自动添加)", userName);
						}
						for(int l = commentlist.size(); l < jiraList.get(j).getCommentModel().size(); l++){
							String reply = jiraList.get(j).getCommentModel().get(l).getBody()+"by"+jiraList.get(j).getCommentModel().get(l).getDisplayName();
							replySb.append(reply+"\n");
							if(!commentdao.isexists(jiraList.get(j).getCommentModel().get(l).getId())){
								commentdao.insertComment(jiraList.get(j).getCommentModel().get(l));
								String status = jiraList.get(j).getStatus();
								String develop = jiraList.get(j).getCreator();
								int num = l+1;
								if(num == jiraList.get(j).getCommentModel().size()){
									Kf5ApiData.gongdanReply(gongdanId, replySb+"\n status:"+status+"\n develop:"+develop+"(来自jira系统自动添加)", userName);
									}
								}
							}
					} catch (URISyntaxException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dao.updateJiraData(jiraList.get(j));
					break;
				}
				break;
			}
				
			}
			
			}
		}
		
		
		
	}
	
}
