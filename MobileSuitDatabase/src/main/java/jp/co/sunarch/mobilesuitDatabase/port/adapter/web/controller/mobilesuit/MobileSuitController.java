package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
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
	public String searchMobileSuit(@ModelAttribute MobileSuitSearchForm msSearchModel, Model model) {
		Criteria criteria = MobileSuitQuery.Criteria.builder()
				.modelNumber(CommonItemSettings.convertToString(msSearchModel.getModelNumber()))
				.msName(CommonItemSettings.convertToString(msSearchModel.getMsName()))
				.headHeightFrom(CommonItemSettings.convertToBigDecimal(msSearchModel.getHeadHeightFrom()))
				.headHeightTo(CommonItemSettings.convertToBigDecimal(msSearchModel.getHeadHeightTo()))
				.overallHeightFrom(CommonItemSettings.convertToBigDecimal(msSearchModel.getOverallHeightFrom()))
				.overallHeightTo(CommonItemSettings.convertToBigDecimal(msSearchModel.getOverallHeightTo()))
				.weightFrom(CommonItemSettings.convertToBigDecimal(msSearchModel.getWeightFrom()))
				.weightTo(CommonItemSettings.convertToBigDecimal(msSearchModel.getWeightTo()))
				.totalWeightFrom(CommonItemSettings.convertToBigDecimal(msSearchModel.getTotalWeightFrom()))
				.totalWeightTo(CommonItemSettings.convertToBigDecimal(msSearchModel.getTotalWeightTo()))
				.powerSource(CommonItemSettings.convertToString(msSearchModel.getPowerSource()))
				.material(CommonItemSettings.convertToString(msSearchModel.getMaterial()))
				.effectiveSensorRadiusFrom(CommonItemSettings.convertToLong(msSearchModel.getEffectiveSensorRadiusFrom()))
				.effectiveSensorRadiusTo(CommonItemSettings.convertToLong(msSearchModel.getEffectiveSensorRadiusTo()))
				.generatorOutputFrom(CommonItemSettings.convertToLong(msSearchModel.getGeneratorOutputFrom()))
				.generatorOutputTo(CommonItemSettings.convertToLong(msSearchModel.getGeneratorOutputTo()))
				.totalThrustersOutputFrom(CommonItemSettings.convertToLong(msSearchModel.getTotalThrustersOutputFrom()))
				.totalThrustersOutputTo(CommonItemSettings.convertToLong(msSearchModel.getTotalThrustersOutputTo()))
				.build();

		List<MobileSuitModel> msModelList = mobileSuitQuery.searchMobileSuit(criteria);
		model.addAttribute("mobilesuits", msModelList);

		return "/MSDB/MobileSuits/MobileSuitList";
	}
}
