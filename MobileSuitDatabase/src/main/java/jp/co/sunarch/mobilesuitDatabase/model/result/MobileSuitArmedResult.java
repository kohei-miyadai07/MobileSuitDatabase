package jp.co.sunarch.mobilesuitDatabase.model.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitArmedResult {
	private String armedId;
	private String armedName;
	private String armedExplanation;

}
