package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.DomaEquipmentArmsEntity;

@ConfigAutowireable
@Dao
public interface JdbcEquipmentDao {
	@Select
	public List<DomaEquipmentArmsEntity> selectEquipmentArmsByMsId(String msId);

}
