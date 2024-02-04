package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitModel {
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
	private Instant insertDate;
	private Instant updateDate;
	private Integer version;
}
