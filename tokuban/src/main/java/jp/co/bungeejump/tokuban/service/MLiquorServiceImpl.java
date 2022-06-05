package jp.co.bungeejump.tokuban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MLiquorDao;
import jp.co.bungeejump.tokuban.entity.real.MLiquor;

@Service
public class MLiquorServiceImpl implements MLiquorService {

	@Autowired
	private MLiquorDao dao;

	@Override
	public List<MLiquor> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public MLiquor getById(Integer merchId) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.getById(merchId);
	}

}
