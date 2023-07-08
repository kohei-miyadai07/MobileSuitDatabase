package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.DomaArmsEntity;

@ConfigAutowireable
@Dao
public interface DomaArmsDao {
	@Select
	Optional<DomaArmsEntity> selectById(String armsId);

	@Insert
	int insert(DomaArmsEntity entity);

	@Update
	int update(DomaArmsEntity entity);

	@Delete(sqlFile = true)
	int deleteById(String armsId);

}
