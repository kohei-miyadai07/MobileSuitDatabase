package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EquipmentArmsModel {
	private String msId;
	private String armsId;
	private String armsName;
	private String numberEquipment;
	private String detail;
	private Instant insertDate;
	private Instant updateDate;
	private String version;
}
