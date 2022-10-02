package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EquipmentController {

	private final EquipmentQuery equipmentQuery;

	@GetMapping("/MSDB/MobileSuits/Equipments")
	public String getEquipments(Model model) {
		List<EquipmentModel> equipmentModelList = equipmentQuery.getEquipmentList();
		model.addAttribute("equipments", equipmentModelList);
		return "/MSDB/MobileSuits/Equipments/EquipmentList";
	}
}
