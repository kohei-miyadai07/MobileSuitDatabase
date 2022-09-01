package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.math.BigDecimal;
import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.EditMobileSuitResult;
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
		private long effectiveSensorRadiusFrom;
		private long effectiveSensorRadiusTo;
		private long generatorOutputFrom;
		private long generatorOutputTo;
		private long totalThrustersOutputFrom;
		private long totalThrustersOutputTo;
	}
	
	public List<MobileSuitModel> getMobileSuitList();

	public MobileSuitDetailModel getMobileSuitDetail(String msId);

	public MobileSuitModel getMobileSuitId(String msId);

	public List<MobileSuitModel> searchMobileSuit(Criteria criteria);

}
