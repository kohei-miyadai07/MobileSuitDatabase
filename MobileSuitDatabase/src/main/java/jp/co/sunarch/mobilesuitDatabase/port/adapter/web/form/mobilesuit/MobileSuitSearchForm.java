package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit;

import java.math.BigDecimal;

import lombok.Setter;

@Setter
public class MobileSuitSearchForm {
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
	private Long effectiveSensorRadiusFrom;
	private Long effectiveSensorRadiusTo;
	private Long generatorOutputFrom;
	private Long generatorOutputTo;
	private Long totalThrustersOutputFrom;
	private Long totalThrustersOutputTo;

	public String getModelNumber() {
		return (modelNumber != null && modelNumber.isEmpty()) ? null : modelNumber;
	}

	public String getMsName() {
		return (msName != null && msName.isEmpty()) ? null : msName;
	}

	public BigDecimal getHeadHeightFrom() {
		return headHeightFrom;
	}

	public BigDecimal getHeadHeightTo() {
		return headHeightTo;
	}

	public BigDecimal getOverallHeightFrom() {
		return overallHeightFrom;
	}

	public BigDecimal getOverallHeightTo() {
		return overallHeightTo;
	}

	public BigDecimal getWeightFrom() {
		return weightFrom;
	}

	public BigDecimal getWeightTo() {
		return weightTo;
	}

	public BigDecimal getTotalWeightFrom() {
		return totalWeightFrom;
	}

	public BigDecimal getTotalWeightTo() {
		return totalWeightTo;
	}

	public String getPowerSource() {
		return (powerSource != null && powerSource.isEmpty()) ? null : powerSource;
	}

	public String getMaterial() {
		return (material != null && material.isEmpty()) ? null : material;
	}

	public Long getEffectiveSensorRadiusFrom() {
		return effectiveSensorRadiusFrom;
	}

	public Long getEffectiveSensorRadiusTo() {
		return effectiveSensorRadiusTo;
	}

	public Long getGeneratorOutputFrom() {
		return generatorOutputFrom;
	}

	public Long getGeneratorOutputTo() {
		return generatorOutputTo;
	}

	public Long getTotalThrustersOutputFrom() {
		return totalThrustersOutputFrom;
	}

	public Long getTotalThrustersOutputTo() {
		return totalThrustersOutputTo;
	}
}
