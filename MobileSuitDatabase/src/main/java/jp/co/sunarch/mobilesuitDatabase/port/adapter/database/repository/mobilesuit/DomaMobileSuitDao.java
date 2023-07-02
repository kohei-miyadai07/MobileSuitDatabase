package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.DomaMobileSuitEntity;

@ConfigAutowireable
@Dao
public interface DomaMobileSuitDao {
	@Select
	Optional<DomaMobileSuitEntity> selectById(String msId);

	@Insert
	int insert(DomaMobileSuitEntity entity);

	@Update
	int update(DomaMobileSuitEntity entity);

	@Delete(sqlFile = true)
	int deleteById(String msId);

}
