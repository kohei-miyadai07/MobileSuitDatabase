package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery.Criteria;

@ConfigAutowireable
@Dao
public interface JdbcEquipmentDao {
	@Select
	public List<EquipmentEntity> selectAll();

	@Select
	public Optional<EquipmentEntity> selectByMsIdAndArmsId(String msId, String armsId);

	@Select
	public List<EquipmentArmsEntity> selectEquipmentArmsByMsId(String msId);

	@Select
	public List<EquipmentEntity> selectByCriteria(Criteria criteria);

}
