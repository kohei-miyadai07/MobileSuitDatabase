package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.ArmsEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArmsRepositoryImpl implements ArmsRepository {

	private final ArmsRepositoryDao armsRepositoryDao;

	private final ArmsConverter armsConverter;

	@Override
	public Arms getArmsById(String armsId) {
		ArmsEntity armsEntity = armsRepositoryDao.selectArmsById(armsId);
		Arms arms = armsConverter.entityToDomain(armsEntity);

		return arms;
	}

	@Override
	public void save(Arms arms) {
		ArmsEntity before = armsRepositoryDao.selectArmsById(arms.getArmsId().getValue());
		if (before == null) {
			ArmsEntity armsEntity = armsConverter.domainToEntity(arms);
			armsRepositoryDao.insert(armsEntity);
		} else {
			ArmsEntity armsEntity = armsConverter.domainToEntity(arms);
			armsRepositoryDao.update(armsEntity);
		}
	}

	@Override
	public void deleteArmsById(String armsId) {
		armsRepositoryDao.deleteById(armsId);
	}

}
