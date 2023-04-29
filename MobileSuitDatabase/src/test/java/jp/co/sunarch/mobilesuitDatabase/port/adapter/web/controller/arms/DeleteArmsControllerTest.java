package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import static org.mockito.Mockito.*;
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

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.DeleteArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.DeleteArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@WebMvcTest(DeleteArmsController.class)
class DeleteArmsControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	ArmsQuery armsQuery;

	@MockBean
	DeleteArmsUseCase deleteArmsUseCase;

	@Test
	void 武器データを削除して一覧画面に遷移すること() throws Exception {
		DeleteArmsCommand command = DeleteArmsCommand.builder()
				.armsId(ArmsId.of("test_arms"))
				.build();

		List<ArmsModel> list = Arrays.asList(
				ArmsModel.builder()
				.armsId("test_arms_a")
				.armsName("テスト武器A")
				.detail("テスト")
				.build()
				);

		doNothing().when(deleteArmsUseCase).execute(any());
		when(armsQuery.getArmsList()).thenReturn(list);

		mvc.perform(post("/MSDB/Arms/test_arms/edit-delete"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/ArmsList"))
		.andExpect(model().attribute("armsList", list));

		verify(deleteArmsUseCase).execute(command);
		verify(armsQuery).getArmsList();
	}

}
