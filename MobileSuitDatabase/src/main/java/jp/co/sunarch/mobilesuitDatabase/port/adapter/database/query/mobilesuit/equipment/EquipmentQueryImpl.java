package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EquipmentQueryImpl implements EquipmentQuery {
	
	private final JdbcTemplateEquipmentDao equipmentDao;

	@Override
	public List<EquipmentModel> getEquipmentList() {
		List<EquipmentEntity> equipmentEntityList = equipmentDao.selectEquipmentList();
		List<EquipmentModel> equipmentModelList = equipmentEntityList.stream().map(e -> toModel(e)).toList();

		return equipmentModelList;
	}

	private EquipmentModel toModel(EquipmentEntity entity) {
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
