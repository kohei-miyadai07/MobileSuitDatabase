package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.UpdateEquipmentForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UpdateEquipmentController {

	private final EquipmentQuery equipmentQuery;

	@GetMapping("/MSDB/MobileSuits/Equipments/{msId}/{armsId}/edit")
	public String updateEquipment(@PathVariable String msId, @PathVariable String armsId, Model model) {
		EquipmentModel equipmentModel = equipmentQuery.getEquipmentByMsIdAndArmsId(msId, armsId);
		UpdateEquipmentForm updateEquipmentForm = UpdateEquipmentForm.ModelToForm(equipmentModel);
		model.addAttribute("equipmentEditForm", updateEquipmentForm);

		return "/MSDB/MobileSuits/Equipments/msId/armsId/edit/EquipmentEdit";
	}

}
