package jp.co.sunarch.mobilesuitDatabase.domain.model.arms;

import java.time.Instant;
import java.util.Optional;

import lombok.Data;

@Data
public class Arms {
	private ArmsId armsId;
	private String armsName;
	private String detail;
	private Instant insertDate;
	private Instant updateDate;
	private Integer version;

	public static Arms create(ArmsId armsId, String armsName, String detail,
			Instant insertDate, Instant updateDate, Integer version) {
		Arms arms = new Arms();
		arms.setArmsId(armsId);
		arms.setArmsName(armsName);
		arms.setDetail(detail);
		arms.setInsertDate(insertDate);
		arms.setUpdateDate(updateDate);
		arms.setVersion(version);

		return arms;
	}

	public Optional<String> getDetail() {
		return Optional.ofNullable(detail);
	}
}
