package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity;

import lombok.Data;

@Data
public class EquipmentEntity {
	private String msId;
	private String msName;
	private String armsId;
	private String armsName;
	private Integer numberEquipment;
	private String detail;

}
