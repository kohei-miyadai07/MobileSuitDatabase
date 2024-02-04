package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.UpdateArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.arms.UpdateArmsUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms.UpdateArmsForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@WebMvcTest(UpdateArmsController.class)
class UpdateArmsControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	ArmsQuery armsQuery;

	@MockBean
	UpdateArmsUseCase updateArmsUseCase;

	@Test
	void 武器更新画面に遷移すること() throws Exception {
		ArmsModel armsModel = ArmsModel.builder()
				.armsId("test_arms")
				.armsName("test_arms_name")
				.detail("テスト")
				.build();

		UpdateArmsForm form = UpdateArmsForm.ModelToForm(armsModel);

		when(armsQuery.getArmsById(any())).thenReturn(Optional.of(armsModel));

		mvc.perform(get("/MSDB/Arms/test_arms/edit"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/armsId/edit/ArmsEdit"))
		.andExpect(model().attribute("armsEditForm", form));

		verify(armsQuery).getArmsById("test_arms");
	}

	@Test
	void 武器データを更新して一覧画面に遷移すること() throws Exception {
		UpdateArmsCommand command = UpdateArmsCommand.builder()
				.armsId(ArmsId.of("test_arms"))
				.armsName("test_arms_name_update")
				.detail("テスト更新")
				.build();

		List<ArmsModel> list = Arrays.asList(
				ArmsModel.builder()
				.armsId("test_arms")
				.armsName("test_arms_name_update")
				.detail("テスト更新")
				.build(),
				ArmsModel.builder()
				.armsId("test_arms_a")
				.armsName("test_arms_a")
				.detail("テストA")
				.build());

		doNothing().when(updateArmsUseCase).execute(any());
		when(armsQuery.getArmsList()).thenReturn(list);

		mvc.perform(post("/MSDB/Arms/test_arms/edit-update")
				.param("armsId", "test_arms")
				.param("armsName", "test_arms_name_update")
				.param("detail", "テスト更新"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/Arms/ArmsList"))
		.andExpect(model().attribute("armsList", list));

		verify(updateArmsUseCase).execute(command);
		verify(armsQuery).getArmsList();
	}

}
