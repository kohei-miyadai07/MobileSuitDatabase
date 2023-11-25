package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.UpdateMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.UpdateMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
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
				.headHeight(CommonItemSettings.convertToBigDecimal(updateMobileSuitForm.getHeadHeight()))
				.overallHeight(CommonItemSettings.convertToBigDecimal(updateMobileSuitForm.getOverallHeight()))
				.weight(CommonItemSettings.convertToBigDecimal(updateMobileSuitForm.getWeight()))
				.totalWeight(CommonItemSettings.convertToBigDecimal(updateMobileSuitForm.getTotalWeight()))
				.powerSource(CommonItemSettings.convertToString(updateMobileSuitForm.getPowerSource()))
				.material(CommonItemSettings.convertToString(updateMobileSuitForm.getMaterial()))
				.effectiveSensorRadius(CommonItemSettings.convertToLong(updateMobileSuitForm.getEffectiveSensorRadius()))
				.generatorOutput(CommonItemSettings.convertToLong(updateMobileSuitForm.getGeneratorOutput()))
				.totalThrustersOutput(CommonItemSettings.convertToLong(updateMobileSuitForm.getTotalThrustersOutput()))
				.msOverview(CommonItemSettings.convertToString(updateMobileSuitForm.getMsOverview()))
				.action(CommonItemSettings.convertToString(updateMobileSuitForm.getAction()))
				.msMultipartFile(updateMobileSuitForm.getMsMultipartFile())
				.build();

		updateMobileSuitUseCase.execute(command);

		List<MobileSuitModel> msModelList = mobileSuitQuery.getMobileSuitList();
		model.addAttribute("mobilesuits", msModelList);

		model.addAttribute("message", "モビルスーツ情報を編集しました。");

		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
