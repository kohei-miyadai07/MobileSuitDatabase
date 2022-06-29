package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileSuitModel {
	private String msId;
	private String modelNumber;
	private String msName;
	private String insertDate;
	private String updateDate;
	private String version;

}
