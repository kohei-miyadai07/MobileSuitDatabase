package jp.co.sunarch.mobilesuitDatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileSuitArmedEntity {
	private String armedId;
	private String armedName;
	private String armedExplanation;
}
