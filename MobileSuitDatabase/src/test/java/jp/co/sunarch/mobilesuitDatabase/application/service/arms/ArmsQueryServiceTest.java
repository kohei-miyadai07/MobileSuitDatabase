package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

@ExtendWith(MockitoExtension.class)
class ArmsQueryServiceTest {
    @InjectMocks
	ArmsQueryService sut;

    @Mock
    ArmsRepository armsRepository;

	@Test
	void 武器IDに紐づく武器ドメインモデルを取得できること() {
		when(armsRepository.getArmsById(any()))
		.thenReturn(
				Arms.create(
						ArmsId.of("arms-test"),
						"arms-test-name",
						"arms-test-detail",
						localDateTimeOf("2023/04/02 10:00:00"),
						localDateTimeOf("2023/04/02 10:00:00"),
						Integer.valueOf(1)));

		Arms arms = sut.getArmsById(ArmsId.of("arms-test"));

		assertThat(arms)
		.isEqualTo(
				Arms.create(
						ArmsId.of("arms-test"),
						"arms-test-name",
						"arms-test-detail",
						localDateTimeOf("2023/04/02 10:00:00"),
						localDateTimeOf("2023/04/02 10:00:00"),
						Integer.valueOf(1)));

		verify(armsRepository).getArmsById("arms-test");
		
	}

	private LocalDateTime localDateTimeOf(String strDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		return LocalDateTime.parse(strDateTime, formatter);
	}

}
