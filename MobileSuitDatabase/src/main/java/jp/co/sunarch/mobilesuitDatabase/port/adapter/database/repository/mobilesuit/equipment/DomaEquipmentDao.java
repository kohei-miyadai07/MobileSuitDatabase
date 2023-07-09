package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.DomaEquipmentEntity;

@ConfigAutowireable
@Dao
public interface DomaEquipmentDao {
	@Select
	Optional<DomaEquipmentEntity> selectByMsIdAndArmsId(String msId, String armsId);

	@Insert
	int insert(DomaEquipmentEntity entity);

	@Update
	int update(DomaEquipmentEntity entity);

	@Delete(sqlFile = true)
	int deleteByMsIdAndArmsId(String msId, String armsId);

}
