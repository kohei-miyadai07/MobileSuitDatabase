package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.common.utils.CommonItemSettings;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.mobilesuit.equipment.EquipmentCountModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.equipment.EquipmentQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EquipmentQueryImpl implements EquipmentQuery {

	private final JdbcEquipmentDao jdbcEquipmentDao;

	@Override
	public List<EquipmentModel> getEquipmentList() {
		List<EquipmentEntity> equipmentEntities = jdbcEquipmentDao.selectAll();
		List<EquipmentModel> equipmentModels = equipmentEntities.stream().map(e -> toModel(e)).toList();

		return equipmentModels;
	}

	@Override
	public EquipmentModel getEquipmentByMsIdAndArmsId(String msId, String armsId) {
		Optional<EquipmentEntity> equipmentEntityOpt = jdbcEquipmentDao.selectByMsIdAndArmsId(msId, armsId);

		EquipmentModel equipmentModel = EquipmentModel.builder()
				.msId("")
				.msName("")
				.armsId("")
				.armsName("")
				.numberEquipment("0")
				.detail("")
				.insertDate(null)
				.updateDate(null)
				.version("")
				.build();

		if (equipmentEntityOpt.isPresent()) {
			equipmentModel = toModel(equipmentEntityOpt.get());
		}

		return equipmentModel;
	}

	@Override
	public List<EquipmentModel> searchEquipment(Criteria criteria) {
		List<EquipmentEntity> equipmentEntities = jdbcEquipmentDao.selectByCriteria(criteria);
		List<EquipmentModel> equipmentModels = equipmentEntities.stream().map(e -> toModel(e)).toList();

		return equipmentModels;
	}

	@Override
	public EquipmentCountModel getEquipmentCount() {
		return new EquipmentCountModel(jdbcEquipmentDao.selectAllCount());
	}

	private EquipmentModel toModel(EquipmentEntity entity) {
		return EquipmentModel.builder()
				.msId(entity.getMsId())
				.msName(entity.getMsName())
				.armsId(entity.getArmsId())
				.armsName(entity.getArmsName())
				.numberEquipment(CommonItemSettings.convertIntegerToString(entity.getNumberEquipment()))
				.detail(entity.getDetail())
				.insertDate(entity.getInsertDate().toInstant())
				.updateDate(entity.getUpdateDate().toInstant())
				.version(String.valueOf(entity.getVersion()))
				.build();
	}
}
