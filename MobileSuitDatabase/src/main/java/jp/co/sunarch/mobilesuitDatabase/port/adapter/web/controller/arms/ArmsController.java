package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArmsController {

	private final ArmsQuery armsQuery;

	@GetMapping("/MSDB/Arms")
	public String getArmsList(Model model) {
		List<ArmsModel> armsModelList = armsQuery.getArmsList();
		model.addAttribute("armsList", armsModelList);
		return "/MSDB/Arms/ArmsList";
	}
	
}
