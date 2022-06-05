package jp.co.bungeejump.tokuban.dao.real;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.bungeejump.tokuban.entity.real.MFood;

public interface MFoodDao extends JpaRepository<MFood, Integer> {

}
