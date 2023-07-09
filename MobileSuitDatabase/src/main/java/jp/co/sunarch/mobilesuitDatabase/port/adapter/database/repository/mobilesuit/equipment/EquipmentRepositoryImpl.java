package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.DomaEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EquipmentRepositoryImpl implements EquipmentRepository {

	private final EquipmentRepositoryDao equipmentRepositoryDao;

	private final DomaEquipmentDao equipmentDao;

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
		Equipment equipment = new Equipment();

		Optional<DomaEquipmentEntity> equipmentEntityOpt = equipmentDao.selectByMsIdAndArmsId(msId, armsId);
		if (equipmentEntityOpt.isPresent()) {
			equipment = equipmentConverter.domaEntityToDomain(equipmentEntityOpt.get());
		}

		return equipment;
	}

	@Override
	public void save(Equipment equipment) {
		Optional<DomaEquipmentEntity> before = equipmentDao.selectByMsIdAndArmsId(
				equipment.getMsId().getValue(),
				equipment.getArmsId().getValue());
		if(before.isPresent()) {
			DomaEquipmentEntity equipmentEntity = equipmentConverter.domaDomainToEntity(equipment);
			equipmentDao.update(equipmentEntity);
		} else {
			DomaEquipmentEntity equipmentEntity = equipmentConverter.domaDomainToEntity(equipment);
			equipmentDao.insert(equipmentEntity);
		}
	}

	@Override
	public void deleteEquipmentByMsIdAndArmsId(String msId, String armsId) {
		equipmentDao.deleteByMsIdAndArmsId(msId, armsId);
	}

	@Override
	public void deleteEquipmentByMsid(String msId) {
		equipmentRepositoryDao.deleteByMsId(msId);
	}

	@Override
	public void deleteEquipmentByArmsId(String armsId) {
		equipmentRepositoryDao.deleteByArmsId(armsId);
	}

}
