package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.EquipmentSearchForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;

@WebMvcTest(EquipmentController.class)
class EquipmentControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	EquipmentQuery equipmentQuery;

	@Test
	void 装備一覧画面に遷移すること() throws Exception {
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

		when(equipmentQuery.getEquipmentList()).thenReturn(list);

		mvc.perform(get("/MSDB/MobileSuits/Equipments"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/EquipmentList"))
		.andExpect(model().attribute("equipments", list));

		verify(equipmentQuery).getEquipmentList();
	}

	@Test
	void 装備検索画面に遷移すること() throws Exception {
		EquipmentSearchForm form = new EquipmentSearchForm();

		mvc.perform(get("/MSDB/MobileSuits/Equipments/-/search"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/-/search/EquipmentSearch"))
		.andExpect(model().attribute("equipmentSearchForm", form));
	}

	@Test
	void 装備データを検索して一覧画面に遷移すること() throws Exception {
		List<EquipmentModel> list = Arrays.asList(
				EquipmentModel.builder()
				.msId("ms1")
				.msName("テストモビルスーツ1")
				.armsId("arms1")
				.armsName("テストビールライフル1")
				.numberEquipment("1")
				.detail("テスト詳細1")
				.build());

		when(equipmentQuery.searchEquipment(any())).thenReturn(list);

		mvc.perform(post("/MSDB/MobileSuits/Equipments/-/search"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/EquipmentList"))
		.andExpect(model().attribute("equipments", list));
	}

}
