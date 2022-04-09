package jp.co.sunarch.mobilesuitDatabase.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileSuitsEntity {
	private String msId;
	private String modelNumber;
	private String msName;
	private Timestamp insertDate;
	private Timestamp updateDate;

}
