package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.DeleteEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment.DeleteEquipmentUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteEquipmentController {

	private final DeleteEquipmentUseCase deleteEquipmentUseCase;

	@PostMapping("/MSDB/MobileSuits/Equipments/{msId}/{armsId}/edit-delete")
	public String deleteEquipment(@PathVariable String msId, @PathVariable String armsId, Model model) {
		DeleteEquipmentCommand command = DeleteEquipmentCommand.builder()
				.msId(MobileSuitId.of(msId))
				.armsId(ArmsId.of(armsId))
				.build();

		String message = deleteEquipmentUseCase.execute(command);

		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuits/Equipments");

		return "/MSDB/MobileSuits/Equipments/msId/armsId/edit/DeleteResult";
	}

}
