package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.mpk.TAchievmentMPK;
import jp.co.bungeejump.tokuban.entity.real.TAchievement;

/**
 *
 * @author 小河原
 * @version 0.0.1
 */
@Repository
public interface TAchievementDao extends JpaRepository<TAchievement, TAchievmentMPK> {

	@Query("select o from TAchievement o where o.userId = :userId and o.achievementId = :achievementId")
	List<TAchievement> findByCompositePrimaryKey(@Param("userId")Integer userId, @Param("achievementId")Integer achievementId);
}
