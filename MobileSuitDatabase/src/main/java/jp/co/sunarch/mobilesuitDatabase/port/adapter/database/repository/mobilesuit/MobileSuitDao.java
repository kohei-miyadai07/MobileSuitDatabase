package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;

@ConfigAutowireable
@Dao
public interface MobileSuitDao {
	@Select
	Optional<MobileSuitEntity> selectById(String msId);

	@Insert
	int insert(MobileSuitEntity entity);

	@Update
	int update(MobileSuitEntity entity);

	@Delete(sqlFile = true)
	int deleteById(String msId);

}
