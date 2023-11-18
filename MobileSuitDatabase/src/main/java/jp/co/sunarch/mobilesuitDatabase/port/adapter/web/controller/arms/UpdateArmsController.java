package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.UpdateArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.UpdateArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms.UpdateArmsForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UpdateArmsController {

	private final ArmsQuery armsQuery;
	private final UpdateArmsUseCase updateArmsUseCase;

	@GetMapping("/MSDB/Arms/{armsId}/edit")
	public String updateArms(@PathVariable String armsId, Model model) {
		ArmsModel armsModel = armsQuery.getArmsById(armsId);
		UpdateArmsForm updateArmsForm = UpdateArmsForm.ModelToForm(armsModel);
		model.addAttribute("armsEditForm", updateArmsForm);

		return "/MSDB/Arms/armsId/edit/ArmsEdit";
	}

	@PostMapping("/MSDB/Arms/{armsId}/edit-update")
	public String updateArms(@PathVariable String armsId,
			@ModelAttribute UpdateArmsForm updateArmsForm, Model model) {
		UpdateArmsCommand command = UpdateArmsCommand.builder()
				.armsId(ArmsId.of(updateArmsForm.getArmsId()))
				.armsName(updateArmsForm.getArmsName())
				.detail(CommonItemSettings.convertToString(updateArmsForm.getDetail()))
				.build();

		updateArmsUseCase.execute(command);

		List<ArmsModel> armsModelList = armsQuery.getArmsList();
		model.addAttribute("armsList", armsModelList);

		model.addAttribute("message", "武器を編集しました。");

		return "/MSDB/Arms/ArmsList";
	}
}
