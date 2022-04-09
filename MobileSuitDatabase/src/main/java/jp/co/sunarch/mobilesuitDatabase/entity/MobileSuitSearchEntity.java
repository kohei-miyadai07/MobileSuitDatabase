package jp.co.sunarch.mobilesuitDatabase.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitSearchEntity {
	private String msName;
	private String modelNumber;
	private BigDecimal headHeightFrom;
	private BigDecimal headHeightTo;
	private BigDecimal weightFrom;
	private BigDecimal weightTo;
	private BigDecimal totalWeightFrom;
	private BigDecimal totalWeightTo;
	private String powerSource;
	private String material;
	private long generatorOutputFrom;
	private long generatorOutputTo;
	private long totalThrustersOutputFrom;
	private long totalThrustersOutputTo;
}
