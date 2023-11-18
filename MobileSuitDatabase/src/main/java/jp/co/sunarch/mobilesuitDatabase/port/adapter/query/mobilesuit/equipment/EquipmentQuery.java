package jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.equipment;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.Builder;
import lombok.Value;

public interface EquipmentQuery {

	@Value
	@Builder
	class Criteria {
		private String msName;
		private String armsName;
	}

	List<EquipmentModel> getEquipmentList();

	List<EquipmentModel> searchEquipment(Criteria criteria);

	EquipmentModel getEquipmentByMsIdAndArmsId(String msId, String armsId);
}
