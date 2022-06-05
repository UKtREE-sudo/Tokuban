package jp.co.bungeejump.tokuban.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MQuestionDao;
import jp.co.bungeejump.tokuban.dao.real.TFortuneSpecialtyDao;
import jp.co.bungeejump.tokuban.dao.real.VMerchDao;
import jp.co.bungeejump.tokuban.entity.real.MQuestion;
import jp.co.bungeejump.tokuban.entity.real.TFortuneSpecialty;
import jp.co.bungeejump.tokuban.entity.real.VMerch;

/**
 * ラッキー特産品関係の処理をやるService
 * @author 小野
 * @version 0.2.0
 */

@Service
public class FortuneSpecialtyServiceImpl implements FortuneSpecialtyService {

	@Autowired
	private MQuestionDao mQuestionDao;
	@Autowired
	private TFortuneSpecialtyDao tFortuneDao;
	@Autowired
	private VMerchDao vMerchDao;


	//t_fortune_specialtyの問題IDをm_questionを使って探す
	@Override
	public List<MQuestion> getQuestion(Date date) {

		TFortuneSpecialty tfs = tFortuneDao.getById(date);

		Integer q1 = tfs.getQ1Id();
		Integer q2 = tfs.getQ2Id();
		Integer q3 = tfs.getQ3Id();

		List<MQuestion> questions = new ArrayList<MQuestion>();
		questions.add(mQuestionDao.getById(q1));
		questions.add(mQuestionDao.getById(q2));
		questions.add(mQuestionDao.getById(q3));

		return questions;
	}

	//t_fortune_specialtyの商品IDをVMerchを使って探す
	@Override
	public List<VMerch> getMerch(Date date) {

		TFortuneSpecialty tfs = tFortuneDao.getById(date);

		Integer m1 = tfs.getFs1Id();
		Integer m2 = tfs.getFs2Id();
		Integer m3 = tfs.getFs3Id();
		Integer m4 = tfs.getFs4Id();
		Integer m5 = tfs.getFs5Id();
		Integer m6 = tfs.getFs6Id();
		Integer m7 = tfs.getFs7Id();
		Integer m8 = tfs.getFs8Id();

		List<VMerch> merchs = new ArrayList<VMerch>();
		merchs.add(vMerchDao.getById(m1));
		merchs.add(vMerchDao.getById(m2));
		merchs.add(vMerchDao.getById(m3));
		merchs.add(vMerchDao.getById(m4));
		merchs.add(vMerchDao.getById(m5));
		merchs.add(vMerchDao.getById(m6));
		merchs.add(vMerchDao.getById(m7));
		merchs.add(vMerchDao.getById(m8));

		return merchs;
	}

}
