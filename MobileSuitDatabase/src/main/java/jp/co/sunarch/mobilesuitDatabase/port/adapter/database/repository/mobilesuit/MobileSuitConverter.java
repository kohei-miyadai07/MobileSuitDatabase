package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;

@Component
public class MobileSuitConverter {

	public MobileSuitEntity domainToEntity(MobileSuit mobileSuit) {

		MobileSuitEntity msEntity = new MobileSuitEntity();
		msEntity.setMsId(mobileSuit.getMsId().getMsId());
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

}
