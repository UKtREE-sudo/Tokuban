package jp.co.bungeejump.tokuban.service;

import java.util.List;

import jp.co.bungeejump.tokuban.entity.real.MFood;

public interface MFoodService {

	public List<MFood> findAll();
	
	public MFood getById(Integer merchId);
	
	public void save(Integer merchId);
	
	public void delete(Integer merchId);
}
