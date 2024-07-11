package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.equipment;

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
class EquipmentRecodeServiceTest {
	@InjectMocks
	EquipmentRecodeService sut;

	@Mock
	EquipmentRepository equipmentRepository;

	@Test
	void 装備登録処理が呼び出されること() {
		doNothing().when(equipmentRepository).save(any());

		Equipment equipment = new Equipment();
		equipment.setMsId(MobileSuitId.of("ms_test"));
		equipment.setArmsId(ArmsId.of("arms_test"));
		equipment.setNumberEquipment(Integer.valueOf(1));
		equipment.setDetail("テスト001");
		equipment.setInsertDate(Instant.ofEpochSecond(0));
		equipment.setUpdateDate(Instant.ofEpochSecond(0));
		equipment.setVersion(Integer.valueOf(1));

		sut.registEquipment(equipment);

		verify(equipmentRepository, times(1)).save(equipment);
	}

	@Test
	void 装備更新処理が呼び出されること() {
		doNothing().when(equipmentRepository).save(any());

		Equipment equipment = new Equipment();
		equipment.setMsId(MobileSuitId.of("ms_test"));
		equipment.setArmsId(ArmsId.of("arms_test"));
		equipment.setNumberEquipment(Integer.valueOf(1));
		equipment.setDetail("テスト001");
		equipment.setInsertDate(Instant.ofEpochSecond(0));
		equipment.setUpdateDate(Instant.ofEpochSecond(0));
		equipment.setVersion(Integer.valueOf(2));

		sut.updateEquipment(equipment);

		verify(equipmentRepository, times(1)).save(equipment);
	}

	@Test
	void 装備削除処理が呼び出されること() {
		doNothing().when(equipmentRepository).deleteEquipmentByMsIdAndArmsId(any(), any());

		Equipment equipment = new Equipment();
		equipment.setMsId(MobileSuitId.of("ms_test"));
		equipment.setArmsId(ArmsId.of("arms_test"));
		equipment.setNumberEquipment(Integer.valueOf(1));
		equipment.setDetail("テスト001");
		equipment.setInsertDate(Instant.ofEpochSecond(0));
		equipment.setUpdateDate(Instant.ofEpochSecond(0));
		equipment.setVersion(Integer.valueOf(1));
		sut.deleteEquipment(equipment);

		verify(equipmentRepository, times(1))
		.deleteEquipmentByMsIdAndArmsId("ms_test", "arms_test");
	}
}
