package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms.ArmsSearchForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@WebMvcTest(ArmsController.class)
class ArmsControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	ArmsQuery armsQuery;

	@Test
	void 武器一覧画面に遷移すること() throws Exception {
		List<ArmsModel> list = Arrays.asList(
				ArmsModel.builder()
				.armsId("arms_test1")
				.armsName("arms_test_name_1")
				.detail("テスト1")
				.build(),
				ArmsModel.builder()
				.armsId("arms_test2")
				.armsName("arms_test_name_2")
				.detail("テスト2")
				.build());

		when(armsQuery.getArmsList()).thenReturn(list);

		mvc.perform(get("/MSDB/Arms"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/ArmsList"))
		.andExpect(model().attribute("armsList", list));

		verify(armsQuery).getArmsList();
	}

	@Test
	void 武器検索画面に遷移すること() throws Exception {
		ArmsSearchForm form = new ArmsSearchForm();

		mvc.perform(get("/MSDB/Arms/-/search"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/-/search/ArmsSearch"))
		.andExpect(model().attribute("armsSearchForm", form));
	}

	@Test
	void 武器データを検索して一覧画面に遷移すること() throws Exception {
		Criteria criteria = ArmsQuery.Criteria.builder()
				.armsName("arms_test_name_A")
				.build();

		List<ArmsModel> list = Arrays.asList(
				ArmsModel.builder()
				.armsId("arms_test1")
				.armsName("arms_test_name_A")
				.detail("テストA")
				.build(),
				ArmsModel.builder()
				.armsId("arms_test2")
				.armsName("arms_test_name_A")
				.detail("テストB")
				.build());

		when(armsQuery.searchArms(any())).thenReturn(list);

		mvc.perform(post("/MSDB/Arms/-/search").param("armsName", "arms_test_name_A"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/ArmsList"))
		.andExpect(model().attribute("armsList", list));

		verify(armsQuery).searchArms(criteria);
	}

}
