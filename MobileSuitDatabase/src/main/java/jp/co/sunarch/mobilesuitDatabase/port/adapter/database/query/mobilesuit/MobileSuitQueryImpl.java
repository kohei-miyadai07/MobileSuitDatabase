package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.NoResultException;
import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.mobilesuit.MobileSuitCountModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.JdbcEquipmentDao;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentArmsModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitQueryImpl implements MobileSuitQuery {

	private final JdbcMobileSuitDao jdbcMobileSuitDao;

	private final JdbcEquipmentDao jdbcEquipmentDao;

	@Override
	public List<MobileSuitModel> getMobileSuitList() {
		List<MobileSuitEntity> msEntities = jdbcMobileSuitDao.selectAll();
		List<MobileSuitModel> msModels = msEntities.stream().map(l -> toResult(l)).toList();

		return msModels;

	}

	@Override
	public MobileSuitDetailModel getMobileSuitDetail(String msId) {
		MobileSuitEntity msEntity = null;

		try {
			msEntity = jdbcMobileSuitDao.selectDetailById(msId);
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		List<EquipmentArmsEntity> equipmentArmsEntities = jdbcEquipmentDao.selectEquipmentArmsByMsId(msId);

		return toJoinResult(msEntity, equipmentArmsEntities);
	}

	@Override
	public MobileSuitModel getMobileSuitById(String msId) {
		Optional<MobileSuitEntity> msEntityOpt = jdbcMobileSuitDao.selectById(msId);

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
				.insertDate(Instant.ofEpochSecond(0))
				.updateDate(Instant.ofEpochSecond(0))
				.version("")
				.build();

		if (msEntityOpt.isPresent()) {
			msModel = toResult(msEntityOpt.get());
		}

		return msModel;
	}

	@Override
	public List<MobileSuitModel> searchMobileSuit(Criteria criteria) {
		List<MobileSuitEntity> msEntities = jdbcMobileSuitDao.selectByCriteria(criteria);
		List<MobileSuitModel> msModels = msEntities.stream().map(l -> toResult(l)).toList();

		return msModels;
	}

	@Override
	public MobileSuitCountModel getMobileSuitCount() {
		return new MobileSuitCountModel(jdbcMobileSuitDao.selectAllCount());
	}

	private MobileSuitModel toResult(MobileSuitEntity msEntity) {

		MobileSuitModel msModel = MobileSuitModel.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.msUrl(msEntity.getMsUrl())
				.headHeight(CommonItemSettings.convertBigDecimalToString(msEntity.getHeadHeight()))
				.overallHeight(CommonItemSettings.convertBigDecimalToString(msEntity.getOverallHeight()))
				.weight(CommonItemSettings.convertBigDecimalToString(msEntity.getWeight()))
				.totalWeight(CommonItemSettings.convertBigDecimalToString(msEntity.getTotalWeight()))
				.powerSource(msEntity.getPowerSource())
				.material(msEntity.getMaterial())
				.effectiveSensorRadius(CommonItemSettings.convertLongToString(msEntity.getEffectiveSensorRadius()))
				.generatorOutput(CommonItemSettings.convertLongToString(msEntity.getGeneratorOutput()))
				.totalThrustersOutput(CommonItemSettings.convertLongToString(msEntity.getTotalThrustersOutput()))
				.msOverview(msEntity.getMsOverview())
				.action(msEntity.getAction())
				.insertDate(msEntity.getInsertDate().toInstant())
				.updateDate(msEntity.getUpdateDate().toInstant())
				.version(String.valueOf(msEntity.getVersion()))
				.build();

		return msModel;
	}

	private EquipmentArmsModel toEquipmentResult(EquipmentArmsEntity equipmentArmsEntity) {

		EquipmentArmsModel equipmentArmsModel = EquipmentArmsModel.builder()
				.msId(equipmentArmsEntity.getMsId())
				.armsId(equipmentArmsEntity.getArmsId())
				.armsName(equipmentArmsEntity.getArmsName())
				.numberEquipment(CommonItemSettings.convertIntegerToString(equipmentArmsEntity.getNumberEquipment()))
				.detail(equipmentArmsEntity.getDetail())
				.insertDate(equipmentArmsEntity.getInsertDate().toInstant())
				.updateDate(equipmentArmsEntity.getUpdateDate().toInstant())
				.version(String.valueOf(equipmentArmsEntity.getVersion()))
				.build();

		return equipmentArmsModel;
	}

	private MobileSuitDetailModel toJoinResult(MobileSuitEntity msEntity, List<EquipmentArmsEntity> equipmentArmsEntities) {

		MobileSuitDetailModel msDetailModel = MobileSuitDetailModel.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.msUrl(msEntity.getMsUrl())
				.headHeight(CommonItemSettings.convertBigDecimalToString(msEntity.getHeadHeight()))
				.overallHeight(CommonItemSettings.convertBigDecimalToString(msEntity.getOverallHeight()))
				.weight(CommonItemSettings.convertBigDecimalToString(msEntity.getWeight()))
				.totalWeight(CommonItemSettings.convertBigDecimalToString(msEntity.getTotalWeight()))
				.powerSource(msEntity.getPowerSource())
				.material(msEntity.getMaterial())
				.effectiveSensorRadius(CommonItemSettings.convertLongToString(msEntity.getEffectiveSensorRadius()))
				.generatorOutput(CommonItemSettings.convertLongToString(msEntity.getGeneratorOutput()))
				.totalThrustersOutput(CommonItemSettings.convertLongToString(msEntity.getTotalThrustersOutput()))
				.msOverview(msEntity.getMsOverview())
				.action(msEntity.getAction())
				.insertDate(msEntity.getInsertDate().toInstant())
				.updateDate(msEntity.getUpdateDate().toInstant())
				.version(String.valueOf(msEntity.getVersion()))
				.equipmentArmsResultList(equipmentArmsEntities.stream().map(l -> toEquipmentResult(l)).toList())
				.build();

		return msDetailModel;
	}
}
