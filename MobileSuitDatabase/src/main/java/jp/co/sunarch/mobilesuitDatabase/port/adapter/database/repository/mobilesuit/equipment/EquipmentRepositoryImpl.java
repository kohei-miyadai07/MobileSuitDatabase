package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepository {

	private final EquipmentRepositoryDao equipmentRepositoryDao;
	private final EquipmentConverter equipmentConverter;

	@Override
	public List<Equipment> getEquipmentListByMsId(String msId) {
		List<EquipmentEntity> equipmentEntityList = equipmentRepositoryDao.selectByMsId(msId);
		List<Equipment> equipmentList = equipmentEntityList.stream().map(e -> equipmentConverter.entityToDomain(e)).toList();

		return equipmentList;
	}

	@Override
	public List<Equipment> getEquipmentListByArmsId(String armsId) {
		List<EquipmentEntity> equipmentEntityList = equipmentRepositoryDao.selectByArmsId(armsId);
		List<Equipment> equipmentList = equipmentEntityList.stream().map(e -> equipmentConverter.entityToDomain(e)).toList();

		return equipmentList;
	}

	@Override
	public Equipment getEquipmentByMsIdAndArmsId(String msId, String armsId) {
		EquipmentEntity equipmentEntity = equipmentRepositoryDao.selectByMsIdAndArmsId(msId, armsId);
		Equipment equipment = equipmentConverter.entityToDomain(equipmentEntity);

		return equipment;
	}

	@Override
	public int save(Equipment equipment) {
		int result = 0;

		EquipmentEntity before = equipmentRepositoryDao.selectByMsIdAndArmsId(
				equipment.getMsId().getValue(), 
				equipment.getArmsId().getValue());
		if(before == null) {
			EquipmentEntity equipmentEntity = equipmentConverter.domainToEntity(equipment);
			result = equipmentRepositoryDao.insert(equipmentEntity);
		} else {
			EquipmentEntity equipmentEntity = equipmentConverter.domainToEntity(equipment);
			result = equipmentRepositoryDao.update(equipmentEntity);
		}

		return result;
	}

	@Override
	public int deleteEquipmentByMsIdAndArmsId(String msId, String armsId) {
		return equipmentRepositoryDao.deleteByMsIdAndArmsId(msId, armsId);
	}

	@Override
	public int deleteEquipmentByMsid(String msId) {
		return equipmentRepositoryDao.deleteByMsId(msId);
	}

	@Override
	public int deleteEquipmentByArmsId(String armsId) {
		return equipmentRepositoryDao.deleteByArmsId(armsId);
	}

}
