package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitSearchEntity {
	private String modelNumber;
	private String msName;
	private BigDecimal headHeightFrom;
	private BigDecimal headHeightTo;
	private BigDecimal overallHeightFrom;
	private BigDecimal overallHeightTo;
	private BigDecimal weightFrom;
	private BigDecimal weightTo;
	private BigDecimal totalWeightFrom;
	private BigDecimal totalWeightTo;
	private String powerSource;
	private String material;
	private long effectiveSensorRadiusFrom;
	private long effectiveSensorRadiusTo;
	private long generatorOutputFrom;
	private long generatorOutputTo;
	private long totalThrustersOutputFrom;
	private long totalThrustersOutputTo;
}
