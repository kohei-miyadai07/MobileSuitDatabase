package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.RegistEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistEquipmentUseCase {

	private final EquipmentRecodeService equipmentRecodeService;

	public String exeute(RegistEquipmentCommand command) {
		Equipment equipment = Equipment.create(
				command.getMsId(),
				command.getArmsId(),
				command.getNumberEquipment(), 
				command.getDetail());

		return equipmentRecodeService.registEquipment(equipment);
	}

}
