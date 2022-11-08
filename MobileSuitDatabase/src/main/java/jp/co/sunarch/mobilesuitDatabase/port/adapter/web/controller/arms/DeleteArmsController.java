package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.DeleteArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.DeleteArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteArmsController {

	private final DeleteArmsUseCase deleteArmsUseCase;

	@PostMapping("/MSDB/Arms/{armsId}/edit-delete")
	public String deleteArms(@PathVariable String armsId, Model model) {
		DeleteArmsCommand command = DeleteArmsCommand.builder()
				.armsId(ArmsId.of(armsId))
				.build();

		String message = deleteArmsUseCase.execute(command);

		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/Arms");

		return "/MSDB/Arms/armsId/edit/DeleteResult";
	}

}
