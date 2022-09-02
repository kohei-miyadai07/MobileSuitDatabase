package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.util.List;

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
	public int registMobileSuit(MobileSuit mobileSuit) {

		MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);

		int result = mobileSuitRepositoryDao.insert(msEntity);

		return result;
	}

	@Override
	public int updateMobileSuit(MobileSuit mobileSuit) {

		MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);

		int result = mobileSuitRepositoryDao.update(msEntity);

		return result;
	}

	@Override
	public MobileSuit getMobileSuitById(String msId) {

		MobileSuitEntity msEntity = mobileSuitRepositoryDao.selectMobileSuitById(msId);

		MobileSuit mobileSuit = mobileSuitConverter.entityToDomain(msEntity);

		return mobileSuit;
	}

	@Override
	public int save(MobileSuit mobileSuit) {
		
		int result;
		
		MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);
		List<String> msIds = mobileSuitRepositoryDao.selectMobileSuitIdById(msEntity.getMsId());

		if (msIds.isEmpty()) {
			result = mobileSuitRepositoryDao.insert(msEntity);
		} else {
			result = mobileSuitRepositoryDao.update(msEntity);
		}

		return result;
	}

}
