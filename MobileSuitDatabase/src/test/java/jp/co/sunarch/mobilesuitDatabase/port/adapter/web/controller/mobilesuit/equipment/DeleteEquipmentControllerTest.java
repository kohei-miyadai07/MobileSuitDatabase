package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment.DeleteEquipmentCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment.DeleteEquipmentUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;

@WebMvcTest(DeleteEquipmentController.class)
class DeleteEquipmentControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	EquipmentQuery equipmentQuery;

	@MockBean
	DeleteEquipmentUseCase deleteEquipmentUseCase;

	@Test
	void 装備データを削除して一覧画面に遷移すること() throws Exception {
		DeleteEquipmentCommand command = DeleteEquipmentCommand.builder()
				.msId(MobileSuitId.of("ms2"))
				.armsId(ArmsId.of("arms2"))
				.build();

		List<EquipmentModel> list = Arrays.asList(
				EquipmentModel.builder()
				.msId("ms1")
				.msName("テストモビルスーツ1")
				.armsId("arms1")
				.armsName("テストビールライフル1")
				.numberEquipment("1")
				.detail("テスト詳細1")
				.build());

		doNothing().when(deleteEquipmentUseCase).execute(any());
		when(equipmentQuery.getEquipmentList()).thenReturn(list);

		mvc.perform(post("/MSDB/MobileSuits/Equipments/ms2/arms2/edit-delete"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/EquipmentList"))
		.andExpect(model().attribute("equipments", list));

		verify(deleteEquipmentUseCase).execute(command);
		verify(equipmentQuery).getEquipmentList();
	}

}
