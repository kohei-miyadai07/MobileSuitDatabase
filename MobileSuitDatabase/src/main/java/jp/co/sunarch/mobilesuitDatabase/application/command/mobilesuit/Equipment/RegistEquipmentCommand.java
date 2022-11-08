package jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.Equipment;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistEquipmentCommand {
	private MobileSuitId msId;
	private ArmsId armsId;
	private Integer numberEquipment;
	private String detail;

}
