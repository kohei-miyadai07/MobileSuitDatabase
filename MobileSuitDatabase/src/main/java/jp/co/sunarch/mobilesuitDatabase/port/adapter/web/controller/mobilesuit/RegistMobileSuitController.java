package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.RegistMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.RegistMobileSuitUseCase;
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
				.headHeight(new BigDecimal(registMobileSuitForm.getHeadHeight()))
				.overallHeight(new BigDecimal(registMobileSuitForm.getOverallHeight()))
				.weight(new BigDecimal(registMobileSuitForm.getWeight()))
				.totalWeight(new BigDecimal(registMobileSuitForm.getTotalWeight()))
				.powerSource(registMobileSuitForm.getPowerSource())
				.material(registMobileSuitForm.getMaterial())
				.effectiveSensorRadius(Long.parseLong(registMobileSuitForm.getEffectiveSensorRadius()))
				.generatorOutput(Long.parseLong(registMobileSuitForm.getGeneratorOutput()))
				.totalThrustersOutput(Long.parseLong(registMobileSuitForm.getTotalThrustersOutput()))
				.msOverview(registMobileSuitForm.getMsOverview())
				.action(registMobileSuitForm.getAction())
				.msMultipartFile(registMobileSuitForm.getMsMultipartFile())
				.build();

		registMobileSuitUseCase.execute(command);

		List<MobileSuitModel> msModelList = mobileSuitQuery.getMobileSuitList();
		model.addAttribute("mobilesuits", msModelList);

		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
