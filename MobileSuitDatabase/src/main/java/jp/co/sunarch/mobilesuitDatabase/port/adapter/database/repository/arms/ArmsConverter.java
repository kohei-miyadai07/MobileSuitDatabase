package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.ArmsEntity;

@Component
public class ArmsConverter {

	public ArmsEntity domainToEntity(Arms domain) {
		ArmsEntity entity = new ArmsEntity();
		entity.setArmsId(domain.getArmsId().getValue());
		entity.setArmsName(domain.getArmsName());
		entity.setDetail(domain.getDetail().orElse(null));

		return entity;
	}

	public Arms entityToDomain(ArmsEntity entity) {
		Arms domain = Arms.create(
				ArmsId.of(entity.getArmsId())
				,entity.getArmsName()
				,entity.getDetail());

		return domain;
	}
}
