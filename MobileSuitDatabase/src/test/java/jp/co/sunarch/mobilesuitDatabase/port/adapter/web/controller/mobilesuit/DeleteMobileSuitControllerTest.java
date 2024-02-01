package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.DeleteMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit.DeleteMobileSuitUseCase;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;

@WebMvcTest(DeleteMobileSuitController.class)
class DeleteMobileSuitControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	MobileSuitQuery mobileSuitQuery;

	@MockBean
	DeleteMobileSuitUseCase deleteMobileSuitUseCase;

	@Test
	void モビルスーツデータを削除して一覧画面に遷移すること() throws Exception {
		DeleteMobileSuitCommand command = DeleteMobileSuitCommand.builder()
				.msId(MobileSuitId.of("ms2"))
				.build();

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
				.version("2")
				.build()
				);

		doNothing().when(deleteMobileSuitUseCase).execute(any());
		when(mobileSuitQuery.getMobileSuitList()).thenReturn(list);

		mvc.perform(post("/MSDB/MobileSuits/ms2/edit-delete"))
		.andExpect(status().isOk())
		.andExpect(view().name("/MSDB/MobileSuits/MobileSuitList"))
		.andExpect(model().attribute("mobilesuits", list));

		verify(deleteMobileSuitUseCase).execute(command);
		verify(mobileSuitQuery).getMobileSuitList();
	}

}
