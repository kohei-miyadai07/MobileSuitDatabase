package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment;

import java.time.Instant;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.UpdateEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment.EquipmentRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;
import jp.co.sunarch.mobilesuitDatabase.util.exception.MobileSuitDataBaseConflictException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateEquipmentUseCase {

	private final EquipmentQueryService equipmentQueryService;
	private final EquipmentRecodeService equipmentRecodeService;

	public void execute(UpdateEquipmentCommand command) {
		// 更新対象のEquipmentを取得
		Equipment before = equipmentQueryService.getEquipmentByMobileSuitIdAndArmsId(command.getMsId(), command.getArmsId());

		// 更新様のEquipmentを作成
		Equipment equipment = new Equipment();
		equipment.setMsId(command.getMsId());
		equipment.setArmsId(command.getArmsId());
		equipment.setNumberEquipment(command.getNumberEquipment());
		equipment.setDetail(command.getDetail());
		equipment.setInsertDate(before.getInsertDate());
		equipment.setUpdateDate(Instant.now());
		equipment.setVersion(command.getVersion());

		if (before.getVersion() != equipment.getVersion()) {
			throw new MobileSuitDataBaseConflictException("すでに別のユーザーによって更新された可能性があります。最新情報をご確認ください。");
		}

		equipmentRecodeService.updateEquipment(equipment);
	}
}
