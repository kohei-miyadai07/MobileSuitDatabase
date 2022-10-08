package jp.co.sunarch.mobilesuitDatabase.application.usecase.arms;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.DeleteArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsRecodeService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteArmsUseCase {

	private final ArmsQueryService armsQueryService;
	private final ArmsRecodeService armsRecodeService;

	private final EquipmentQueryService equipmentQueryService;
	private final EquipmentRecodeService equipmentRecodeService;

	public String execute(DeleteArmsCommand command) {
		int resultEquipment = 0;
		int resultArms = 0;
		String message = null;

		// 削除機能のArmsを取得
		Arms arms = armsQueryService.getArmsById(command.getArmsId());

		// 削除対象のEquipmentを取得
		List<Equipment> equipmentList = equipmentQueryService.getEquipmentByArmsId(command.getArmsId());

		if (arms != null || equipmentList.size() != 0) {
			resultEquipment = equipmentRecodeService.deleteEquipmentByArms(arms);
			resultArms = armsRecodeService.deleteArms(arms);
		}

		if (resultEquipment == 0 || resultArms == 0) {
			message = "削除処理に失敗しました。";
		} else {
			message = "削除処理に成功しました。";
		}

		return message;
	}
}
