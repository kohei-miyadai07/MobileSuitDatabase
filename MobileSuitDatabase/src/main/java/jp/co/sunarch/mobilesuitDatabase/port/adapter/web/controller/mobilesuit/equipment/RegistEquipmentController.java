package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.RegistEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment.RegistEquipmentUseCase;
import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.RegistEquipmentForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistEquipmentController {

	private final EquipmentQuery equipmentQuery;
	private final MobileSuitQuery mobileSuitQuery;
	private final ArmsQuery armsQuery;

	private final RegistEquipmentUseCase registEquipmentUseCase;

	@GetMapping("/MSDB/MobileSuits/Equipments/-/new")
	public String registEquipment(Model model) {
		List<MobileSuitModel> msList = mobileSuitQuery.getMobileSuitList();
		List<ArmsModel> armsList = armsQuery.getArmsList();
		
		model.addAttribute("equipmentRegistForm", new RegistEquipmentForm(msList, armsList));

		return "/MSDB/MobileSuits/Equipments/-/new/EquipmentRegister";
	}

	@PostMapping("/MSDB/MobileSuits/Equipments/-/new")
	public String registEquipment(@ModelAttribute RegistEquipmentForm registEquipmentForm, Model model) {
		RegistEquipmentCommand command = RegistEquipmentCommand.builder()
				.msId(MobileSuitId.of(registEquipmentForm.getMsId()))
				.armsId(ArmsId.of(registEquipmentForm.getArmsId()))
				.numberEquipment(CommonItemSettings.convertToInteger(registEquipmentForm.getNumberEquipment()))
				.detail(CommonItemSettings.convertToString(registEquipmentForm.getDetail()))
				.build();

		registEquipmentUseCase.exeute(command);

		List<EquipmentModel> equipmentModelList = equipmentQuery.getEquipmentList();
		model.addAttribute("equipments", equipmentModelList);

		return "/MSDB/MobileSuits/Equipments/EquipmentList";	
	}

}
