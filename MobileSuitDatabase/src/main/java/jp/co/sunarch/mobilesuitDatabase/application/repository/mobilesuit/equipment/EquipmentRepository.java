package jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

public interface EquipmentRepository {

	Equipment getEquipmentByMsIdAndArmsId(String msId, String armsId);

	void save(Equipment equipment);

	void deleteEquipmentByMsIdAndArmsId(String msId, String armsId);

}
