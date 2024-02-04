package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.UpdateMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.UpdateMobileSuitForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;

@WebMvcTest(UpdateMobileSuitController.class)
class UpdateMobileSuitControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext wac;

	@MockBean
	MobileSuitQuery mobileSuitQuery;

	@MockBean
	UpdateMobileSuitUseCase updateMobileSuitUseCase;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void モビルスーツ更新画面に遷移すること() throws Exception {
		MobileSuitModel model = MobileSuitModel.builder()
				.msId("ms1")
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.msUrl("/ms/test")
				.headHeight(new BigDecimal(18.00).setScale(2, RoundingMode.DOWN))
				.overallHeight(new BigDecimal(18.50).setScale(2, RoundingMode.DOWN))
				.weight(new BigDecimal(90.25).setScale(2, RoundingMode.DOWN))
				.totalWeight(new BigDecimal(97.55).setScale(2, RoundingMode.DOWN))
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
				.build();

		UpdateMobileSuitForm form = UpdateMobileSuitForm.ModelToForm(model);

		when(mobileSuitQuery.getMobileSuitById(any())).thenReturn(model);

		mvc.perform(get("/MSDB/MobileSuits/ms1/edit"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/msId/edit/MobileSuitEdit"))
		.andExpect(model().attribute("msEditForm", form));

		verify(mobileSuitQuery).getMobileSuitById("ms1");
	}

	@Test
	void モビルスーツデータを更新して一覧画面に遷移すること() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "test.txt",
                "text/plain", "test data".getBytes(StandardCharsets.UTF_8));

		List<MobileSuitModel> list = Arrays.asList(
				MobileSuitModel.builder()
				.msId("ms1")
				.modelNumber("ms-01")
				.msName("テストモビルスーツ1")
				.msUrl("/ms/test")
				.headHeight(new BigDecimal(18.00).setScale(2, RoundingMode.DOWN))
				.overallHeight(new BigDecimal(19.00).setScale(2, RoundingMode.DOWN))
				.weight(new BigDecimal(85.55).setScale(2, RoundingMode.DOWN))
				.totalWeight(new BigDecimal(87.55).setScale(2, RoundingMode.DOWN))
				.powerSource("テストリアクター")
				.material("テストマテリアル")
				.effectiveSensorRadius(100L)
				.generatorOutput(200L)
				.totalThrustersOutput(300L)
				.msOverview("テスト概要")
				.action("テストアクション")
				.insertDate(Instant.ofEpochSecond(0))
				.updateDate(Instant.ofEpochSecond(0))
				.version(2)
				.build()
				);

		doNothing().when(updateMobileSuitUseCase).execute(any());
		when(mobileSuitQuery.getMobileSuitList()).thenReturn(list);

		MockHttpServletRequestBuilder requestBuilder = multipart("/MSDB/MobileSuits/ms1/edit-update")
				.file(file)
				.param("msId", "ms1")
				.param("modelNumber", "ms-01")
				.param("msName", "テストモビルスーツ1")
				.param("headHeight", "18.00")
				.param("overallHeight", "19.00")
				.param("weight", "85.55")
				.param("totalWeight", "87.55")
				.param("powerSource", "テストリアクター")
				.param("material", "テストマテリアル")
				.param("effectiveSensorRadius", "100")
				.param("generatorOutput", "200")
				.param("totalThrustersOutput", "300")
				.param("msOverview", "テスト概要")
				.param("action", "テストアクション");

		mvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/MobileSuitList"))
		.andExpect(model().attribute("mobilesuits", list));
	}
}
