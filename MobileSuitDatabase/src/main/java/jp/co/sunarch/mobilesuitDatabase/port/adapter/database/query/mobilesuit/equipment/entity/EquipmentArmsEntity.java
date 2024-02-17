package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity;

import java.sql.Timestamp;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

@Entity
@Data
public class EquipmentArmsEntity {
	@Column(name = "ms_id")
	private String msId;

	@Column(name = "arms_id")
	private String armsId;

	@Column(name = "arms_name")
	private String armsName;

	@Column(name = "number_equipment")
	private Integer numberEquipment;

	@Column(name = "detail")
	private String detail;

	@Column(name = "insert_date")
	private Timestamp insertDate;

	@Column(name = "update_date")
	private Timestamp updateDate;

	@Column(name = "version")
	private Integer version;
}
