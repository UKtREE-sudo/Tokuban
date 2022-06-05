package jp.co.bungeejump.tokuban.dao.real;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.MRegion;

/**
 * m_regionから情報を取得するdao
 * @author 小野
 * @version 0.0.1
 */
@Repository
public interface MRegionDao extends JpaRepository<MRegion, Integer>{
//	MRegion getById(Integer regionId);

}
