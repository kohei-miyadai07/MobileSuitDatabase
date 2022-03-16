package jp.co.sunarch.mobilesuitDatabase.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitArmedEntity {
	private String armedId;
	private String armedName;
	private String armedExplanation;
}
