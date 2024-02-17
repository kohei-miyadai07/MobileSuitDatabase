package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.DeleteArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.DeleteArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteArmsController {

	private final ArmsQuery armsQuery;
	private final DeleteArmsUseCase deleteArmsUseCase;

	@PostMapping("/MSDB/Arms/{armsId}/edit-delete")
	public String deleteArms(@PathVariable String armsId, Model model) {
		DeleteArmsCommand command = DeleteArmsCommand.builder()
				.armsId(ArmsId.of(armsId))
				.build();

		deleteArmsUseCase.execute(command);

		List<ArmsModel> armsModelList = armsQuery.getArmsList();
		model.addAttribute("armsList", armsModelList);

		model.addAttribute("message", "武器を削除しました。");

		return "/MSDB/Arms/ArmsList";
	}
}
