package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.Data;

@Data
public class UpdateEquipmentForm {
	private String msId;
	private String msName;
	private String armsId;
	private String armsName;
	private Integer numberEquipment;
	private String detail;

	public static UpdateEquipmentForm ModelToForm(EquipmentModel model) {
		UpdateEquipmentForm form = new UpdateEquipmentForm();
		form.setMsId(model.getMsId());
		form.setMsName(model.getMsName());
		form.setArmsId(model.getArmsId());
		form.setArmsName(model.getArmsName());
		form.setNumberEquipment(model.getNumberEquipment());
		form.setDetail(model.getDetail());

		return form;
	}
}
