package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.DeleteMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitRecodeService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteMobileSuitUseCase {

	private final MobileSuitQueryService mobileSuitQueryService;
	private final MobileSuitRecodeService mobileSuitRecodeService;

	private final EquipmentQueryService equipmentQueryService;
	private final EquipmentRecodeService equipmentRecodeService;

	public void execute(DeleteMobileSuitCommand command) {
		// 削除対象のMobileSuitを取得
		MobileSuit mobileSuit = mobileSuitQueryService.getMobileSuitById(command.getMsId());

		// 削除対象のEquipmentを取得
		List<Equipment> equipmentList = equipmentQueryService.getEquipmentByMobileSuitId(command.getMsId());

		if (mobileSuit != null || equipmentList.size() != 0) {
			mobileSuitRecodeService.deleteImageFile(mobileSuit.getMsUrl());
			equipmentRecodeService.deleteEquipmentByMobileSuit(mobileSuit);
			mobileSuitRecodeService.deleteMobileSuit(mobileSuit);
		}
	}
}
