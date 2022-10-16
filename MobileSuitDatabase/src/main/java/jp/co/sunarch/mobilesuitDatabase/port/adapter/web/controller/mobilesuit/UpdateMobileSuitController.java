package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.UpdateMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.UpdateMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.UpdateMobileSuitForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UpdateMobileSuitController {
	
	private final MobileSuitQuery mobileSuitQuery;
	private final UpdateMobileSuitUseCase updateMobileSuitUseCase;

	@GetMapping("/MSDB/MobileSuits/{msId}/edit")
	public String updateMobileSuit(@PathVariable String msId, Model model) {
		MobileSuitModel msModel = mobileSuitQuery.getMobileSuitById(msId);
		UpdateMobileSuitForm updateMobileSuitForm = UpdateMobileSuitForm.ModelToForm(msModel);
		model.addAttribute("msEditForm", updateMobileSuitForm);

		return "/MSDB/MobileSuits/msId/edit/MobileSuitEdit";
	}

	@PostMapping("/MSDB/MobileSuits/{msId}/edit-update")
	public String updateMobileSuit(@PathVariable String msId,
			@ModelAttribute UpdateMobileSuitForm updateMobileSuitForm, Model model) {
		UpdateMobileSuitCommand command = UpdateMobileSuitCommand.builder()
				.msId(MobileSuitId.of(updateMobileSuitForm.getMsId()))
				.modelNumber(updateMobileSuitForm.getModelNumber())
				.msName(updateMobileSuitForm.getMsName())
				.msUrl(updateMobileSuitForm.getMsUrl())
				.headHeight(new BigDecimal(updateMobileSuitForm.getHeadHeight()))
				.overallHeight(new BigDecimal(updateMobileSuitForm.getOverallHeight()))
				.weight(new BigDecimal(updateMobileSuitForm.getWeight()))
				.totalWeight(new BigDecimal(updateMobileSuitForm.getTotalWeight()))
				.powerSource(updateMobileSuitForm.getPowerSource())
				.material(updateMobileSuitForm.getMaterial())
				.effectiveSensorRadius(Long.parseLong(updateMobileSuitForm.getEffectiveSensorRadius()))
				.generatorOutput(Long.parseLong(updateMobileSuitForm.getGeneratorOutput()))
				.totalThrustersOutput(Long.parseLong(updateMobileSuitForm.getTotalThrustersOutput()))
				.msOverview(updateMobileSuitForm.getMsOverview())
				.action(updateMobileSuitForm.getAction())
				.msMultipartFile(updateMobileSuitForm.getMsMultipartFile())
				.build();

		String message = updateMobileSuitUseCase.execute(command);

		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuits");

		return "/MSDB/MobileSuits/msId/edit/EditResult";
	}

}
