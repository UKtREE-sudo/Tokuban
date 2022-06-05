package jp.co.bungeejump.tokuban.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MUserDao;
import jp.co.bungeejump.tokuban.dao.real.TAddressDao;
import jp.co.bungeejump.tokuban.entity.real.TAddress;
import jp.co.bungeejump.tokuban.form.AddressForm;

/**
* 配送先に関するサービスの実装クラス
* @author 大野
* @version 0.2.0
*/

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private TAddressDao tAddDao;

	@Autowired
	private MUserDao mUserDao;


	@Override
	public TAddress getAddress1(Integer userId) {
		TAddress tAddress;
		Integer address1 = mUserDao.getById(userId).getAddress1Id();
		if (address1 == null) {
			tAddress = null;
		} else {
			tAddress = tAddDao.getById(address1);
		}
		return tAddress;
	}

	@Override
	public TAddress getAddress2(Integer userId) {
		TAddress tAddress;
		Integer address2 = mUserDao.getById(userId).getAddress2Id();
		if (address2 == null) {
			tAddress = null;
		} else {
			tAddress = tAddDao.getById(address2);
		}
		return tAddress;
	}

	@Transactional
	@Override
	public TAddress editAddress1(AddressForm addressForm, Integer userId) {
		//トランザクションに入れてからユーザーテーブルに入れる
		TAddress tAddress = new TAddress();
		tAddress.setToName(addressForm.getToName());
		tAddress.setZipcode(addressForm.getZipcode());
		tAddress.setAddressCore(addressForm.getAddressCore());
		tAddress.setAddressNumber(addressForm.getAddressNumber());
		tAddress.setAddressBuilding(addressForm.getAddressBuilding());
		TAddress generatedEntity = tAddDao.save(tAddress);
		mUserDao.updateAddress1(generatedEntity.getAddressId(), userId);

		return generatedEntity;
	}

	@Transactional
	@Override
	public TAddress editAddress2(AddressForm addressForm, Integer userId) {
		//トランザクションに入れてからユーザーテーブルに入れる
		TAddress tAddress = new TAddress();
		tAddress.setToName(addressForm.getToName());
		tAddress.setZipcode(addressForm.getZipcode());
		tAddress.setAddressCore(addressForm.getAddressCore());
		tAddress.setAddressNumber(addressForm.getAddressNumber());
		tAddress.setAddressBuilding(addressForm.getAddressBuilding());
		TAddress generatedEntity = tAddDao.save(tAddress);
		mUserDao.updateAddress2(generatedEntity.getAddressId(), userId);

		return generatedEntity;
	}

	@Override
	public void deleteAddress1(Integer userId) {
		mUserDao.updateAddress1null(userId);
	}

	@Override
	public void deleteAddress2(Integer userId) {
		mUserDao.updateAddress2null(userId);
	}

	@Override
	@Transactional
	public Integer insertTmpAddress(AddressForm addressForm) {
		TAddress tAddress = new TAddress();
		tAddress.setToName(addressForm.getToName());
		tAddress.setZipcode(addressForm.getZipcode());
		tAddress.setAddressCore(addressForm.getAddressCore());
		tAddress.setAddressNumber(addressForm.getAddressNumber());
		tAddress.setAddressBuilding(addressForm.getAddressBuilding());
		TAddress generatedAddress = tAddDao.save(tAddress);
		return generatedAddress.getAddressId();
	}

}
