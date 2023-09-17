package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

		sut.registArms(Arms.create(
				ArmsId.of("arms-test"),
				"arms-test-name",
				"arms-test-detail",
				localDateTimeOf("2023/04/02 10:00:00"),
				localDateTimeOf("2023/04/02 10:00:00"),
				Integer.valueOf(1)));

		verify(armsRepository, times(1)).save(
				Arms.create(
						ArmsId.of("arms-test"),
						"arms-test-name",
						"arms-test-detail",
						localDateTimeOf("2023/04/02 10:00:00"),
						localDateTimeOf("2023/04/02 10:00:00"),
						Integer.valueOf(1)));;
	}

	@Test
	void 武器データ更新処理が呼び出されること() {
		doNothing().when(armsRepository).save(any());

		sut.updateArms(Arms.create(
				ArmsId.of("arms-test"),
				"arms-test-name",
				"arms-test-detail",
				localDateTimeOf("2023/04/02 10:00:00"),
				localDateTimeOf("2023/04/02 10:00:00"),
				Integer.valueOf(2)
				));

		verify(armsRepository, times(1)).save(
				Arms.create(
						ArmsId.of("arms-test"),
						"arms-test-name",
						"arms-test-detail",
						localDateTimeOf("2023/04/02 10:00:00"),
						localDateTimeOf("2023/04/02 10:00:00"),
						Integer.valueOf(2)));
	}

	@Test
	void 武器データ削除処理が呼び出されること() {
		doNothing().when(armsRepository).deleteArmsById(any());

		Arms arms = Arms.create(
				ArmsId.of("arms-test"),
				"arms-test-name",
				"arms-test-detail",
				localDateTimeOf("2023/04/02 10:00:00"),
				localDateTimeOf("2023/04/02 10:00:00"),
				Integer.valueOf(1)
				);
		sut.deleteArms(arms);

		verify(armsRepository, times(1)).deleteArmsById(arms.getArmsId().getValue());
	}

	private LocalDateTime localDateTimeOf(String strDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		return LocalDateTime.parse(strDateTime, formatter);
	}

}
