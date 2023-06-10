package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.MobileSuitSearchForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentArmsModel;

@WebMvcTest(MobileSuitController.class)
class MobileSuitControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	MobileSuitQuery mobileSuitQuery;

	@Test
	void モビルスーツ一覧画面に遷移すること() throws Exception {
		List<MobileSuitModel> list = Arrays.asList(
				MobileSuitModel.builder()
				.msId("ms1")
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.msUrl("/ms/test")
				.headHeight("18.00")
				.overallHeight("18.50")
				.weight("90.25")
				.totalWeight("95.85")
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius("1000")
				.generatorOutput("2000")
				.totalThrustersOutput("3000")
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate("2023/04/18 10:20:30")
				.updateDate("2023/04/18 10:20:30")
				.version("1")
				.build()
				);

		when(mobileSuitQuery.getMobileSuitList()).thenReturn(list);

		mvc.perform(get("/MSDB/MobileSuits"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/MobileSuitList"))
		.andExpect(model().attribute("mobilesuits", list));

		verify(mobileSuitQuery).getMobileSuitList();
	}

	@Test
	void モビルスーツ詳細画面に遷移すること() throws Exception {
		MobileSuitDetailModel model = MobileSuitDetailModel.builder()
				.msId("ms1")
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.msUrl("/ms/test")
				.headHeight("18.00")
				.overallHeight("18.50")
				.weight("90.25")
				.totalWeight("95.85")
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius("1000")
				.generatorOutput("2000")
				.totalThrustersOutput("3000")
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate("2023/04/18 10:20:30")
				.updateDate("2023/04/18 10:20:30")
				.version("1")
				.equipmentArmsResultList(createEquipmentArmsModelList())
				.build();

		when(mobileSuitQuery.getMobileSuitDetail(any())).thenReturn(model);

		mvc.perform(get("/MSDB/MobileSuits/ms1"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/msId/MobileSuitDetail"))
		.andExpect(model().attribute("mobilesuitDetail", model));

		verify(mobileSuitQuery).getMobileSuitDetail("ms1");
	}

	@Test
	void モビルスーツ検索画面に遷移すること() throws Exception {
		MobileSuitSearchForm form = new MobileSuitSearchForm();

		mvc.perform(get("/MSDB/MobileSuits/-/search"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/-/search/MobileSuitSearch"))
		.andExpect(model().attribute("msSearchForm", form));
	}

	@Test
	void モビルスーツを検索して一覧画面に遷移すること() throws Exception {
		Criteria criteria = MobileSuitQuery.Criteria.builder()
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.headHeightFrom(new BigDecimal("18.55"))
				.headHeightTo(new BigDecimal("19.55"))
				.overallHeightFrom(new BigDecimal("20.25"))
				.overallHeightTo(new BigDecimal("21.25"))
				.weightFrom(new BigDecimal("90.55"))
				.weightTo(new BigDecimal("92.25"))
				.totalWeightFrom(new BigDecimal("99.50"))
				.totalWeightTo(new BigDecimal("99.65"))
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadiusFrom(100L)
				.effectiveSensorRadiusTo(150L)
				.generatorOutputFrom(200L)
				.generatorOutputTo(250L)
				.totalThrustersOutputFrom(300L)
				.totalThrustersOutputTo(350L)
				.build();

		List<MobileSuitModel> list = Arrays.asList(
				MobileSuitModel.builder()
				.msId("ms1")
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.msUrl("/ms/test")
				.headHeight("19.25")
				.overallHeight("20.50")
				.weight("91.55")
				.totalWeight("99.60")
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius("110")
				.generatorOutput("210")
				.totalThrustersOutput("310")
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate("2023/04/18 10:20:30")
				.updateDate("2023/04/18 10:20:30")
				.version("1")
				.build()
				);

		when(mobileSuitQuery.searchMobileSuit(any())).thenReturn(list);

		mvc.perform(post("/MSDB/MobileSuits/-/search")
				.param("modelNumber", "ms-01")
				.param("msName", "テストモビルスーツ1")
				.param("headHeightFrom", "18.55")
				.param("headHeightTo", "19.55")
				.param("overallHeightFrom", "20.25")
				.param("overallHeightTo", "21.25")
				.param("weightFrom", "90.55")
				.param("weightTo", "92.25")
				.param("totalWeightFrom", "99.50")
				.param("totalWeightTo", "99.65")
				.param("powerSource", "テストリアクター")
				.param("material", "テストマテリアル")
				.param("effectiveSensorRadiusFrom", "100")
				.param("effectiveSensorRadiusTo", "150")
				.param("generatorOutputFrom", "200")
				.param("generatorOutputTo", "250")
				.param("totalThrustersOutputFrom", "300")
				.param("totalThrustersOutputTo", "350"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/MobileSuitList"))
		.andExpect(model().attribute("mobilesuits", list));

		verify(mobileSuitQuery).searchMobileSuit(criteria);
	}

	private List<EquipmentArmsModel> createEquipmentArmsModelList() {
		return Arrays.asList(
				EquipmentArmsModel.builder()
				.msId("ms1")
				.armsId("arms1")
				.armsName("arms_name")
				.numberEquipment("2")
				.detail("概要")
				.build(),
				EquipmentArmsModel.builder()
				.msId("ms1")
				.armsId("arms2")
				.armsName("arms_name2")
				.numberEquipment("3")
				.detail("概要2")
				.build()
				);
	}

}