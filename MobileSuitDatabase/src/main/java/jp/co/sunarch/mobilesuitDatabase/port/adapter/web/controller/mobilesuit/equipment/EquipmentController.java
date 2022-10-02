package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {

	@GetMapping("/MSDB/MobileSuits/Equipments")
	public String getEquipments(Model model) {
		return "";
	}
}
