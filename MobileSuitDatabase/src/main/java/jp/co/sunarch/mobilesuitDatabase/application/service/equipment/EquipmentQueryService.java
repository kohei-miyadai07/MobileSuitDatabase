package jp.co.sunarch.mobilesuitDatabase.application.service.equipment;

import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EquipmentQueryService {
	
	private final EquipmentRepository equipmentRepository;

	public List<Equipment> getEquipmentByMobileSuitId(MobileSuitId msId) {
		return equipmentRepository.getEquipmentListByMsId(msId.getValue());
	}

	public List<Equipment> getEquipmentByArmsId(ArmsId armsId) {
		return equipmentRepository.getEquipmentListByArmsId(armsId.getValue());
	}

}
