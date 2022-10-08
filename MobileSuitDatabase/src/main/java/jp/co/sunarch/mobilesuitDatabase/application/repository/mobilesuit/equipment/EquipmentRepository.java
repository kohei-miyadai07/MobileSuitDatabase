package jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

public interface EquipmentRepository {

	List<Equipment> getEquipmentListByMsId(String msId);

	List<Equipment> getEquipmentListByArmsId(String armsId);

	int deleteEquipmentByMsid(String msId);

	int deleteEquipmentByArmsId(String armsId);

}
