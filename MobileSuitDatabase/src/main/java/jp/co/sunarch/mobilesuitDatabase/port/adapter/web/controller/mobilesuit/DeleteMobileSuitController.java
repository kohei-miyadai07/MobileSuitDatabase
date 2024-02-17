package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.DeleteMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.DeleteMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteMobileSuitController {

	private final MobileSuitQuery mobileSuitQuery;
	private final DeleteMobileSuitUseCase deleteMobileSuitUseCase;

	@PostMapping("/MSDB/MobileSuits/{msId}/edit-delete")
	public String deleteMobileSuit(@PathVariable String msId, Model model) {
		DeleteMobileSuitCommand command = DeleteMobileSuitCommand.builder()
				.msId(MobileSuitId.of(msId))
				.build();

		deleteMobileSuitUseCase.execute(command);

		List<MobileSuitModel> msModelList = mobileSuitQuery.getMobileSuitList();
		model.addAttribute("mobilesuits", msModelList);
		model.addAttribute("message", "モビルスーツ情報を削除しました。");

		return "/MSDB/MobileSuits/MobileSuitList";
	}
}
