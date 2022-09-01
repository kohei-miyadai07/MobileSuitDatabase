package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitIdEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.JdbcTemplateEquipmentDao;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentArmsModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.EditMobileSuitResult;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitQueryImpl implements MobileSuitQuery {

	private final JdbcTemplateMobileSuitDao mobileSuitDao;
	
	private final JdbcTemplateEquipmentDao equipmentDao;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private NumberFormat comFormat = NumberFormat.getNumberInstance();

	@Override
	public List<MobileSuitModel> getMobileSuitList() {

		List<MobileSuitEntity> msEntityList = mobileSuitDao.selectMobileSuitList();
		List<MobileSuitModel> msResultList = msEntityList.stream().map(l -> toResult(l)).toList();
		return msResultList;
	}

	@Override
	public MobileSuitDetailModel getMobileSuitDetail(String msId) {

		MobileSuitEntity msEntity = mobileSuitDao.selectMobileSuitById(msId);
		List<EquipmentArmsEntity> equipmentArmsEntityList = equipmentDao.selectEquipmentArmsByMsId(msId);

		return toJoinResult(msEntity, equipmentArmsEntityList);
	}

	@Override
	public MobileSuitModel getMobileSuitId(String msId) {

		MobileSuitEntity msEntity = mobileSuitDao.selectMobileSuitById(msId);
		MobileSuitModel msModel = toResult(msEntity);

		return msModel;
	}

	@Override
	public List<MobileSuitModel> searchMobileSuit(Criteria criteria) {
		List<MobileSuitEntity> msEntityList = mobileSuitDao.selectMobileSuitByCriteria(criteria);
		List<MobileSuitModel> msModelList = msEntityList.stream().map(l -> toResult(l)).toList();

		return msModelList;
	}

	private MobileSuitModel toResult(MobileSuitEntity msEntity) {

		MobileSuitModel msResult = MobileSuitModel.builder()
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

	private EquipmentArmsModel toEquipmentResult(EquipmentArmsEntity Entity) {
		
		EquipmentArmsModel result = EquipmentArmsModel.builder()
				.msId(Entity.getMsId())
				.armsId(Entity.getArmsId())
				.armsName(Entity.getArmsName())
				.numberEquipment(String.valueOf(Entity.getNumberEquipment()))
				.detail(Entity.getDetail())
				.build();
		
		return result;
	}

	private MobileSuitDetailModel toJoinResult(MobileSuitEntity msEntity, List<EquipmentArmsEntity> equipmentArmsEntityList) {
		
		MobileSuitDetailModel msDetailResult = MobileSuitDetailModel.builder()
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
