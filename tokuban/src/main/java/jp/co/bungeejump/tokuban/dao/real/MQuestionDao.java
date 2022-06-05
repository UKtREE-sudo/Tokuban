package jp.co.bungeejump.tokuban.dao.real;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.MQuestion;

/**
 * m_questionから情報を取得するdao
 * @author 小野
 * @version 0.2.0
 */
@Repository
public interface MQuestionDao extends JpaRepository<MQuestion, Integer> {
	//MQuestion getById(Integer qustionId);

}
