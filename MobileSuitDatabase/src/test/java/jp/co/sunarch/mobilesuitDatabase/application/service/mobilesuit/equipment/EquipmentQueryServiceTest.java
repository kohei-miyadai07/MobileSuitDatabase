package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

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
	void モビルスーツIDに紐づく装備ドメインモデルを取得できること() {
		List<Equipment> extendList = Arrays.asList(
				Equipment.create(
						MobileSuitId.of("ms_test1"),
						ArmsId.of("arms_test1"),
						Integer.valueOf(1),
						"テスト001"),
				Equipment.create(
						MobileSuitId.of("ms_test1"),
						ArmsId.of("arms_test2"),
						Integer.valueOf(2),
						"テスト002")
				);

		when(equipmentRepository.getEquipmentListByMsId(any()))
		.thenReturn(extendList);

		List<Equipment> actualList = sut.getEquipmentByMobileSuitId(MobileSuitId.of("ms_test1"));

		assertThat(actualList)
		.isEqualTo(extendList);
	}

	@Test
	void 武器IDに紐づく装備ドメインモデルを取得できること() {
		List<Equipment> extendList = Arrays.asList(
				Equipment.create(
						MobileSuitId.of("ms_test1"),
						ArmsId.of("arms_test1"),
						Integer.valueOf(1),
						"テスト001"),
				Equipment.create(
						MobileSuitId.of("ms_test2"),
						ArmsId.of("arms_test1"),
						Integer.valueOf(2),
						"テスト002")
				);

		when(equipmentRepository.getEquipmentListByArmsId(any()))
		.thenReturn(extendList);

		List<Equipment> actualList = sut.getEquipmentByArmsId(ArmsId.of("arms_test1"));

		assertThat(actualList)
		.isEqualTo(actualList);
	}

	@Test
	void モビルスーツIDと武器IDに紐づく装備ドメインモデルを取得できること() {
		Equipment extend = Equipment.create(
				MobileSuitId.of("ms_test1"),
				ArmsId.of("arms_test1"),
				Integer.valueOf(1),
				"テスト001");

		when(equipmentRepository.getEquipmentByMsIdAndArmsId(any(), any()))
		.thenReturn(extend);

		Equipment actual = sut.getEquipmentByMobileSuitIdAndArmsId(
				MobileSuitId.of("ms_test1"), ArmsId.of("arms_test1"));

		assertThat(actual)
		.isEqualTo(extend);
	}

}
