package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitResult {
	private String msId;
	private String modelNumber;
	private String msName;
	private String insertDate;
	private String updateDate;

}
