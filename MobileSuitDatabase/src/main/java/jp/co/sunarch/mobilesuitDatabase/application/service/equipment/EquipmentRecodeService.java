package jp.co.sunarch.mobilesuitDatabase.application.service.equipment;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EquipmentRecodeService {

	private final EquipmentRepository equipmentRepository;

	public int deleteEquipmentByMobileSuit(MobileSuitId msId) {
		return equipmentRepository.deleteEquipmentByMsid(msId.getValue());
	}
}
