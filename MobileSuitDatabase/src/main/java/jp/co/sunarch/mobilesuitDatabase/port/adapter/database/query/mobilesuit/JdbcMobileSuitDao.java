package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.DomaMobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery.Criteria;

@ConfigAutowireable
@Dao
public interface JdbcMobileSuitDao {
	@Select
	public List<DomaMobileSuitEntity> selectAll();

	@Select
	public Optional<DomaMobileSuitEntity> selectById(String msId);

	@Select(ensureResult = true)
	public DomaMobileSuitEntity selectDetailById(String msId);

	@Select
	public List<DomaMobileSuitEntity> selectByCriteria(Criteria criteria);

}
