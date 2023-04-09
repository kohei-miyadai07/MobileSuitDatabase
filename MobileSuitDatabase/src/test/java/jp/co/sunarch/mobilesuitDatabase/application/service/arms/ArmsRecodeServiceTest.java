package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

@ExtendWith(MockitoExtension.class)
class ArmsRecodeServiceTest {
	@InjectMocks
	ArmsRecodeService sut;

	@Mock
	ArmsRepository armsRepository;

	@Test
	void 武器データ登録処理が呼び出されること() {
		doNothing().when(armsRepository).save(any());

		sut.registArms(Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail"));

		verify(armsRepository, times(1)).save(
				Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail"));;
	}

	@Test
	void 武器データ更新処理が呼び出されること() {
		doNothing().when(armsRepository).save(any());

		sut.updateArms(Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail"));

		verify(armsRepository, times(1)).save(
				Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail"));
	}

	@Test
	void 武器データ削除処理が呼び出されること() {
		doNothing().when(armsRepository).deleteArmsById(any());

		Arms arms = Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail");
		sut.deleteArms(arms);

		verify(armsRepository, times(1)).deleteArmsById(arms.getArmsId().getValue());
	}

}
