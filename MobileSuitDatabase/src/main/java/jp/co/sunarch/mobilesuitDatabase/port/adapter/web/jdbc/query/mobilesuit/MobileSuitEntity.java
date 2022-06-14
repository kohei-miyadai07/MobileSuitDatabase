package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.jdbc.query.mobilesuit;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileSuitEntity {
	private String msId;
	private String modelNumber;
	private String msName;
	private Timestamp insertDate;
	private Timestamp updateDate;
	private String version;

}
