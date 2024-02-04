package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery.Criteria;
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
				.headHeight(new BigDecimal(18).setScale(2, RoundingMode.DOWN))
				.overallHeight(new BigDecimal(18.50).setScale(2, RoundingMode.DOWN))
				.weight(new BigDecimal(90.25).setScale(2, RoundingMode.DOWN))
				.totalWeight(new BigDecimal(95.85).setScale(2, RoundingMode.DOWN))
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius(1000L)
				.generatorOutput(2000L)
				.totalThrustersOutput(3000L)
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate(Instant.ofEpochSecond(0))
				.updateDate(Instant.ofEpochSecond(0))
				.version(1)
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
				.headHeight(new BigDecimal(18.00).setScale(2, RoundingMode.DOWN))
				.overallHeight(new BigDecimal(18.50).setScale(2, RoundingMode.DOWN))
				.weight(new BigDecimal(90.25).setScale(2, RoundingMode.DOWN))
				.totalWeight(new BigDecimal(95.80).setScale(2, RoundingMode.DOWN))
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius(1000L)
				.generatorOutput(2000L)
				.totalThrustersOutput(3000L)
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate(Instant.ofEpochSecond(0))
				.updateDate(Instant.ofEpochSecond(0))
				.version(1)
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
				.headHeight(new BigDecimal(19.25).setScale(2, RoundingMode.DOWN))
				.overallHeight(new BigDecimal(20.50).setScale(2, RoundingMode.DOWN))
				.weight(new BigDecimal(91.55).setScale(2, RoundingMode.DOWN))
				.totalWeight(new BigDecimal(99.50).setScale(2, RoundingMode.DOWN))
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius(110L)
				.generatorOutput(210L)
				.totalThrustersOutput(310L)
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate(Instant.ofEpochSecond(0))
				.updateDate(Instant.ofEpochSecond(0))
				.version(1)
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
				.numberEquipment(2)
				.detail("概要")
				.build(),
				EquipmentArmsModel.builder()
				.msId("ms1")
				.armsId("arms2")
				.armsName("arms_name2")
				.numberEquipment(3)
				.detail("概要2")
				.build()
				);
	}
}
