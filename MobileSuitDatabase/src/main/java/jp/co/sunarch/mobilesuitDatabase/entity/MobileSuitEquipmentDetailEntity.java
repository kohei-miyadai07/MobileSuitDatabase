package jp.co.sunarch.mobilesuitDatabase.entity;

import lombok.Data;

@Data
public class MobileSuitEquipmentDetailEntity {
	private String equipmentId;
	private String msId;
	private String armedId;
	private int numberEquipment;
    private String armedName;
	private String armedExplanation;

}
