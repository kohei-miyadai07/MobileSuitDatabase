package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.StandardCharsets;
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
				.version("2")
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
