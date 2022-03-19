package jp.co.sunarch.mobilesuitDatabase.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitInfo {
	private String msId;
	private String msName;
}
