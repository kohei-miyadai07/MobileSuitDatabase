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
		entity.setHeadHeight(domain.getHeadHeight());
		entity.setOverallHeight(domain.getOverallHeight());
		entity.setWeight(domain.getWeight());
		entity.setTotalWeight(domain.getTotalWeight());
		entity.setPowerSource(domain.getPowerSource());
		entity.setMaterial(domain.getMaterial());
		entity.setEffectiveSensorRadius(domain.getEffectiveSensorRadius());
		entity.setGeneratorOutput(domain.getGeneratorOutput());
		entity.setTotalThrustersOutput(domain.getTotalThrustersOutput());
		entity.setMsOverview(domain.getMsOverview());
		entity.setAction(domain.getAction());
		entity.setInsertDate(Timestamp.valueOf(domain.getInsertDate()));
		entity.setUpdateDate(Timestamp.valueOf(domain.getUpdateDate()));
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
				,entity.getInsertDate().toLocalDateTime()
				,entity.getUpdateDate().toLocalDateTime()
				,entity.getVersion());

		return domain;
	}

}
