package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.DomaEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EquipmentQueryImpl implements EquipmentQuery {

	private final JdbcEquipmentDao jdbcEquipmentDao;

	@Override
	public List<EquipmentModel> getEquipmentList() {
		List<DomaEquipmentEntity> equipmentEntities = jdbcEquipmentDao.selectAll();
		List<EquipmentModel> equipmentModels = equipmentEntities.stream().map(e -> toDomaModel(e)).toList();

		return equipmentModels;

	}

	@Override
	public EquipmentModel getEquipmentByMsIdAndArmsId(String msId, String armsId) {
		Optional<DomaEquipmentEntity> equipmentEntityOpt = jdbcEquipmentDao.selectByMsIdAndArmsId(msId, armsId);

		EquipmentModel equipmentModel = EquipmentModel.builder()
				.msId("")
				.msName("")
				.armsId("")
				.armsName("")
				.numberEquipment("0")
				.detail("")
				.build();

		if (equipmentEntityOpt.isPresent()) {
			equipmentModel = toDomaModel(equipmentEntityOpt.get());
		}

		return equipmentModel;

	}

	@Override
	public List<EquipmentModel> searchEquipment(Criteria criteria) {
		List<DomaEquipmentEntity> equipmentEntities = jdbcEquipmentDao.selectByCriteria(criteria);
		List<EquipmentModel> equipmentModels = equipmentEntities.stream().map(e -> toDomaModel(e)).toList();

		return equipmentModels;

	}

	private EquipmentModel toDomaModel(DomaEquipmentEntity entity) {
		return EquipmentModel.builder()
				.msId(entity.getMsId())
				.msName(entity.getMsName())
				.armsId(entity.getArmsId())
				.armsName(entity.getArmsName())
				.numberEquipment(String.valueOf(entity.getNumberEquipment()))
				.detail(entity.getDetail())
				.build();

	}

}
