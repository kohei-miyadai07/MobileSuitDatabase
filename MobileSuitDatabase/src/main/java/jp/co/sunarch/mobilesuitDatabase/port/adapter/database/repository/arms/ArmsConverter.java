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
		entity.setInsertDate(Timestamp.valueOf(domain.getInsertDate()));
		entity.setUpdateDate(Timestamp.valueOf(domain.getUpdateDate()));
		entity.setVersion(domain.getVersion());

		return entity;
	}

	public Arms entityToDomain(ArmsEntity entity) {
		Arms domain = Arms.create(
				ArmsId.of(entity.getArmsId())
				,entity.getArmsName()
				,entity.getDetail()
				,entity.getInsertDate().toLocalDateTime()
				,entity.getUpdateDate().toLocalDateTime()
				,entity.getVersion());

		return domain;
	}
}
