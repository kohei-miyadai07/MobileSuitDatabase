package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity;

import java.sql.Timestamp;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

import lombok.Data;

@Entity
@Table(name = "Equipment")
@Data
public class EquipmentEntity {
	@Id
	private String msId;

	@Id
	private String armsId;

	private Integer numberEquipment;

	private String detail;

	private Timestamp insertDate;

	private Timestamp updateDate;

	@Version
	private Integer version;
}
