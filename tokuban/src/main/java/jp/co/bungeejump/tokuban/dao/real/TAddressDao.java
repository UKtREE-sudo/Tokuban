package jp.co.bungeejump.tokuban.dao.real;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.TAddress;

/**
 * t_addressテーブルにアクセスするdao
 * @author 大野
 * @version 0.0.0
 *
 */
@Repository
public interface TAddressDao extends JpaRepository<TAddress, Integer> {

}
