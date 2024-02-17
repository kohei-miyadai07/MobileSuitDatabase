package jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms;

import java.util.List;
import java.util.Optional;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.arms.ArmsCountModel;
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

	Optional<ArmsModel> getArmsById(String armsId);

	List<ArmsModel> searchArms(Criteria criteria);

	ArmsCountModel getArmsCount();
}
