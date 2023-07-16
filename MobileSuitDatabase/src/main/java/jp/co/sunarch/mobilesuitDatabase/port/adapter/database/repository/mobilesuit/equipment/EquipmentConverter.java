package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;

@Component
public class EquipmentConverter {

	public Equipment entityToDomain(EquipmentEntity entity) {
		Equipment domain = Equipment.create(
				MobileSuitId.of(entity.getMsId())
				,ArmsId.of(entity.getArmsId())
				,entity.getNumberEquipment()
				,entity.getDetail());

		return domain;
	}

	public EquipmentEntity domainToEntity(Equipment domain) {
		EquipmentEntity entity = new EquipmentEntity();
		entity.setMsId(domain.getMsId().getValue());
		entity.setArmsId(domain.getArmsId().getValue());
		entity.setNumberEquipment(domain.getNumberEquipment());
		entity.setDetail(domain.getDetail());

		return entity;
	}
}
