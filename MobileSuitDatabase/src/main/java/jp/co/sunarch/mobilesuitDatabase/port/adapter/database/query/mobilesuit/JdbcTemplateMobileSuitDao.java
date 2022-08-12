package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JdbcTemplateMobileSuitDao {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<MobileSuitEntity> selectMobileSuitList() {

		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);
		
		return namedParameterJdbcTemplate.query(MobileSuitSqlCode.GET_MOBILESUIT_LIST_QUERY, params, mapper);
	}

}
