package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity;

import java.sql.Timestamp;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;

@Entity(naming = NamingType.LENIENT_SNAKE_LOWER_CASE)
@Table(name = "Arms")
@Data
public class ArmsEntity {
	@Id
	private String armsId;

	private String armsName;

	private String detail;

	private Timestamp insertDate;

	private Timestamp updateDate;

	private Integer version;
}
