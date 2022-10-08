package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment.entity.EquipmentEntity;
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
	public int deleteEquipmentByMsid(String msId) {
		return equipmentRepositoryDao.deleteByMsId(msId);
	}

	@Override
	public int deleteEquipmentByArmsId(String armsId) {
		return equipmentRepositoryDao.deleteByArmsId(armsId);
	}

}
