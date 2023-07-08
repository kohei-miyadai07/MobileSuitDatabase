package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;

@Entity(naming = NamingType.LENIENT_SNAKE_LOWER_CASE)
@Table(name = "Arms")
@Data
public class DomaArmsEntity {
	@Id
	private String armsId;

	private String armsName;

	private String detail;

}
