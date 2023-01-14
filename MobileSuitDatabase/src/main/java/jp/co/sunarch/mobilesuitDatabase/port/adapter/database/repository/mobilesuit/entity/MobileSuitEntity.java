package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class MobileSuitEntity {
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
	private long effectiveSensorRadius;
	private long generatorOutput;
	private long totalThrustersOutput;
	private String msOverview;
	private String action;
	private Timestamp insertDate;
	private Timestamp updateDate;
	private Integer version;

}
