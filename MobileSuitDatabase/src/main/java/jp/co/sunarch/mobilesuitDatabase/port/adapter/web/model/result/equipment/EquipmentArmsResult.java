package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.equipment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EquipmentArmsResult {
	private String msId;
	private String armsId;
	private String armsName;
	private String numberEquipment;
	private String detail;

}
