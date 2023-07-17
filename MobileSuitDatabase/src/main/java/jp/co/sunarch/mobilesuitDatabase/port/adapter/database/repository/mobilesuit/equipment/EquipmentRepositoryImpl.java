package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepository {

	private final EquipmentDao equipmentDao;

	private final EquipmentConverter equipmentConverter;

	@Override
	public Equipment getEquipmentByMsIdAndArmsId(String msId, String armsId) {
		Equipment equipment = new Equipment();

		Optional<EquipmentEntity> equipmentEntityOpt = equipmentDao.selectByMsIdAndArmsId(msId, armsId);
		if (equipmentEntityOpt.isPresent()) {
			equipment = equipmentConverter.entityToDomain(equipmentEntityOpt.get());
		}

		return equipment;
	}

	@Override
	public void save(Equipment equipment) {
		Optional<EquipmentEntity> before = equipmentDao.selectByMsIdAndArmsId(
				equipment.getMsId().getValue(),
				equipment.getArmsId().getValue());
		if(before.isPresent()) {
			EquipmentEntity equipmentEntity = equipmentConverter.domainToEntity(equipment);
			equipmentDao.update(equipmentEntity);
		} else {
			EquipmentEntity equipmentEntity = equipmentConverter.domainToEntity(equipment);
			equipmentDao.insert(equipmentEntity);
		}
	}

	@Override
	public void deleteEquipmentByMsIdAndArmsId(String msId, String armsId) {
		equipmentDao.deleteByMsIdAndArmsId(msId, armsId);
	}

}
