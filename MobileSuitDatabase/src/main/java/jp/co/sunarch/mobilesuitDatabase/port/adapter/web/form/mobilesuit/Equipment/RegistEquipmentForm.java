package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit.Equipment;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.Data;

@Data
public class RegistEquipmentForm {
	private String msId;
	private String armsId;
	private String numberEquipment;
	private String detail;
	
	private final List<MobileSuitModel> msList;

	private final List<ArmsModel> armsList;
}
