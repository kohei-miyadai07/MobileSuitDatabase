package jp.co.sunarch.mobilesuitDatabase.application.repository.equipment;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.domain.model.equipment.Equipment;

public interface EquipmentRepository {

	List<Equipment> getEquipmentListByMsId(String msId);

	int deleteEquipmentByMsid(String msId);
}
