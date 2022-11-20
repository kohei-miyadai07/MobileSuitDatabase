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
	public MobileSuit getMobileSuitById(String msId) {
		MobileSuitEntity msEntity = mobileSuitRepositoryDao.selectMobileSuitById(msId);
		MobileSuit mobileSuit = mobileSuitConverter.entityToDomain(msEntity);

		return mobileSuit;
	}

	@Override
	public void save(MobileSuit mobileSuit) {
		MobileSuitEntity before = mobileSuitRepositoryDao.selectMobileSuitById(mobileSuit.getMsId().getValue());
		if (before == null) {
			MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);
			mobileSuitRepositoryDao.insert(msEntity);
		} else {
			MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);
			mobileSuitRepositoryDao.update(msEntity);
		}
	}

	@Override
	public void deleteMobileSuitById(String msId) {
		mobileSuitRepositoryDao.deleteById(msId);
	}

}
