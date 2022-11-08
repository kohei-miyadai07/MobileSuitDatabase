package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.ArmsEntity;

@Component
public class ArmsConverter {

	public ArmsEntity domainToEntity(Arms arms) {
		ArmsEntity armsEntity = new ArmsEntity();
		armsEntity.setArmsId(arms.getArmsId().getValue());
		armsEntity.setArmsName(arms.getArmsName());
		armsEntity.setDetail(arms.getDetail());

		return armsEntity;
	}

	public Arms entityToDomain(ArmsEntity armsEntity) {
		Arms arms = Arms.create(
				ArmsId.of(armsEntity.getArmsId())
				,armsEntity.getArmsName()
				,armsEntity.getDetail());

		return arms;
	}
}
