package com.oneapm.service;

import java.net.URISyntaxException;
import java.util.List;

import com.oneapm.model.Kf5Model;

public interface ICompareKf5Service {
	public List<Kf5Model> selectTable(String jiraNum) throws URISyntaxException;
	
}
