package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.DeleteMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.DeleteMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteMobileSuitController {

	private final DeleteMobileSuitUseCase deleteMobileSuitUseCase;

	@PostMapping("/MSDB/MobileSuits/{msId}/edit-delete")
	public String deleteMobileSuit(@PathVariable String msId, Model model) {
		DeleteMobileSuitCommand command = DeleteMobileSuitCommand.builder()
				.msId(MobileSuitId.of(msId))
				.build();

		String message = deleteMobileSuitUseCase.execute(command);

		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuits");

		return "/MSDB/MobileSuits/msId/edit/DeleteResult";
	}
}
