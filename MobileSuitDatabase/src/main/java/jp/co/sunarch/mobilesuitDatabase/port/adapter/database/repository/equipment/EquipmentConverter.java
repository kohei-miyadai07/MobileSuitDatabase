package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment.entity.EquipmentEntity;

@Component
public class EquipmentConverter {

	public Equipment entityToDomain(EquipmentEntity entity) {
		Equipment equipment = Equipment.create(
				MobileSuitId.of(entity.getMsId())
				,ArmsId.of(entity.getArmsId())
				,entity.getNumberEquipment()
				,entity.getDetail());

		return equipment;
	}
}
