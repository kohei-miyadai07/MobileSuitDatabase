package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;

@Entity(naming = NamingType.LENIENT_SNAKE_LOWER_CASE)
@Table(name = "MobileSuit")
@Data
public class MobileSuitEntity {
	@Id
	private String msId;

	private String modelNumber;

	private String msName;

	private String msUrl;

	private BigDecimal headHeight;

	private BigDecimal overallHeight;

	private BigDecimal weight;

	private BigDecimal totalWeight;

	private String powerSource;

	private String material;

	private Long effectiveSensorRadius;

	private Long generatorOutput;

	private Long totalThrustersOutput;

	private String msOverview;

	private String action;

	private Timestamp insertDate;

	private Timestamp updateDate;

	@Version
	private Integer version;

}
