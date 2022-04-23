package jp.co.sunarch.mobilesuitDatabase.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Equipment {
	private String equipmentId;
	private String msId;
	private String armedId;
	private int numberEquipment;

}
