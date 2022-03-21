package jp.co.sunarch.mobilesuitDatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileSuitEquipmentEntity {
	private String equipmentId;
	private String msId;
	private String msName;
	private String armedId;
	private String armedName;
	private int numberEquipment;
}
