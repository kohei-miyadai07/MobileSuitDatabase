package jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeleteMobileSuitCommand {
	private MobileSuitId msId;

}
