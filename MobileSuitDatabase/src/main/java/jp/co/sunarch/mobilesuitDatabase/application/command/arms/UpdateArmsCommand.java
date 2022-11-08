package jp.co.sunarch.mobilesuitDatabase.application.command.arms;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateArmsCommand {
	private ArmsId armsId;
	private String armsName;
	private String detail;

}
