package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.DomaMobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;

@Component
public class MobileSuitConverter {

	public MobileSuitEntity domainToEntity(MobileSuit mobileSuit) {

		MobileSuitEntity msEntity = new MobileSuitEntity();
		msEntity.setMsId(mobileSuit.getMsId().getValue());
		msEntity.setModelNumber(mobileSuit.getModelNumber());
		msEntity.setMsName(mobileSuit.getMsName());
		msEntity.setMsUrl(mobileSuit.getMsUrl());
		msEntity.setHeadHeight(mobileSuit.getHeadHeight());
		msEntity.setOverallHeight(mobileSuit.getOverallHeight());
		msEntity.setWeight(mobileSuit.getWeight());
		msEntity.setTotalWeight(mobileSuit.getTotalWeight());
		msEntity.setPowerSource(mobileSuit.getPowerSource());
		msEntity.setMaterial(mobileSuit.getMaterial());
		msEntity.setEffectiveSensorRadius(mobileSuit.getEffectiveSensorRadius());
		msEntity.setGeneratorOutput(mobileSuit.getGeneratorOutput());
		msEntity.setTotalThrustersOutput(mobileSuit.getTotalThrustersOutput());
		msEntity.setMsOverview(mobileSuit.getMsOverview());
		msEntity.setAction(mobileSuit.getAction());
		msEntity.setInsertDate(Timestamp.valueOf(mobileSuit.getInsertDate()));
		msEntity.setUpdateDate(Timestamp.valueOf(mobileSuit.getUpdateDate()));
		msEntity.setVersion(mobileSuit.getVersion());

		return msEntity;
	}

	public MobileSuit entityToDomain(MobileSuitEntity msEntity) {

		MobileSuit mobileSuit = MobileSuit.create(
				MobileSuitId.of(msEntity.getMsId())
				,msEntity.getModelNumber()
				,msEntity.getMsName()
				,msEntity.getMsUrl()
				,msEntity.getHeadHeight()
				,msEntity.getOverallHeight()
				,msEntity.getWeight()
				,msEntity.getTotalWeight()
				,msEntity.getPowerSource()
				,msEntity.getMaterial()
				,msEntity.getEffectiveSensorRadius()
				,msEntity.getGeneratorOutput()
				,msEntity.getTotalThrustersOutput()
				,msEntity.getMsOverview()
				,msEntity.getAction()
				,msEntity.getInsertDate().toLocalDateTime()
				,msEntity.getUpdateDate().toLocalDateTime()
				,msEntity.getVersion());

		return mobileSuit;
	}

	public DomaMobileSuitEntity domadomainToEntity(MobileSuit mobileSuit) {

		DomaMobileSuitEntity msEntity = new DomaMobileSuitEntity();
		msEntity.setMsId(mobileSuit.getMsId().getValue());
		msEntity.setModelNumber(mobileSuit.getModelNumber());
		msEntity.setMsName(mobileSuit.getMsName());
		msEntity.setMsUrl(mobileSuit.getMsUrl());
		msEntity.setHeadHeight(mobileSuit.getHeadHeight());
		msEntity.setOverallHeight(mobileSuit.getOverallHeight());
		msEntity.setWeight(mobileSuit.getWeight());
		msEntity.setTotalWeight(mobileSuit.getTotalWeight());
		msEntity.setPowerSource(mobileSuit.getPowerSource());
		msEntity.setMaterial(mobileSuit.getMaterial());
		msEntity.setEffectiveSensorRadius(mobileSuit.getEffectiveSensorRadius());
		msEntity.setGeneratorOutput(mobileSuit.getGeneratorOutput());
		msEntity.setTotalThrustersOutput(mobileSuit.getTotalThrustersOutput());
		msEntity.setMsOverview(mobileSuit.getMsOverview());
		msEntity.setAction(mobileSuit.getAction());
		msEntity.setInsertDate(Timestamp.valueOf(mobileSuit.getInsertDate()));
		msEntity.setUpdateDate(Timestamp.valueOf(mobileSuit.getUpdateDate()));
		msEntity.setVersion(mobileSuit.getVersion());

		return msEntity;
	}

	public MobileSuit entityToDomain(DomaMobileSuitEntity msEntity) {

		MobileSuit mobileSuit = MobileSuit.create(
				MobileSuitId.of(msEntity.getMsId())
				,msEntity.getModelNumber()
				,msEntity.getMsName()
				,msEntity.getMsUrl()
				,msEntity.getHeadHeight()
				,msEntity.getOverallHeight()
				,msEntity.getWeight()
				,msEntity.getTotalWeight()
				,msEntity.getPowerSource()
				,msEntity.getMaterial()
				,msEntity.getEffectiveSensorRadius()
				,msEntity.getGeneratorOutput()
				,msEntity.getTotalThrustersOutput()
				,msEntity.getMsOverview()
				,msEntity.getAction()
				,msEntity.getInsertDate().toLocalDateTime()
				,msEntity.getUpdateDate().toLocalDateTime()
				,msEntity.getVersion());

		return mobileSuit;
	}

}
