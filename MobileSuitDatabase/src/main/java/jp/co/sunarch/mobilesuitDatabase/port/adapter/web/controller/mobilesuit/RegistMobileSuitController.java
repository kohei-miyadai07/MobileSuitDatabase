package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.RegistMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.RegistMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.RegistMobileSuitForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistMobileSuitController {

	private final MobileSuitQuery mobileSuitQuery;
	private final RegistMobileSuitUseCase registMobileSuitUseCase;
	
	@GetMapping("/MSDB/MobileSuits/-/new")
	public String registMobileSuit(Model model) {
		model.addAttribute("msRegistForm", new RegistMobileSuitForm());
		return "/MSDB/MobileSuits/-/new/MobileSuitRegister";
	}

	@PostMapping("/MSDB/MobileSuits/-/new")
	public String registMobileSuit(@ModelAttribute RegistMobileSuitForm registMobileSuitForm, Model model) {
		RegistMobileSuitCommand command = RegistMobileSuitCommand.builder()
				.modelNumber(registMobileSuitForm.getModelNumber())
				.msName(registMobileSuitForm.getMsName())
				.headHeight(CommonItemSettings.convertToBigDecimal(registMobileSuitForm.getHeadHeight()))
				.overallHeight(CommonItemSettings.convertToBigDecimal(registMobileSuitForm.getOverallHeight()))
				.weight(CommonItemSettings.convertToBigDecimal(registMobileSuitForm.getWeight()))
				.totalWeight(CommonItemSettings.convertToBigDecimal(registMobileSuitForm.getTotalWeight()))
				.powerSource(CommonItemSettings.convertToString(registMobileSuitForm.getPowerSource()))
				.material(CommonItemSettings.convertToString(registMobileSuitForm.getMaterial()))
				.effectiveSensorRadius(CommonItemSettings.convertToLong(registMobileSuitForm.getEffectiveSensorRadius()))
				.generatorOutput(CommonItemSettings.convertToLong(registMobileSuitForm.getGeneratorOutput()))
				.totalThrustersOutput(CommonItemSettings.convertToLong(registMobileSuitForm.getTotalThrustersOutput()))
				.msOverview(CommonItemSettings.convertToString(registMobileSuitForm.getMsOverview()))
				.action(CommonItemSettings.convertToString(registMobileSuitForm.getAction()))
				.msMultipartFile(registMobileSuitForm.getMsMultipartFile())
				.build();

		registMobileSuitUseCase.execute(command);

		List<MobileSuitModel> msModelList = mobileSuitQuery.getMobileSuitList();
		model.addAttribute("mobilesuits", msModelList);

		model.addAttribute("message", "モビルスーツ情報を登録しました。");

		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
