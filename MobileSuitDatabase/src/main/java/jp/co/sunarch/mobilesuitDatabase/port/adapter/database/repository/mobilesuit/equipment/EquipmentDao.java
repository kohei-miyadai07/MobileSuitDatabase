package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;

@ConfigAutowireable
@Dao
public interface EquipmentDao {
	@Select
	Optional<EquipmentEntity> selectByMsIdAndArmsId(String msId, String armsId);

	@Insert
	int insert(EquipmentEntity entity);

	@Update
	int update(EquipmentEntity entity);

	@Delete(sqlFile = true)
	int deleteByMsIdAndArmsId(String msId, String armsId);

}
