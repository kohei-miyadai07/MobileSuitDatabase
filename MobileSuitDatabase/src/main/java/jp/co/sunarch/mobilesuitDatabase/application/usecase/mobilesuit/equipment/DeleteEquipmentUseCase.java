package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.DeleteEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteEquipmentUseCase {

	private final EquipmentQueryService equipmentQueryService;
	private final EquipmentRecodeService equipmentRecodeService;

	public void execute(DeleteEquipmentCommand command) {
		// 削除対象のEquipmentを取得
		Equipment equipment = equipmentQueryService.getEquipmentByMobileSuitIdAndArmsId(command.getMsId(), command.getArmsId());

		equipmentRecodeService.deleteEquipment(equipment);
	}

}
