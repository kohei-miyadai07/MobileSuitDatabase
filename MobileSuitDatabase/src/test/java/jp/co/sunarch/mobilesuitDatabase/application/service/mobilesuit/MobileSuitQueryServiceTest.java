package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;

@ExtendWith(MockitoExtension.class)
class MobileSuitQueryServiceTest {
	@InjectMocks
	MobileSuitQueryService sut;

	@Mock
	MobileSuitRepository mobileSuitRepository;

	@Test
	void モビルスーツIDに紐づくモビルスーツドメインモデルが取得できること() {
		when(mobileSuitRepository.getMobileSuitById(any()))
		.thenReturn(
				MobileSuit.create(
						MobileSuitId.of("ms1"),
						"msNum1",
						"テストモビルスーツ1",
						"/ms/url1",
						new BigDecimal("18.55"),
						new BigDecimal("18.55"),
						new BigDecimal("18.55"),
						new BigDecimal("18.55"),
						"テストパワーソース1",
						"テストマテリアル1",
						100L,
						200L,
						300L,
						"テスト説明1",
						"テスト活躍1",
						localDateTimeOf("2023/04/02 10:00:00"),
						localDateTimeOf("2023/04/02 10:00:00"),
						Integer.valueOf(1))
				);

		MobileSuit mobileSuit = sut.getMobileSuitById(MobileSuitId.of("ms1"));

		assertThat(mobileSuit)
		.isEqualTo(
				MobileSuit.create(
						MobileSuitId.of("ms1"),
						"msNum1",
						"テストモビルスーツ1",
						"/ms/url1",
						new BigDecimal("18.55"),
						new BigDecimal("18.55"),
						new BigDecimal("18.55"),
						new BigDecimal("18.55"),
						"テストパワーソース1",
						"テストマテリアル1",
						100L,
						200L,
						300L,
						"テスト説明1",
						"テスト活躍1",
						localDateTimeOf("2023/04/02 10:00:00"),
						localDateTimeOf("2023/04/02 10:00:00"),
						Integer.valueOf(1))
				);

		verify(mobileSuitRepository, times(1)).getMobileSuitById("ms1");
	}

	private LocalDateTime localDateTimeOf(String strDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		return LocalDateTime.parse(strDateTime, formatter);
	}

}
