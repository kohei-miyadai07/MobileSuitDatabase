package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

@Entity
@Data
public class DomaEquipmentArmsEntity {
	@Column(name = "ms_id")
	private String msId;

	@Column(name = "arms_id")
	private String armsId;

	@Column(name = "arms_name")
	private String armsName;

	@Column(name = "number_equipment")
	private int numberEquipment;

	@Column(name = "detail")
	private String detail;

}
