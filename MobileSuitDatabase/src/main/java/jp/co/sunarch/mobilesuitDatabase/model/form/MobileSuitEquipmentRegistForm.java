package jp.co.sunarch.mobilesuitDatabase.model.form;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.vo.MobileSuitEquipmentInfo;
import lombok.Data;

@Data
public class MobileSuitEquipmentRegistForm {
	private String equipmentId;
	private String msId;
	private String armedId;
	private String numberEquipment;

	private List<MobileSuitEquipmentInfo> msEquipmentInfoList;
	
}
