package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EquipmentArmsForm {
	private String msId;
	private String armsId;
	private String armsName;
	private String numberEquipment;
	private String detail;

}
