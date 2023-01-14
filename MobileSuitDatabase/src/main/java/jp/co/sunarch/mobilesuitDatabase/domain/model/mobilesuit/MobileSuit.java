package jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	private long effectiveSensorRadius;
	private long generatorOutput;
	private long totalThrustersOutput;
	private String msOverview;
	private String action;
	private LocalDateTime insertDate;
	private LocalDateTime updateDate;
	private Integer version;

	public static MobileSuit create(MobileSuitId msId, String modelNumber, String msName, String msUrl,
			BigDecimal headHeight, BigDecimal overallHeight, BigDecimal weight, BigDecimal totalWeight,
			String powerSource, String material, long effectiveSensorRadius, long generatorOutput, long totalThrustersOutput,
			String msOverview, String action, LocalDateTime insertDate, LocalDateTime updateDate, Integer version) {
		
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

}
