package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.springframework.web.multipart.MultipartFile;

import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.RegistMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.RegistMobileSuitForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;

@WebMvcTest(RegistMobileSuitController.class)
class RegistMobileSuitControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	WebApplicationContext wac;

	@MockBean
	MobileSuitQuery mobileSuitQuery;

	@MockBean
	RegistMobileSuitUseCase registMobileSuitUseCase;

	@MockBean
	MultipartFile multipartFile;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void モビルスーツ登録画面に遷移すること() throws Exception {
		mvc.perform(get("/MSDB/MobileSuits/-/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/-/new/MobileSuitRegister"))
		.andExpect(model().attribute("msRegistForm", new RegistMobileSuitForm()));
	}

	@Test
	void モビルスーツデータを登録して一覧画面に遷移すること() throws Exception {
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
				.insertDate(Instant.ofEpochSecond(0))
				.updateDate(Instant.ofEpochSecond(0))
				.version("1")
				.build()
				);

		doNothing().when(registMobileSuitUseCase).execute(any());
		when(mobileSuitQuery.getMobileSuitList()).thenReturn(list);

		MockHttpServletRequestBuilder requestBuilder = multipart("/MSDB/MobileSuits/-/new")
				.file(file)
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
