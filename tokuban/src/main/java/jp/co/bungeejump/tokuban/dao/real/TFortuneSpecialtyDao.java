package jp.co.bungeejump.tokuban.dao.real;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.TFortuneSpecialty;

/**
 * t_fortune_specialtyから情報を取得するdao
 * @author 小野
 * @version 0.2.0
 */
@Repository
public interface TFortuneSpecialtyDao extends JpaRepository<TFortuneSpecialty, Date> {
	//Optional<TFortuneSpecialty> findById(Date date);
}
