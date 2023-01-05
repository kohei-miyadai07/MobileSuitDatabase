package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.UpdateEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateEquipmentUseCase {

	private final EquipmentQueryService equipmentQueryService;
	private final EquipmentRecodeService equipmentRecodeService;

	public void execute(UpdateEquipmentCommand command) {
		// 更新対象のEquipmentを取得
		Equipment equipment = equipmentQueryService.getEquipmentByMobileSuitIdAndArmsId(command.getMsId(), command.getArmsId());

		// 更新対象のEquipmentに更新項目をセット
		equipment.setNumberEquipment(command.getNumberEquipment());
		equipment.setDetail(command.getDetail());

		equipmentRecodeService.updateEquipment(equipment);
	}
}
