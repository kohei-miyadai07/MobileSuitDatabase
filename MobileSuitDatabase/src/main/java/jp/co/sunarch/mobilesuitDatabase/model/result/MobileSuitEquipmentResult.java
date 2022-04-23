package jp.co.sunarch.mobilesuitDatabase.model.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitEquipmentResult {
	public String equipmentId;
	public String msName;
	public String armedName;
	public String numberEquipment;

}
