package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.RegistArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.RegistArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms.RegistArmsForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistArmsController {

	private final ArmsQuery armsQuery;
	private final RegistArmsUseCase registArmsUseCase;

	@GetMapping("/MSDB/Arms/-/new")
	public String registArms(Model model) {
		model.addAttribute("armsRegistForm", new RegistArmsForm());
		return "/MSDB/Arms/-/new/ArmsRegister";
	}

	@PostMapping("/MSDB/Arms/-/new")
	public String registArms(@ModelAttribute RegistArmsForm registArmsForm, Model model) {
		RegistArmsCommand command = RegistArmsCommand.builder()
				.armsName(registArmsForm.getArmsName())
				.detail(CommonItemSettings.convertToString(registArmsForm.getDetail()))
				.build();

		registArmsUseCase.execute(command);

		List<ArmsModel> armsModelList = armsQuery.getArmsList();
		model.addAttribute("armsList", armsModelList);
		model.addAttribute("message", "武器を登録しました。");

		return "/MSDB/Arms/ArmsList";
	}
}
