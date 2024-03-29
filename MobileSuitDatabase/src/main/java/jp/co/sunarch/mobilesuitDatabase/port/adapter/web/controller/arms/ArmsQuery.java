package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.Builder;
import lombok.Value;

public interface ArmsQuery {
	
	@Value
	@Builder
	class Criteria {
		private String armsName;

	}

	List<ArmsModel> getArmsList();

	ArmsModel getArmsById(String armsId);

	List<ArmsModel> searchArms(Criteria criteria);

}
