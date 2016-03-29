package com.oneapm.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.oneapm.dao.impl.TransferDaoImpl;
import com.oneapm.jira.client.JiraIssueClient;
import com.oneapm.kf5.client.Kf5ApiData;

public class Kf5DataClean {
	public static Map checkGongdan() throws URISyntaxException{
		try {
			
			String json = Kf5ApiData.getGongdanList();
			JSONObject  dataJson=new JSONObject(json);
			JSONArray data = dataJson.getJSONArray("datas");
			Map map = new HashMap();
			int l = 1;
			for(int i = 0; i < data.length(); i++){
			JSONObject jiraNum = data.getJSONObject(i);
			String jiraNumString = jiraNum.getString("field_3365").replaceAll("\\s+","");
			
			if(jiraNumString !="null"&&jiraNumString != ""&&jiraNumString != null&&jiraNumString.length()!=0){
				String key = jiraNumString.substring(29,jiraNumString.length()).replace("/", "");				
				if(key.contains("?")){
					int q =  key.indexOf("?"); 
					key = key.substring(0, q);
				}
				map.put(l, key);
				//String status = JiraIssueClient.getIssueStatus(key, userName, password);
				l++;
			}
			
		}
			JSONArray new_ja = new JSONArray(); 
			new_ja.put(map);
			return map;
			
			
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("转换为json字符串时出现了问题……………………………………………………");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException……………………………………………………");
		}
		return null;
	}
	public static Map getGongdanId() throws URISyntaxException{
		try {
			
			String json = Kf5ApiData.getGongdanList();
			JSONObject  dataJson=new JSONObject(json);
			JSONArray data = dataJson.getJSONArray("datas");
			Map<String,Object> map = new HashMap<String,Object>();
			
			for(int i = 0; i < data.length(); i++){
			JSONObject jiraNum = data.getJSONObject(i);
			String jiraNumString = jiraNum.getString("field_3365").replaceAll("\\s+","");
			
			if(jiraNumString !="null"&&jiraNumString != ""&&jiraNumString != null&&jiraNumString.length()!=0){
				String key = jiraNumString.substring(29,jiraNumString.length()).replace("/", "");
				if(key.contains("?")){
					int q =  key.indexOf("?"); 
					key = key.substring(0, q);
				}
				String id = jiraNum.getString("id");			
				map.put(key, id);
				//String status = JiraIssueClient.getIssueStatus(key, userName, password);
				
			}
			
		}
			JSONArray new_ja = new JSONArray(); 
			new_ja.put(map);
			return map;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	public static void main(String[] args) {
		try {
			Map json = getGongdanId();
			json.get("CS-20");
			System.out.println(json.get("PHP-288"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
