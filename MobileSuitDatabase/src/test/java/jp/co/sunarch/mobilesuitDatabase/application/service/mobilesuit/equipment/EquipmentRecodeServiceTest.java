package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment;

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

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.equipment.EquipmentRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

@ExtendWith(MockitoExtension.class)
class EquipmentRecodeServiceTest {
	@InjectMocks
	EquipmentRecodeService sut;

	@Mock
	EquipmentRepository equipmentRepository;

	@Test
	void 装備登録処理が呼び出されること() {
		doNothing().when(equipmentRepository).save(any());

		Equipment equipment = Equipment.create(
				MobileSuitId.of("ms_test"),
				ArmsId.of("arms_test"),
				Integer.valueOf(1),
				"テスト001");
		sut.registEquipment(equipment);

		verify(equipmentRepository, times(1)).save(equipment);
	}

	@Test
	void 装備更新処理が呼び出されること() {
		doNothing().when(equipmentRepository).save(any());

		Equipment equipment = Equipment.create(
				MobileSuitId.of("ms_test"),
				ArmsId.of("arms_test"),
				Integer.valueOf(1),
				"テスト001");
		sut.updateEquipment(equipment);

		verify(equipmentRepository, times(1)).save(equipment);
	}

	@Test
	void 装備削除処理が呼び出されること() {
		doNothing().when(equipmentRepository).deleteEquipmentByMsIdAndArmsId(any(), any());

		Equipment equipment = Equipment.create(
				MobileSuitId.of("ms_test"),
				ArmsId.of("arms_test"),
				Integer.valueOf(1),
				"テスト001");
		sut.deleteEquipment(equipment);

		verify(equipmentRepository, times(1))
		.deleteEquipmentByMsIdAndArmsId("ms_test", "arms_test");
	}

	@Test
	void モビルスーツに紐づいた装備データ削除処理が呼び出されること() {
		doNothing().when(equipmentRepository).deleteEquipmentByMsid(any());

		MobileSuit mobileSuit = MobileSuit.create(
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
				Integer.valueOf(1));
		sut.deleteEquipmentByMobileSuit(mobileSuit);

		verify(equipmentRepository, times(1)).deleteEquipmentByMsid("ms1");
	}

	@Test
	void 武器に紐づいた装備データ削除処理が呼び出されること() {
		doNothing().when(equipmentRepository).deleteEquipmentByArmsId(any());

		Arms arms = Arms.create(
				ArmsId.of("arms1"),
				"arms_name1",
				"テスト");
		sut.deleteEquipmentByArms(arms);

		verify(equipmentRepository, times(1)).deleteEquipmentByArmsId("arms1");
	}

	private LocalDateTime localDateTimeOf(String strDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		return LocalDateTime.parse(strDateTime, formatter);
	}

}
