package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.NoResultException;
import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.DomaMobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.JdbcEquipmentDao;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.DomaEquipmentArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentArmsModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitQueryImpl implements MobileSuitQuery {

	private final JdbcMobileSuitDao jdbcMobileSuitDao;

	private final JdbcEquipmentDao jdbcEquipmentDao;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private NumberFormat comFormat = NumberFormat.getNumberInstance();

	@Override
	public List<MobileSuitModel> getMobileSuitList() {
		List<DomaMobileSuitEntity> msEntityList = jdbcMobileSuitDao.selectAll();
		List<MobileSuitModel> msResultList = msEntityList.stream().map(l -> toResult(l)).toList();

		return msResultList;

	}

	@Override
	public MobileSuitDetailModel getMobileSuitDetail(String msId) {
		DomaMobileSuitEntity msEntity = null;

		try {
			msEntity = jdbcMobileSuitDao.selectDetailById(msId);
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		List<DomaEquipmentArmsEntity> equipmentArmsEntityList = jdbcEquipmentDao.selectEquipmentArmsByMsId(msId);

		return toJoinResult(msEntity, equipmentArmsEntityList);
	}

	@Override
	public MobileSuitModel getMobileSuitById(String msId) {
		Optional<DomaMobileSuitEntity> msEntityOpt = jdbcMobileSuitDao.selectById(msId);

		MobileSuitModel msModel = MobileSuitModel.builder()
				.msId("")
				.modelNumber("")
				.msName("")
				.msUrl("")
				.headHeight("")
				.overallHeight("")
				.weight("")
				.totalWeight("")
				.powerSource("")
				.material("")
				.effectiveSensorRadius("")
				.generatorOutput("")
				.totalThrustersOutput("")
				.msOverview("")
				.action("")
				.insertDate("")
				.updateDate("")
				.version("")
				.build();

		if (msEntityOpt.isPresent()) {
			msModel = toResult(msEntityOpt.get());
		}

		return msModel;
	}

	@Override
	public List<MobileSuitModel> searchMobileSuit(Criteria criteria) {
		List<DomaMobileSuitEntity> msEntityList = jdbcMobileSuitDao.selectByCriteria(criteria);
		List<MobileSuitModel> msModelList = msEntityList.stream().map(l -> toResult(l)).toList();

		return msModelList;
	}

	private MobileSuitModel toResult(DomaMobileSuitEntity msEntity) {

		MobileSuitModel msResult = MobileSuitModel.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.msUrl(msEntity.getMsUrl())
				.headHeight(msEntity.getHeadHeight().toPlainString())
				.overallHeight(msEntity.getOverallHeight().toPlainString())
				.weight(msEntity.getWeight().toString())
				.totalWeight(msEntity.getTotalWeight().toString())
				.powerSource(msEntity.getPowerSource())
				.material(msEntity.getMaterial())
				.effectiveSensorRadius(String.valueOf(msEntity.getEffectiveSensorRadius()))
				.generatorOutput(String.valueOf(msEntity.getGeneratorOutput()))
				.totalThrustersOutput(String.valueOf(msEntity.getTotalThrustersOutput()))
				.msOverview(msEntity.getMsOverview())
				.action(msEntity.getAction())
				.insertDate(sdf.format(msEntity.getInsertDate()))
				.updateDate(sdf.format(msEntity.getUpdateDate()))
				.version(String.valueOf(msEntity.getVersion()))
				.build();

		return msResult;
	}

	private EquipmentArmsModel toEquipmentResult(DomaEquipmentArmsEntity Entity) {

		EquipmentArmsModel result = EquipmentArmsModel.builder()
				.msId(Entity.getMsId())
				.armsId(Entity.getArmsId())
				.armsName(Entity.getArmsName())
				.numberEquipment(String.valueOf(Entity.getNumberEquipment()))
				.detail(Entity.getDetail())
				.build();

		return result;
	}

	private MobileSuitDetailModel toJoinResult(DomaMobileSuitEntity msEntity, List<DomaEquipmentArmsEntity> equipmentArmsEntityList) {

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
				.version(String.valueOf(msEntity.getVersion()))
				.equipmentArmsResultList(equipmentArmsEntityList.stream().map(l -> toEquipmentResult(l)).toList())
				.build();

		return msDetailResult;
	}

}
