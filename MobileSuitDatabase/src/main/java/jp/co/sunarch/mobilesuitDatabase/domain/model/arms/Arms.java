package jp.co.sunarch.mobilesuitDatabase.domain.model.arms;

import lombok.Data;

@Data
public class Arms {
	private ArmsId armsId;
	private String armsName;
	private String detail;

	public static Arms create(ArmsId armsId, String armsName, String detail) {
		Arms arms = new Arms();
		arms.setArmsId(armsId);
		arms.setArmsName(armsName);
		arms.setDetail(detail);

		return arms;
	}

}
