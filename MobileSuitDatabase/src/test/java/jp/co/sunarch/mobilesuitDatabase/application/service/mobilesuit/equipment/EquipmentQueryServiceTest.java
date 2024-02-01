package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

@ExtendWith(MockitoExtension.class)
class EquipmentQueryServiceTest {
	@InjectMocks
	EquipmentQueryService sut;

	@Mock
	EquipmentRepository equipmentRepository;

	@Test
	void モビルスーツIDと武器IDに紐づく装備ドメインモデルを取得できること() {
		Equipment extend = Equipment.create(
				MobileSuitId.of("ms_test1"),
				ArmsId.of("arms_test1"),
				Integer.valueOf(1),
				"テスト001",
				Instant.ofEpochSecond(0),
				Instant.ofEpochSecond(0),
				Integer.valueOf(1));

		when(equipmentRepository.getEquipmentByMsIdAndArmsId(any(), any()))
		.thenReturn(extend);

		Equipment actual = sut.getEquipmentByMobileSuitIdAndArmsId(
				MobileSuitId.of("ms_test1"), ArmsId.of("arms_test1"));

		assertThat(actual)
		.isEqualTo(extend);
	}
}
