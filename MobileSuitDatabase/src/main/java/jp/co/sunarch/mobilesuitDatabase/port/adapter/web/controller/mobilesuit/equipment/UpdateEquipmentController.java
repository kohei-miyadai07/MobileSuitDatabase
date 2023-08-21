package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.UpdateEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment.UpdateEquipmentUseCase;
import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.UpdateEquipmentForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UpdateEquipmentController {

	private final EquipmentQuery equipmentQuery;
	private final UpdateEquipmentUseCase updateEquipmentUseCase;

	@GetMapping("/MSDB/MobileSuits/Equipments/{msId}/{armsId}/edit")
	public String updateEquipment(@PathVariable String msId, @PathVariable String armsId, Model model) {
		EquipmentModel equipmentModel = equipmentQuery.getEquipmentByMsIdAndArmsId(msId, armsId);
		UpdateEquipmentForm updateEquipmentForm = UpdateEquipmentForm.ModelToForm(equipmentModel);
		model.addAttribute("equipmentEditForm", updateEquipmentForm);

		return "/MSDB/MobileSuits/Equipments/msId/armsId/edit/EquipmentEdit";
	}

	@PostMapping("/MSDB/MobileSuits/Equipments/{msId}/{armsId}/edit-update")
	public String updateEquipment(@PathVariable String msId, @PathVariable String armsId,
			@ModelAttribute UpdateEquipmentForm updateEquipmentForm, Model model) {
		UpdateEquipmentCommand command = UpdateEquipmentCommand.builder()
				.msId(MobileSuitId.of(msId))
				.armsId(ArmsId.of(armsId))
				.numberEquipment(CommonItemSettings.convertToInteger(updateEquipmentForm.getNumberEquipment()))
				.detail(CommonItemSettings.convertToString(updateEquipmentForm.getDetail()))
				.build();

		updateEquipmentUseCase.execute(command);

		List<EquipmentModel> equipmentModelList = equipmentQuery.getEquipmentList();
		model.addAttribute("equipments", equipmentModelList);

		return "/MSDB/MobileSuits/Equipments/EquipmentList";
	}

}
