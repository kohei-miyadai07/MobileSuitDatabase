package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms.entity.ArmsEntity;
import lombok.RequiredArgsConstructor;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery.Criteria;

@Component
@RequiredArgsConstructor
public class ArmsQueryDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<ArmsEntity> selectArmsList() {
		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<ArmsEntity> mapper = 
				new BeanPropertyRowMapper<ArmsEntity>(ArmsEntity.class);

		return namedParameterJdbcTemplate.query(ArmsSqlCode.SELECT_ARMS_LIST, params, mapper);
	}

	public ArmsEntity selectArmsById(String armsId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("armsId", armsId);
		RowMapper<ArmsEntity> mapper = 
				new BeanPropertyRowMapper<ArmsEntity>(ArmsEntity.class);

		return namedParameterJdbcTemplate.queryForObject(ArmsSqlCode.SELECT_ARMS_QUERY_BY_ID, params, mapper);
	}

	public List<ArmsEntity> selectArmsByCriteria(Criteria criteria) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(ArmsSqlCode.SELECT_ARMS_QUERY_BASE);

		MapSqlParameterSource params = new MapSqlParameterSource();
		RowMapper<ArmsEntity> mapper = 
				new BeanPropertyRowMapper<ArmsEntity>(ArmsEntity.class);

		if (criteria.getArmsName() != null && criteria.getArmsName() != "") {
			sqlBuilder.append(" where ");
			sqlBuilder.append("""
					arms_name = :armsName
					""");
			params.addValue("armsName", criteria.getArmsName());
		}

		return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, mapper);
	}

}
