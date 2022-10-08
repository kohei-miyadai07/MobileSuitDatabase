package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.RegistEquipmentForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistEquipmentController {

	private final MobileSuitQuery mobileSuitQuery;
	private final ArmsQuery armsQuery;

	@GetMapping("/MSDB/MobileSuits/Equipments/-/new")
	public String registEquipment(Model model) {
		List<MobileSuitModel> msList = mobileSuitQuery.getMobileSuitList();
		List<ArmsModel> armsList = armsQuery.getArmsList();
		
		model.addAttribute("equipmentRegistForm", new RegistEquipmentForm(msList, armsList));

		return "/MSDB/MobileSuits/Equipments/-/new/EquipmentRegister";
	}

}
