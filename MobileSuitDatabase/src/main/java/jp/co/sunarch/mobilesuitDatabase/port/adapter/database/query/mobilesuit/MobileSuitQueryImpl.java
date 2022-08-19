package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.equipment.JdbcTemplateEquipmentDao;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.equipment.entity.EquipmentArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.from.mobilesuit.MobileSuitDetailFrom;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.equipment.EquipmentArmsResult;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitDetailResult;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitResult;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitQueryImpl implements MobileSuitQuery {

	private final JdbcTemplateMobileSuitDao mobileSuitDao;
	
	private final JdbcTemplateEquipmentDao equipmentDao;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private NumberFormat comFormat = NumberFormat.getNumberInstance();

	@Override
	public List<MobileSuitResult> getMobileSuitList() {

		List<MobileSuitEntity> msEntityList = mobileSuitDao.selectMobileSuitList();
		List<MobileSuitResult> msResultList = msEntityList.stream().map(l -> toResult(l)).toList();
		return msResultList;
	}

	@Override
	public MobileSuitDetailResult getMobileSuitDetail(MobileSuitDetailFrom msIdFrom) {

		MobileSuitEntity msEntity = mobileSuitDao.selectMobileSuitById(msIdFrom.getMsId());
		List<EquipmentArmsEntity> equipmentArmsEntityList = equipmentDao.selectEquipmentArmsByMsId(msIdFrom.getMsId());

		return toJoinResult(msEntity, equipmentArmsEntityList);
	}

	private MobileSuitResult toResult(MobileSuitEntity msEntity) {

		MobileSuitResult msResult = MobileSuitResult.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.msUrl(msEntity.getMsUrl())
				.headHeight(String.format("%sm", msEntity.getHeadHeight().toPlainString()))
				.overallHeight(String.format("%sm", msEntity.getOverallHeight().toPlainString()))
				.weight(String.format("%st", msEntity.getWeight().toString()))
				.totalWeight(String.format("%st", msEntity.getTotalWeight().toString()))
				.powerSource(msEntity.getPowerSource())
				.material(msEntity.getMaterial())
				.effectiveSensorRadius(String.format("%sm", comFormat.format(msEntity.getEffectiveSensorRadius())))
				.generatorOutput(String.format("%skW", 
						comFormat.format(msEntity.getGeneratorOutput())))
				.totalThrustersOutput(String.format("%skg", 
						comFormat.format(msEntity.getTotalThrustersOutput())))
				.msOverview(msEntity.getMsOverview())
				.action(msEntity.getAction())
				.insertDate(sdf.format(msEntity.getInsertDate()))
				.updateDate(sdf.format(msEntity.getUpdateDate()))
				.version(msEntity.getVersion())
				.build();

		return msResult;
	}

	private EquipmentArmsResult toEquipmentResult(EquipmentArmsEntity Entity) {
		
		EquipmentArmsResult result = EquipmentArmsResult.builder()
				.msId(Entity.getMsId())
				.armsId(Entity.getArmsId())
				.armsName(Entity.getArmsName())
				.numberEquipment(String.valueOf(Entity.getNumberEquipment()))
				.detail(Entity.getDetail())
				.build();
		
		return result;
	}

	private MobileSuitDetailResult toJoinResult(MobileSuitEntity msEntity, List<EquipmentArmsEntity> equipmentArmsEntityList) {
		
		MobileSuitDetailResult msDetailResult = MobileSuitDetailResult.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.msUrl(msEntity.getMsUrl())
				.headHeight(String.format("%sm", msEntity.getHeadHeight().toPlainString()))
				.overallHeight(String.format("%sm", msEntity.getOverallHeight().toPlainString()))
				.weight(String.format("%st", msEntity.getWeight().toString()))
				.totalWeight(String.format("%st", msEntity.getTotalWeight().toString()))
				.powerSource(msEntity.getPowerSource())
				.material(msEntity.getMaterial())
				.effectiveSensorRadius(String.format("%sm", comFormat.format(msEntity.getEffectiveSensorRadius())))
				.generatorOutput(String.format("%skW", 
						comFormat.format(msEntity.getGeneratorOutput())))
				.totalThrustersOutput(String.format("%skg", 
						comFormat.format(msEntity.getTotalThrustersOutput())))
				.msOverview(msEntity.getMsOverview())
				.action(msEntity.getAction())
				.insertDate(sdf.format(msEntity.getInsertDate()))
				.updateDate(sdf.format(msEntity.getUpdateDate()))
				.version(msEntity.getVersion())
				.equipmentArmsResultList(equipmentArmsEntityList.stream().map(l -> toEquipmentResult(l)).toList())
				.build();

		return msDetailResult;
	}

}
