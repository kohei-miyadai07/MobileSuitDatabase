package jp.co.sunarch.mobilesuitDatabase.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileSuitArmedInfo {
	private String armedId;
	private String armedName;
}
