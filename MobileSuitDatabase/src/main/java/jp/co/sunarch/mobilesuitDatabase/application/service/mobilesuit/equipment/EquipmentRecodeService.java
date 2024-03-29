package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EquipmentRecodeService {

	private final EquipmentRepository equipmentRepository;
	
	public void registEquipment(Equipment equipment) {
		equipmentRepository.save(equipment);
	}

	public void updateEquipment(Equipment equipment) {
		equipmentRepository.save(equipment);
	}

	public void deleteEquipment(Equipment equipment) {
		equipmentRepository.deleteEquipmentByMsIdAndArmsId(
				equipment.getMsId().getValue(), equipment.getArmsId().getValue());
	}

	public void deleteEquipmentByMobileSuit(MobileSuit mobileSuit) {
		equipmentRepository.deleteEquipmentByMsid(mobileSuit.getMsId().getValue());
	}

	public void deleteEquipmentByArms(Arms arms) {
		equipmentRepository.deleteEquipmentByArmsId(arms.getArmsId().getValue());
	}

	
}
