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

import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.equipment.RegistEquipmentUseCase;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.equipment.EquipmentQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment.RegistEquipmentForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;

@WebMvcTest(RegistEquipmentController.class)
class RegistEquipmentControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	EquipmentQuery equipmentQuery;

	@MockBean
	MobileSuitQuery mobileSuitQuery;

	@MockBean
	ArmsQuery armsQuery;

	@MockBean
	RegistEquipmentUseCase registEquipmentUseCase;

	@Test
	void 装備登録画面に遷移すること() throws Exception {
		List<MobileSuitModel> msList = Arrays.asList(
				MobileSuitModel.builder()
				.msId("ms1")
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.msUrl("/ms/test")
				.headHeight("18.00")
				.overallHeight("19.00")
				.weight("85.55")
				.totalWeight("87.55")
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius("100")
				.generatorOutput("200")
				.totalThrustersOutput("300")
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate("2023/04/18 10:20:30")
				.updateDate("2023/04/18 10:20:30")
				.version("1")
				.build()
				);

		List<ArmsModel> armsList = Arrays.asList(
				ArmsModel.builder()
				.armsId("arms_test1")
				.armsName("テストビームサーベル1")
				.detail("テスト1")
				.build(),
				ArmsModel.builder()
				.armsId("arms_test2")
				.armsName("テストビームサーベル2")
				.detail("テスト2")
				.build());

		when(mobileSuitQuery.getMobileSuitList()).thenReturn(msList);
		when(armsQuery.getArmsList()).thenReturn(armsList);

		mvc.perform(get("/MSDB/MobileSuits/Equipments/-/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/-/new/EquipmentRegister"))
		.andExpect(model().attribute("equipmentRegistForm", new RegistEquipmentForm(msList, armsList)));

		verify(mobileSuitQuery).getMobileSuitList();
		verify(armsQuery).getArmsList();
	}

	@Test
	void 装備データを登録して一覧画面に遷移すること() throws Exception {
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
				.build());

		doNothing().when(registEquipmentUseCase).exeute(any());
		when(equipmentQuery.getEquipmentList()).thenReturn(list);

		mvc.perform(post("/MSDB/MobileSuits/Equipments/-/new")
				.param("msId", "ms2")
				.param("armsId", "arms2")
				.param("numberEquipment", "2")
				.param("detail", "テスト詳細2"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/Equipments/EquipmentList"))
		.andExpect(model().attribute("equipments", list));
	}

}
