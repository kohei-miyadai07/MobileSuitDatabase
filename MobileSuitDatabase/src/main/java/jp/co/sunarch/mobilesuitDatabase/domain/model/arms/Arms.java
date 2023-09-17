package jp.co.sunarch.mobilesuitDatabase.domain.model.arms;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Data;

@Data
public class Arms {
	private ArmsId armsId;
	private String armsName;
	private String detail;
	private LocalDateTime insertDate;
	private LocalDateTime updateDate;
	private Integer version;

	public static Arms create(ArmsId armsId, String armsName, String detail,
			LocalDateTime insertDate, LocalDateTime updateDate, Integer version) {
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
