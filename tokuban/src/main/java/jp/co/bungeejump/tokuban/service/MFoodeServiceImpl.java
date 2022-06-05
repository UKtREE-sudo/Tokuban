package jp.co.bungeejump.tokuban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MFoodDao;
import jp.co.bungeejump.tokuban.entity.real.MFood;

@Service
public class MFoodeServiceImpl implements MFoodService {

	@Autowired
	private MFoodDao mfDao;

	@Override
	public List<MFood> findAll() {

		return mfDao.findAll();
	}

	@Override
	public MFood getById(Integer merchId) {
		
		return mfDao.getById(merchId);
	}

	@Override
	public void save(Integer merchId) {
	}

	@Override
	public void delete(Integer merchId) {
	}

	
}
