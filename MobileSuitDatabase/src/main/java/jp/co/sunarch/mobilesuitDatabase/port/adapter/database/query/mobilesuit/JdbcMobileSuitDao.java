package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery.Criteria;

@ConfigAutowireable
@Dao
public interface JdbcMobileSuitDao {
	@Select
	public List<MobileSuitEntity> selectAll();

	@Select
	public Optional<MobileSuitEntity> selectById(String msId);

	@Select(ensureResult = true)
	public MobileSuitEntity selectDetailById(String msId);

	@Select
	public List<MobileSuitEntity> selectByCriteria(Criteria criteria);

}
