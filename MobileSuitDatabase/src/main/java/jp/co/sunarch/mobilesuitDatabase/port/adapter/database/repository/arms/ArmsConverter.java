package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import java.sql.Timestamp;

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
		entity.setInsertDate(Timestamp.from(domain.getInsertDate()));
		entity.setUpdateDate(Timestamp.from(domain.getUpdateDate()));
		entity.setVersion(domain.getVersion());

		return entity;
	}

	public Arms entityToDomain(ArmsEntity entity) {
		Arms domain = new Arms();
		domain.setArmsId(ArmsId.of(entity.getArmsId()));
		domain.setArmsName(entity.getArmsName());
		domain.setDetail(entity.getDetail());
		domain.setInsertDate(entity.getInsertDate().toInstant());
		domain.setUpdateDate(entity.getUpdateDate().toInstant());
		domain.setVersion(entity.getVersion());

		return domain;
	}
}
