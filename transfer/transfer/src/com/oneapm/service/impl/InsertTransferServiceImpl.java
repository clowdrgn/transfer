package com.oneapm.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.oneapm.dao.ITransferDao;
import com.oneapm.dao.impl.TransferDaoImpl;
import com.oneapm.init.ConfigInfoDepository;
import com.oneapm.model.Kf5Model;
import com.oneapm.service.CheckJiraStatus;
import com.oneapm.service.IInsertTransferService;

public class InsertTransferServiceImpl implements IInsertTransferService{
	String userName = ConfigInfoDepository.WorkTime.USERNAME;
	String password = ConfigInfoDepository.WorkTime.PASSWORD;
	@Override
	public void insertKf5(List<Kf5Model> list) throws URISyntaxException {
		CheckJiraStatus.setJiraNumsAndStatus(list);
		//CheckJiraStatus.setJiraStatus(list);
		ITransferDao dao = new TransferDaoImpl();
		for(int i = 0; i < list.size(); i++){
		dao.insertTransferFromKf5(list.get(i));
		}
	}

}
