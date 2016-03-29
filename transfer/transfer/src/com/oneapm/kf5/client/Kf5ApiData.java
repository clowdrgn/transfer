package com.oneapm.kf5.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.server.ParamException;

import com.oneapm.model.UserGroups;


public class Kf5ApiData {
	final static String baseUrl = "http://manage.oneapm.com/info_gongdan";
	final static String userUrl = "http://manage.oneapm.com/user_group_findByUserId?groupId=";
	final static String qqUrl = "http://123.56.156.93:8880/WebApiInsert";
//	final static String userUrl = "http://localhost:8080/oneapm-manage/user_group_findByUserId?groupId=";
	public static String getGongdanList() throws IOException {
		URL url = null;
		HttpURLConnection connection;
		BufferedReader br;
		url = new URL(baseUrl); // 把字符串转换为URL请求地址
		connection = (HttpURLConnection) url.openConnection();// 打开连接
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
			br.close();
			connection.disconnect();// 断开连接

            
			return sb.toString();
			}
	
	public static String findByGroupId(Long id) throws IOException {
		URL url = null;
		HttpURLConnection connection;
		BufferedReader br;
		String u = userUrl + id;
		url = new URL(u); // 把字符串转换为URL请求地址
		connection = (HttpURLConnection) url.openConnection();// 打开连接
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
			br.close();
			connection.disconnect();// 断开连接
			String name = sb.toString();
			return name;
		} 
	public static String checkMessage() throws IOException {
		URL url = null;
		HttpURLConnection connection;
		BufferedReader br;
		String u = qqUrl;
		url = new URL(u); // 把字符串转换为URL请求地址
		connection = (HttpURLConnection) url.openConnection();// 打开连接
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
			br.close();
			connection.disconnect();// 断开连接
			String name = sb.toString();
			return name;
	}
	public static String gongdanReply(String gongdanId, String gongdanContent, String userName) throws IOException {
		URL url = null;
		InputStreamReader in = null;
		BufferedReader br;
		String result = null;
		gongdanContent = URLEncoder.encode(gongdanContent, "utf-8");
		gongdanContent = URLEncoder.encode(gongdanContent, "utf-8");
		String u = baseUrl+"Reply?gongdanId="+gongdanId+"&gongdanContent="+gongdanContent+"&gongdanUserName="+userName+"";
		url = new URL(u); // 把字符串转换为URL请求地址
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接;
		connection.connect();// 连接会话
		// 获取输入流
		br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {// 循环读取流
			sb.append(line);
		}
			br.close();
			connection.disconnect();// 断开连接

            
			return sb.toString();
	}
	public static void main(String[] args) {
		try {
			/*String str = getGongdanList();
			String gongdanJson = DecodeUni.decodeUnicode(str);
			System.out.println(gongdanJson);*/
//			String str = gongdanReply("100135","运营接口测试","lijiang@oneapm.com");
			String str = findByGroupId(520L);
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
