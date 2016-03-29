package com.oneapm.dao;

import java.util.List;
import com.oneapm.model.Kf5Model;

public interface ITransferDao {
	
	/**
	 * @return
	 */
	public List<Kf5Model> selectTransferList();

	/**
	 * @param kf5Model
	 */
	public void insertTransferFromKf5(Kf5Model kf5Model);
}
