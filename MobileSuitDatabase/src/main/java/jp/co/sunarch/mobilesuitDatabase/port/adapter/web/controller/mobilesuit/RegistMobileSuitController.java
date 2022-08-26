package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.RegistMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.RegistMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.from.mobilesuit.RegistMobileSuitForm;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistMobileSuitController {
	
	private final RegistMobileSuitUseCase registMobileSuitUseCase;
	
	@GetMapping("/MSDB/MobileSuits/MobileSuit/-/new")
	public String RegistMobileSuit(Model model) {
		model.addAttribute("msRegistForm", new RegistMobileSuitForm());
		return "/MSDB/MobileSuits/-/new/MobileSuitRegister";
	}

	@PostMapping("/MSDB/MobileSuits/MobileSuit/-/new")
	public String RegistMobileSuit(@ModelAttribute RegistMobileSuitForm registMobileSuitForm, Model model) {
		
		RegistMobileSuitCommand command = RegistMobileSuitCommand.builder()
				.modelNumber(registMobileSuitForm.getModelNumber())
				.msName(registMobileSuitForm.getMsName())
				.msUrl(registMobileSuitForm.getMsUrl())
				.headHeight(registMobileSuitForm.getHeadHeight())
				.overallHeight(registMobileSuitForm.getOverallHeight())
				.weight(registMobileSuitForm.getWeight())
				.totalWeight(registMobileSuitForm.getTotalWeight())
				.powerSource(registMobileSuitForm.getPowerSource())
				.material(registMobileSuitForm.getMaterial())
				.effectiveSensorRadius(registMobileSuitForm.getEffectiveSensorRadius())
				.generatorOutput(registMobileSuitForm.getGeneratorOutput())
				.totalThrustersOutput(registMobileSuitForm.getTotalThrustersOutput())
				.msOverview(registMobileSuitForm.getMsOverview())
				.action(registMobileSuitForm.getAction())
				.build();

		String message = registMobileSuitUseCase.execute(command);

		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuits");

		return "/MSDB/MobileSuits/-/new/RegisterResult";
	}

}
