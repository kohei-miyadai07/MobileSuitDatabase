package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitRepositoryImpl implements MobileSuitRepository {

	private final MobileSuitDao mobileSuitDao;

	private final MobileSuitConverter mobileSuitConverter;

	@Override
	public MobileSuit getMobileSuitById(String msId) {
		MobileSuit mobilesuit = new MobileSuit();

		Optional<MobileSuitEntity> msEntityOpt = mobileSuitDao.selectById(msId);
		if (msEntityOpt.isPresent()) {
			mobilesuit = mobileSuitConverter.entityToDomain(msEntityOpt.get());
		}

		return mobilesuit;
	}

	@Override
	public void save(MobileSuit mobileSuit) {
		Optional<MobileSuitEntity> before = mobileSuitDao.selectById(mobileSuit.getMsId().getValue());
		if (before.isPresent()) {
			MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);
			mobileSuitDao.update(msEntity);
		} else {
			MobileSuitEntity msEntity = mobileSuitConverter.domainToEntity(mobileSuit);
			mobileSuitDao.insert(msEntity);
		}
	}

	@Override
	public void deleteMobileSuitById(String msId) {
		mobileSuitDao.deleteById(msId);
	}
}
