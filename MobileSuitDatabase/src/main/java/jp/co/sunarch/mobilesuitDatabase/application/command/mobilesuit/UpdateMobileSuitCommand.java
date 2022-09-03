package jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit;

import java.math.BigDecimal;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateMobileSuitCommand {
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

}
