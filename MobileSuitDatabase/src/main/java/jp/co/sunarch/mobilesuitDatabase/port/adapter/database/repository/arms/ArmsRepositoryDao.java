package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms.entity.ArmsEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArmsRepositoryDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ArmsEntity selectArmsById(String armsId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("armsId", armsId);
		RowMapper<ArmsEntity> mapper =
				new BeanPropertyRowMapper<ArmsEntity>(ArmsEntity.class);

		try {
			return namedParameterJdbcTemplate.queryForObject(ArmsSqlCode.SELECT_ARMS_QUERY_BY_ID, params, mapper);
		} catch(Exception e) {
			return null;
		}

	}

	public int insert(ArmsEntity armsEntity) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("armsId", armsEntity.getArmsId())
				.addValue("armsName", armsEntity.getArmsName())
				.addValue("detail", armsEntity.getDetail());

		return namedParameterJdbcTemplate.update(ArmsSqlCode.INSERT_ARMS, params);
	}

	public int update(ArmsEntity armsEntity) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("armsId", armsEntity.getArmsId())
				.addValue("armsName", armsEntity.getArmsName())
				.addValue("detail", armsEntity.getDetail());

		return namedParameterJdbcTemplate.update(ArmsSqlCode.UPDATE_ARMS, params);
	}

	public int deleteById(String armsId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("armsId", armsId);

		return namedParameterJdbcTemplate.update(ArmsSqlCode.DELETE_ARMS_BY_ID, params);
	}
}
