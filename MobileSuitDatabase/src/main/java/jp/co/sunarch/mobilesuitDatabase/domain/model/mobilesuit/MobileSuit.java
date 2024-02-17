package jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import lombok.Data;

@Data
public class MobileSuit {
	private MobileSuitId msId;
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
	private Instant insertDate;
	private Instant updateDate;
	private Integer version;

	public static MobileSuit create(MobileSuitId msId, String modelNumber, String msName, String msUrl,
			BigDecimal headHeight, BigDecimal overallHeight, BigDecimal weight, BigDecimal totalWeight,
			String powerSource, String material, Long effectiveSensorRadius, Long generatorOutput, Long totalThrustersOutput,
			String msOverview, String action, Instant insertDate, Instant updateDate, Integer version) {
		
		MobileSuit mobileSuit = new MobileSuit();
		mobileSuit.setMsId(msId);
		mobileSuit.setModelNumber(modelNumber);
		mobileSuit.setMsName(msName);
		mobileSuit.setMsUrl(msUrl);
		mobileSuit.setHeadHeight(headHeight);
		mobileSuit.setOverallHeight(overallHeight);
		mobileSuit.setWeight(weight);
		mobileSuit.setTotalWeight(totalWeight);
		mobileSuit.setPowerSource(powerSource);
		mobileSuit.setMaterial(material);
		mobileSuit.setEffectiveSensorRadius(effectiveSensorRadius);
		mobileSuit.setGeneratorOutput(generatorOutput);
		mobileSuit.setTotalThrustersOutput(generatorOutput);
		mobileSuit.setMsOverview(msOverview);
		mobileSuit.setAction(action);
		mobileSuit.setInsertDate(insertDate);
		mobileSuit.setUpdateDate(updateDate);
		mobileSuit.setVersion(version);

		return mobileSuit;
	}

	public Optional<BigDecimal> getHeadHeight() {
		return Optional.ofNullable(headHeight);
	}

	public Optional<BigDecimal> getOverallHeight() {
		return Optional.ofNullable(overallHeight);
	}

	public Optional<BigDecimal> getWeight() {
		return Optional.ofNullable(weight);
	}

	public Optional<BigDecimal> getTotalWeight() {
		return Optional.ofNullable(totalWeight);
	}

	public Optional<String> getPowerSource() {
		return Optional.ofNullable(powerSource);
	}

	public Optional<String> getMaterial() {
		return Optional.ofNullable(material);
	}

	public Optional<Long> getEffectiveSensorRadius() {
		return Optional.ofNullable(effectiveSensorRadius);
	}

	public Optional<Long> getGeneratorOutput() {
		return Optional.ofNullable(generatorOutput);
	}

	public Optional<Long> getTotalThrustersOutput() {
		return Optional.ofNullable(totalThrustersOutput);
	}

	public Optional<String> getMsOverview() {
		return Optional.ofNullable(msOverview);
	}

	public Optional<String> getAction() {
		return Optional.ofNullable(action);
	}
}
