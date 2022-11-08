package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArmsModel {
	private String armsId;
	private String armsName;
	private String detail;

}
