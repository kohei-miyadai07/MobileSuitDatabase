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
	
	public String registEquipment(Equipment equipment) {
		String message = null;

		int result = equipmentRepository.save(equipment);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

	public String updateEquipment(Equipment equipment) {
		String message = null;

		int result = equipmentRepository.save(equipment);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}

		return message;
	}

	public String deleteEquipment(Equipment equipment) {
		String message = null;

		int result = equipmentRepository.deleteEquipmentByMsIdAndArmsId(
				equipment.getMsId().getValue(), equipment.getArmsId().getValue());
		if (result != 1) {
			message = "削除処理に失敗しました。";
		} else {
			message = "削除処理に成功しました。";
		}

		return message;
	}

	public int deleteEquipmentByMobileSuit(MobileSuit mobileSuit) {
		return equipmentRepository.deleteEquipmentByMsid(mobileSuit.getMsId().getValue());
	}

	public int deleteEquipmentByArms(Arms arms) {
		return equipmentRepository.deleteEquipmentByArmsId(arms.getArmsId().getValue());
	}

	
}
