package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery.Criteria;
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
				.modelNumber(msSearchModel.getModelNumber())
				.msName(msSearchModel.getMsName())
				.headHeightFrom(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getHeadHeightFrom())))
				.headHeightTo(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getHeadHeightTo())))
				.overallHeightFrom(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getOverallHeightFrom())))
				.overallHeightTo(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getOverallHeightTo())))
				.weightFrom(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getWeightFrom())))
				.weightTo(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getWeightTo())))
				.totalWeightFrom(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getTotalWeightFrom())))
				.totalWeightTo(new BigDecimal(CommonItemSettings.contentsRequired(msSearchModel.getTotalWeightTo())))
				.powerSource(msSearchModel.getPowerSource())
				.material(msSearchModel.getMaterial())
				.effectiveSensorRadiusFrom(Long.parseLong(CommonItemSettings.contentsRequired(msSearchModel.getEffectiveSensorRadiusFrom())))
				.effectiveSensorRadiusTo(Long.parseLong(CommonItemSettings.contentsRequired(msSearchModel.getEffectiveSensorRadiusTo())))
				.generatorOutputFrom(Long.parseLong(CommonItemSettings.contentsRequired(msSearchModel.getGeneratorOutputFrom())))
				.generatorOutputTo(Long.parseLong(CommonItemSettings.contentsRequired(msSearchModel.getGeneratorOutputTo())))
				.totalThrustersOutputFrom(Long.parseLong(CommonItemSettings.contentsRequired(msSearchModel.getTotalThrustersOutputFrom())))
				.totalThrustersOutputTo(Long.parseLong(CommonItemSettings.contentsRequired(msSearchModel.getTotalThrustersOutputTo())))
				.build();

		List<MobileSuitModel> msModelList = mobileSuitQuery.searchMobileSuit(criteria);
		model.addAttribute("mobilesuits", msModelList);

		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
