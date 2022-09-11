package jp.co.sunarch.mobilesuitDatabase.application.command.arms;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistArmsCommand {
	private String armsName;
	private String detail;

}
