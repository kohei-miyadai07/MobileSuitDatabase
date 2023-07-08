package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.DomaEquipmentArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.DomaEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery.Criteria;

@ConfigAutowireable
@Dao
public interface JdbcEquipmentDao {
	@Select
	public List<DomaEquipmentEntity> selectAll();

	@Select
	public Optional<DomaEquipmentEntity> selectByMsIdAndArmsId(String msId, String armsId);

	@Select
	public List<DomaEquipmentArmsEntity> selectEquipmentArmsByMsId(String msId);

	@Select
	public List<DomaEquipmentEntity> selectByCriteria(Criteria criteria);

}
