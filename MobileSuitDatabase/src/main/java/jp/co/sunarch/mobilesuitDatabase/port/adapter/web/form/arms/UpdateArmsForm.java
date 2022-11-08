package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.arms;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.Data;

@Data
public class UpdateArmsForm {
	private String armsId;
	private String armsName;
	private String detail;

	public static UpdateArmsForm ModelToForm(ArmsModel model) {
		UpdateArmsForm form = new UpdateArmsForm();
		form.setArmsId(model.getArmsId());
		form.setArmsName(model.getArmsName());
		form.setDetail(model.getDetail());

		return form;
	}

}
