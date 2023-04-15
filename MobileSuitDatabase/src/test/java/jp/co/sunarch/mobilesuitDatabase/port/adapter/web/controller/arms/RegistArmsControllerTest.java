package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.RegistArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.RegistArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms.RegistArmsForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@WebMvcTest(RegistArmsController.class)
class RegistArmsControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	ArmsQuery armsQuery;

	@MockBean
	RegistArmsUseCase registArmsUseCase;

	@Test
	void 武器登録画面に遷移すること() throws Exception {
		mvc.perform(get("/MSDB/Arms/-/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/-/new/ArmsRegister"))
		.andExpect(model().attribute("armsRegistForm", new RegistArmsForm()));
	}

	@Test
	void 武器データを登録して一覧画面に遷移すること() throws Exception {
		RegistArmsCommand command = RegistArmsCommand.builder()
				.armsName("テストビームサーベル")
				.detail("テスト")
				.build();

		List<ArmsModel> list = Arrays.asList(
				ArmsModel.builder()
				.armsId("arms_test1")
				.armsName("テストビームサーベル")
				.detail("テスト")
				.build(),
				ArmsModel.builder()
				.armsId("arms_test2")
				.armsName("テストビームサーベル_Before")
				.detail("テスト_Before")
				.build());

		doNothing().when(registArmsUseCase).execute(command);
		when(armsQuery.getArmsList()).thenReturn(list);

		mvc.perform(post("/MSDB/Arms/-/new").param("armsName", "テストビームサーベル").param("detail", "テスト"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/ArmsList"))
		.andExpect(model().attribute("armsList", list));

		verify(registArmsUseCase).execute(command);
		verify(armsQuery).getArmsList();
	}

}
