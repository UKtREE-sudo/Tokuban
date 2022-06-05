package jp.co.bungeejump.tokuban.dao.real;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.MAchievement;

/**
 *
 * @author 小河原
 * @version 0.0.1
 */
@Repository
public interface MAchievementDao extends JpaRepository <MAchievement , Integer>{


}
