package jp.co.sunarch.mobilesuitDatabase.model.result;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitsResult {
	private String msId;
	private String modelNumber;
	private String msName;
	private String msUrl;
	private String headHeight;
	private String insertDate;
	private String updateDate;

}
