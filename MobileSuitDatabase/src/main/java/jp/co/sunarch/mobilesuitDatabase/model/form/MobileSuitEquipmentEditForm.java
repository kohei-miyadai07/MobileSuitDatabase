package jp.co.sunarch.mobilesuitDatabase.model.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitEquipmentEditForm {
	private String equipmentId;
	private String msId;
	private String msName;
	private String armedId;
	private String armedName;
	private String numberEquipment;
	
}
