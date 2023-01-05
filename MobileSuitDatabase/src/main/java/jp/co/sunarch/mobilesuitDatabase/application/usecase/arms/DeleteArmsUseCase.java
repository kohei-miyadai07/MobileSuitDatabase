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

	public void execute(DeleteArmsCommand command) {
		// 削除機能のArmsを取得
		Arms arms = armsQueryService.getArmsById(command.getArmsId());

		// 削除対象のEquipmentを取得
		List<Equipment> equipmentList = equipmentQueryService.getEquipmentByArmsId(command.getArmsId());

		if (arms != null || equipmentList.size() != 0) {
			equipmentRecodeService.deleteEquipmentByArms(arms);
			armsRecodeService.deleteArms(arms);
		}
	}
}
