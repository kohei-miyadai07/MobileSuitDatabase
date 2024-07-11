package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.Instant;

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

		Arms arms = new Arms();
		arms.setArmsId(ArmsId.of("arms-test"));
		arms.setArmsName("arms-test-name");
		arms.setDetail("arms-test-detail");
		arms.setInsertDate(Instant.ofEpochSecond(0));
		arms.setUpdateDate(Instant.ofEpochSecond(0));
		arms.setVersion(1);

		sut.registArms(arms);

		verify(armsRepository, times(1)).save(arms);
	}

	@Test
	void 武器データ更新処理が呼び出されること() {
		doNothing().when(armsRepository).save(any());

		Arms arms = new Arms();
		arms.setArmsId(ArmsId.of("arms-test"));
		arms.setArmsName("arms-test-name");
		arms.setDetail("arms-test-detail");
		arms.setInsertDate(Instant.ofEpochSecond(0));
		arms.setUpdateDate(Instant.ofEpochSecond(0));
		arms.setVersion(2);

		sut.updateArms(arms);

		verify(armsRepository, times(1)).save(arms);
	}

	@Test
	void 武器データ削除処理が呼び出されること() {
		doNothing().when(armsRepository).deleteArmsById(any());

		Arms arms = new Arms();
		arms.setArmsId(ArmsId.of("arms-test"));
		arms.setArmsName("arms-test-name");
		arms.setDetail("arms-test-detail");
		arms.setInsertDate(Instant.ofEpochSecond(0));
		arms.setUpdateDate(Instant.ofEpochSecond(0));
		arms.setVersion(1);

		sut.deleteArms(arms);

		verify(armsRepository, times(1)).deleteArmsById(arms.getArmsId().getValue());
	}
}
