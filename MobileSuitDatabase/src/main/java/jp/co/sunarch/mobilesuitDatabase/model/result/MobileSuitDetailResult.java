package jp.co.sunarch.mobilesuitDatabase.model.result;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.vo.MobileSuitEquipment;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitDetailResult {
	private String msId;
	private String modelNumber;
	private String msName;
	private String msUrl;
	private String headHeight;
	private String weight;
	private String totalWeight;
	private String powerSource;
	private String material;
	private String generatorOutput;
	private String totalThrustersOutput;
	private String msOverview;
	private String action;
	
	private List<MobileSuitEquipment> msEquipmentList;
}
