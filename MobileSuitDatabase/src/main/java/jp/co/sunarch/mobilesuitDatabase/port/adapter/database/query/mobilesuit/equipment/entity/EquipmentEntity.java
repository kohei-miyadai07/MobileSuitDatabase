package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;

@Entity(naming = NamingType.LENIENT_SNAKE_LOWER_CASE)
@Table(name = "Equipment")
@Data
public class EquipmentEntity {
	@Id
	private String msId;

	private String msName;

	@Id
	private String armsId;

	private String armsName;

	private Integer numberEquipment;

	private String detail;

}
