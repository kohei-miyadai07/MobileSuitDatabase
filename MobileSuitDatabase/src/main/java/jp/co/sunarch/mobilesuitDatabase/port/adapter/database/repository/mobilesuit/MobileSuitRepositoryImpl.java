package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.DomaMobileSuitEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitRepositoryImpl implements MobileSuitRepository {

	private final MobileSuitRepositoryDao mobileSuitRepositoryDao;

	private final DomaMobileSuitDao mobileSuitDao;

	private final MobileSuitConverter mobileSuitConverter;

	@Override
	public MobileSuit getMobileSuitById(String msId) {
		MobileSuit mobilesuit = new MobileSuit();

		Optional<DomaMobileSuitEntity> msEntityOpt = mobileSuitDao.selectById(msId);
		if (msEntityOpt.isPresent()) {
			mobilesuit = mobileSuitConverter.entityToDomain(msEntityOpt.get());
		}

		return mobilesuit;
	}

	@Override
	public void save(MobileSuit mobileSuit) {
		Optional<DomaMobileSuitEntity> before = mobileSuitDao.selectById(mobileSuit.getMsId().getValue());
		if (before.isPresent()) {
			DomaMobileSuitEntity msEntity = mobileSuitConverter.domadomainToEntity(mobileSuit);
			mobileSuitDao.update(msEntity);
		} else {
			DomaMobileSuitEntity msEntity = mobileSuitConverter.domadomainToEntity(mobileSuit);
			mobileSuitDao.insert(msEntity);
		}
	}

	@Override
	public void deleteMobileSuitById(String msId) {
		mobileSuitRepositoryDao.deleteById(msId);
	}

}
