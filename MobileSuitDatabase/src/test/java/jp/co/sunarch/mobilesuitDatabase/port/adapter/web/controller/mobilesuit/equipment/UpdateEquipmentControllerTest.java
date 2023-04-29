package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment.UpdateEquipmentUseCase;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.UpdateEquipmentForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;

@WebMvcTest(UpdateEquipmentController.class)
class UpdateEquipmentControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	EquipmentQuery equipmentQuery;

	@MockBean
	UpdateEquipmentUseCase updateEquipmentUseCase;

	@Test
	void 装備更新画面に遷移すること() throws Exception {
		EquipmentModel model = EquipmentModel.builder()
				.msId("ms1")
				.msName("テストモビルスーツ1")
				.armsId("arms1")
				.armsName("テスト－ライフル1")
				.numberEquipment("3")
				.detail("テスト更新")
				.build();

		UpdateEquipmentForm form = UpdateEquipmentForm.ModelToForm(model);

		when(equipmentQuery.getEquipmentByMsIdAndArmsId(any(), any())).thenReturn(model);

		mvc.perform(get("/MSDB/MobileSuits/Equipments/ms1/arms1/edit"))
		.andExpect(status().isOk())
		.andExpectAll(view().name("/MSDB/MobileSuits/Equipments/msId/armsId/edit/EquipmentEdit"))
		.andExpect(model().attribute("equipmentEditForm", form));

		verify(equipmentQuery).getEquipmentByMsIdAndArmsId("ms1", "arms1");
	}

	@Test
	void 装備データを更新して一覧画面に遷移すること() throws Exception {
		List<EquipmentModel> list = Arrays.asList(
				EquipmentModel.builder()
				.msId("ms1")
				.msName("テストモビルスーツ1")
				.armsId("arms1")
				.armsName("テストビールライフル1")
				.numberEquipment("1")
				.detail("テスト詳細1")
				.build(),
				EquipmentModel.builder()
				.msId("ms2")
				.msName("テストモビルスーツ2")
				.armsId("arms2")
				.armsName("テストビームライフル2")
				.numberEquipment("2")
				.detail("テスト詳細2")
				.build()
				);

		doNothing().when(updateEquipmentUseCase).execute(any());
		when(equipmentQuery.getEquipmentList()).thenReturn(list);

		mvc.perform(post("/MSDB/MobileSuits/Equipments/ms2/arms2/edit-update")
				.param("msId", "ms2")
				.param("msName", "テストモビルスーツ2")
				.param("armsId", "arms2")
				.param("armsName", "テストビームライフル2")
				.param("numberEquipment", "2")
				.param("detail", "テスト詳細2"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/EquipmentList"))
		.andExpect(model().attribute("equipments", list));
	}

}
