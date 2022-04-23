package jp.co.sunarch.mobilesuitDatabase.model.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitArmedEditForm {
	private String armedId;
	private String armedName;
	private String armedExplanation;

}
