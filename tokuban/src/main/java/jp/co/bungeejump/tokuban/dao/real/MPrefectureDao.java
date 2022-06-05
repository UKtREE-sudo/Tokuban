package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.MPrefecture;

@Repository
public interface MPrefectureDao extends JpaRepository <MPrefecture , Integer>{

	List<MPrefecture> findByPrefectureName(String prefectureName);

}
