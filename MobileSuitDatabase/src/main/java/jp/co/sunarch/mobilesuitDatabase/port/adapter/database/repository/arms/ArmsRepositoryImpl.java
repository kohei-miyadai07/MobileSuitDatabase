package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.ArmsEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArmsRepositoryImpl implements ArmsRepository {

	private final ArmsDao armsDao;

	private final ArmsConverter armsConverter;

	@Override
	public Arms getArmsById(String armsId) {
		Arms arms = new Arms();

		Optional<ArmsEntity> armsEntityOpt = armsDao.selectById(armsId);
		if (armsEntityOpt.isPresent()) {
			arms = armsConverter.entityToDomain(armsEntityOpt.get());
		}

		return arms;

	}

	@Override
	public void save(Arms arms) {
		Optional<ArmsEntity> before = armsDao.selectById(arms.getArmsId().getValue());
		if (before.isPresent()) {
			ArmsEntity armsEntity = armsConverter.domainToEntity(arms);
			armsDao.update(armsEntity);
		} else {
			ArmsEntity armsEntity = armsConverter.domainToEntity(arms);
			armsDao.insert(armsEntity);
		}
	}

	@Override
	public void deleteArmsById(String armsId) {
		armsDao.deleteById(armsId);

	}

}
