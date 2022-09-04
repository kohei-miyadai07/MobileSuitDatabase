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

}
