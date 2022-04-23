package jp.co.sunarch.mobilesuitDatabase.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Armed {
	private String armedId;
	private String armedName;
	private String armedExplanation;

}
