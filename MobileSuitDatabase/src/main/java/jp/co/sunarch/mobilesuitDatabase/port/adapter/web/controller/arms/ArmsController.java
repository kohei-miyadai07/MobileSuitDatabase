package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms.ArmsSearchForm;
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

	@GetMapping("/MSDB/Arms/-/search")
	public String searchArms(Model model) {
		model.addAttribute("armsSearchForm", new ArmsSearchForm());
		return "/MSDB/Arms/-/search/ArmsSearch";
	}

	@PostMapping("/MSDB/Arms/-/search")
	public String searchArms(@ModelAttribute ArmsSearchForm armsSearchForm, Model model) {
		Criteria criteria = ArmsQuery.Criteria.builder()
				.armsName(CommonItemSettings.convertToString(armsSearchForm.getArmsName()))
				.build();

		List<ArmsModel> armsModelList = armsQuery.searchArms(criteria);
		model.addAttribute("armsList", armsModelList);

		return "/MSDB/Arms/ArmsList";
	}
	
}
