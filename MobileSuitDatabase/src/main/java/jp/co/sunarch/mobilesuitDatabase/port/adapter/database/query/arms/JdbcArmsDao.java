package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms.entity.DomaArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery.Criteria;

@ConfigAutowireable
@Dao
public interface JdbcArmsDao {
	@Select
	public List<DomaArmsEntity> selectAll();

	@Select
	public Optional<DomaArmsEntity> selectById(String armsId);

	@Select
	public List<DomaArmsEntity> selectByCriteria(Criteria criteria);

}
