package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.DomaEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;

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

	public EquipmentEntity domainToEntity(Equipment domain) {
		EquipmentEntity entity = new EquipmentEntity();
		entity.setMsId(domain.getMsId().getValue());
		entity.setArmsId(domain.getArmsId().getValue());
		entity.setNumberEquipment(domain.getNumberEquipment());
		entity.setDetail(domain.getDetail());

		return entity;
	}

	public Equipment domaEntityToDomain(DomaEquipmentEntity entity) {
		Equipment equipment = Equipment.create(
				MobileSuitId.of(entity.getMsId())
				,ArmsId.of(entity.getArmsId())
				,entity.getNumberEquipment()
				,entity.getDetail());

		return equipment;
	}

	public DomaEquipmentEntity domaDomainToEntity(Equipment domain) {
		DomaEquipmentEntity entity = new DomaEquipmentEntity();
		entity.setMsId(domain.getMsId().getValue());
		entity.setArmsId(domain.getArmsId().getValue());
		entity.setNumberEquipment(domain.getNumberEquipment());
		entity.setDetail(domain.getDetail());

		return entity;
	}
}
