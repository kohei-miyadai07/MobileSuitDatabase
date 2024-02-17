package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;

@Component
public class MobileSuitConverter {

	public MobileSuitEntity domainToEntity(MobileSuit domain) {

		MobileSuitEntity entity = new MobileSuitEntity();
		entity.setMsId(domain.getMsId().getValue());
		entity.setModelNumber(domain.getModelNumber());
		entity.setMsName(domain.getMsName());
		entity.setMsUrl(domain.getMsUrl());
		entity.setHeadHeight(domain.getHeadHeight().orElse(null));
		entity.setOverallHeight(domain.getOverallHeight().orElse(null));
		entity.setWeight(domain.getWeight().orElse(null));
		entity.setTotalWeight(domain.getTotalWeight().orElse(null));
		entity.setPowerSource(domain.getPowerSource().orElse(null));
		entity.setMaterial(domain.getMaterial().orElse(null));
		entity.setEffectiveSensorRadius(domain.getEffectiveSensorRadius().orElse(null));
		entity.setGeneratorOutput(domain.getGeneratorOutput().orElse(null));
		entity.setTotalThrustersOutput(domain.getTotalThrustersOutput().orElse(null));
		entity.setMsOverview(domain.getMsOverview().orElse(null));
		entity.setAction(domain.getAction().orElse(null));
		entity.setInsertDate(Timestamp.from(domain.getInsertDate()));
		entity.setUpdateDate(Timestamp.from(domain.getUpdateDate()));
		entity.setVersion(domain.getVersion());

		return entity;
	}

	public MobileSuit entityToDomain(MobileSuitEntity entity) {

		MobileSuit domain = MobileSuit.create(
				MobileSuitId.of(entity.getMsId())
				,entity.getModelNumber()
				,entity.getMsName()
				,entity.getMsUrl()
				,entity.getHeadHeight()
				,entity.getOverallHeight()
				,entity.getWeight()
				,entity.getTotalWeight()
				,entity.getPowerSource()
				,entity.getMaterial()
				,entity.getEffectiveSensorRadius()
				,entity.getGeneratorOutput()
				,entity.getTotalThrustersOutput()
				,entity.getMsOverview()
				,entity.getAction()
				,entity.getInsertDate().toInstant()
				,entity.getUpdateDate().toInstant()
				,entity.getVersion());

		return domain;
	}
}
