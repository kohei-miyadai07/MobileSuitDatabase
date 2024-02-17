package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArmsModel {
	private String armsId;
	private String armsName;
	private String detail;
	private Instant insertDate;
	private Instant updateDate;
	private Integer version;
}
