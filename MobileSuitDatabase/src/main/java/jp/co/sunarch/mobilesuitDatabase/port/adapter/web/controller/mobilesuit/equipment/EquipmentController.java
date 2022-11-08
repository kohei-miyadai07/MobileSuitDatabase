package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.EquipmentSearchForm;
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

	@GetMapping("/MSDB/MobileSuits/Equipments/-/search")
	public String searchEquipment(Model model) {
		model.addAttribute("equipmentSearchForm", new EquipmentSearchForm());
		return "/MSDB/MobileSuits/Equipments/-/search/EquipmentSearch";
	}

	@PostMapping("/MSDB/MobileSuits/Equipments/-/search")
	public String searchEquipment(@ModelAttribute EquipmentSearchForm equipmentSearchForm, Model model) {
		Criteria criteria = EquipmentQuery.Criteria.builder()
				.msName(equipmentSearchForm.getMsName())
				.armsName(equipmentSearchForm.getArmsName())
				.build();

		List<EquipmentModel> equipmentModelList = equipmentQuery.searchEquipment(criteria);
		model.addAttribute("equipments", equipmentModelList);

		return "/MSDB/MobileSuits/Equipments/EquipmentList";
	}
}
