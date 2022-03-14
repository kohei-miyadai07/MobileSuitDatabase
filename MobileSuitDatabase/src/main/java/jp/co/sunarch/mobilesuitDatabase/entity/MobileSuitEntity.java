package jp.co.sunarch.mobilesuitDatabase.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitEntity {
	private String msId;
	private String modelNumber;
	private String msName;
	private String msUrl;
	private BigDecimal headHeight;
	private BigDecimal weight;
	private BigDecimal totalWeight;
	private String powerSource;
	private String material;
	private long generatorOutput;
	private long totalThrustersOutput;
	private String msOverview;
	private String action;

}
