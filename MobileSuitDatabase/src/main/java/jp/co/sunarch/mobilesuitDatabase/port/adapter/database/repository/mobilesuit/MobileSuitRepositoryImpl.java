package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitRepositoryImpl implements MobileSuitRepository {

	private final MobileSuitRepositoryDao mobileSuitRepositoryDao;

	private final MobileSuitConverter mobileSuitConverter;

	@Override
	public int RegistMobileSuit(MobileSuit mobileSuit) {

		MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);

		int result = mobileSuitRepositoryDao.insertOneMobileSuit(msEntity);

		return result;
	}

}
