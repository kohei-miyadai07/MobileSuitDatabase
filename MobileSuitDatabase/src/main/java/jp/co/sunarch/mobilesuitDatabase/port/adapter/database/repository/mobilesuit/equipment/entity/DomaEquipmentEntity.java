package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;

@Entity(naming = NamingType.LENIENT_SNAKE_LOWER_CASE)
@Table(name = "Equipment")
@Data
public class DomaEquipmentEntity {
	@Id
	private String msId;

	@Id
	private String armsId;

	private Integer numberEquipment;

	private String detail;

}
