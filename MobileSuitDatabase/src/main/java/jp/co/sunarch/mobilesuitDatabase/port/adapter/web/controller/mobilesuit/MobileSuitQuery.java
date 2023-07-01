package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.math.BigDecimal;
import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.Builder;
import lombok.Value;

public interface MobileSuitQuery {
	
	@Value
	@Builder
	class Criteria {
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
	}
	
	List<MobileSuitModel> getMobileSuitList();

	MobileSuitDetailModel getMobileSuitDetail(String msId);

	MobileSuitModel getMobileSuitById(String msId);

	List<MobileSuitModel> searchMobileSuit(Criteria criteria);

}
