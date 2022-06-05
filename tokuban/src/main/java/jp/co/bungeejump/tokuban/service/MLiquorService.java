package jp.co.bungeejump.tokuban.service;

import java.util.List;

import jp.co.bungeejump.tokuban.entity.real.MLiquor;

public interface MLiquorService {

	public List<MLiquor> findAll();

	public MLiquor getById(Integer merchId);
}
