package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EquipmentRecodeService {

	private final EquipmentRepository equipmentRepository;

	public int deleteEquipmentByMobileSuit(MobileSuit mobileSuit) {
		return equipmentRepository.deleteEquipmentByMsid(mobileSuit.getMsId().getValue());
	}

	public int deleteEquipmentByArms(Arms arms) {
		return equipmentRepository.deleteEquipmentByArmsId(arms.getArmsId().getValue());
	}

	
}
