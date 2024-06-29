package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.common.utils.FileOperations;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.MobileSuitSearchForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MobileSuitController {
	
	private final MobileSuitQuery mobileSuitQuery;
	
	@GetMapping("/MSDB/MobileSuits")
	public String getMobileSuits(Model model) {
		List<MobileSuitModel> msModelList = mobileSuitQuery.getMobileSuitList();
		model.addAttribute("mobilesuits", msModelList);
		return "/MSDB/MobileSuits/MobileSuitList";
	}

	@GetMapping("/MSDB/MobileSuits/{msId}")
	public String getMobileSuitDetail(@PathVariable String msId, Model model) {
		MobileSuitDetailModel msDetailModel = mobileSuitQuery.getMobileSuitDetail(msId);

		String base64Data = "";
		try {
			base64Data = FileOperations.getImage(msDetailModel.getMsUrl());
		} catch (IOException e) {
			base64Data = "";
		}

		model.addAttribute("base64Data", "data:image/png;base64,"+ base64Data);
		model.addAttribute("mobilesuitDetail", msDetailModel);
		return "/MSDB/MobileSuits/msId/MobileSuitDetail";
	}

	@GetMapping("/MSDB/MobileSuits/-/search")
	public String searchMobileSuit(Model model) {
		model.addAttribute("msSearchForm", new MobileSuitSearchForm());
		return "/MSDB/MobileSuits/-/search/MobileSuitSearch";
	}

	@PostMapping("/MSDB/MobileSuits/-/search")
	public String searchMobileSuit(@ModelAttribute MobileSuitSearchForm msSearchForm, Model model) {
		Criteria criteria = MobileSuitQuery.Criteria.builder()
				.modelNumber(msSearchForm.getModelNumber())
				.msName(msSearchForm.getMsName())
				.headHeightFrom(msSearchForm.getHeadHeightFrom())
				.headHeightTo(msSearchForm.getHeadHeightTo())
				.overallHeightFrom(msSearchForm.getOverallHeightFrom())
				.overallHeightTo(msSearchForm.getOverallHeightTo())
				.weightFrom(msSearchForm.getWeightFrom())
				.weightTo(msSearchForm.getWeightTo())
				.totalWeightFrom(msSearchForm.getTotalWeightFrom())
				.totalWeightTo(msSearchForm.getTotalWeightTo())
				.powerSource(msSearchForm.getPowerSource())
				.material(msSearchForm.getMaterial())
				.effectiveSensorRadiusFrom(msSearchForm.getEffectiveSensorRadiusFrom())
				.effectiveSensorRadiusTo(msSearchForm.getEffectiveSensorRadiusTo())
				.generatorOutputFrom(msSearchForm.getGeneratorOutputFrom())
				.generatorOutputTo(msSearchForm.getGeneratorOutputTo())
				.totalThrustersOutputFrom(msSearchForm.getTotalThrustersOutputFrom())
				.totalThrustersOutputTo(msSearchForm.getTotalThrustersOutputTo())
				.build();

		List<MobileSuitModel> msModelList = mobileSuitQuery.searchMobileSuit(criteria);
		model.addAttribute("mobilesuits", msModelList);

		return "/MSDB/MobileSuits/MobileSuitList";
	}
}
