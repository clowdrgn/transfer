package com.oneapm.service;

import java.net.URISyntaxException;
import java.util.List;

import com.oneapm.model.Kf5Model;

public interface IInsertTransferService {
	public void insertKf5(List<Kf5Model> list) throws URISyntaxException;
}
