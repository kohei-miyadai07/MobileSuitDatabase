package jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment;

import java.util.Optional;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.Data;

@Data
public class Equipment {
	private MobileSuitId msId;
	private ArmsId armsId;
	private Integer numberEquipment;
	private String detail;

	public static Equipment create(MobileSuitId msId, ArmsId armsId, Integer numberEquipment, String detail) {
		Equipment equipment = new Equipment();
		equipment.setMsId(msId);
		equipment.setArmsId(armsId);
		equipment.setNumberEquipment(numberEquipment);
		equipment.setDetail(detail);

		return equipment;
	}

	public Optional<Integer> getNumberEquipment() {
		return Optional.ofNullable(numberEquipment);
	}

	public Optional<String> getDetail() {
		return Optional.ofNullable(detail);
	}

}
