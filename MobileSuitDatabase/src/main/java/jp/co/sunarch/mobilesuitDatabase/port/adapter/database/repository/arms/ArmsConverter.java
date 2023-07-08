package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.DomaArmsEntity;

@Component
public class ArmsConverter {

	public DomaArmsEntity domainToEntity(Arms arms) {
		DomaArmsEntity entity = new DomaArmsEntity();
		entity.setArmsId(arms.getArmsId().getValue());
		entity.setArmsName(arms.getArmsName());
		entity.setDetail(arms.getDetail());

		return entity;
	}

	public Arms entityToDomain(DomaArmsEntity entity) {
		Arms arms = Arms.create(
				ArmsId.of(entity.getArmsId())
				,entity.getArmsName()
				,entity.getDetail());

		return arms;
	}
}
