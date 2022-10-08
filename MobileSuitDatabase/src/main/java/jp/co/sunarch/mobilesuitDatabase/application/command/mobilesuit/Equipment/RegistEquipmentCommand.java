package jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistEquipmentCommand {
	private String msId;
	private String armsId;
	private Integer numberEquipment;
	private String detail;

}
