package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.jdbc.query.mobilesuit;

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
public class MobileSuitQueryDao {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	final String GET_MOBILESUIT_QUERY = """
			select
			ms_id
			,model_number
			,ms_name
			,insert_date
			,update_date
			,version
			from
			MobileSuit
			order by
			ms_name
			,ms_id
			""";
	
	public List<MobileSuitEntity> getMobileSuits() {
		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitEntity> mapper = new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);

		return namedParameterJdbcTemplate.query(GET_MOBILESUIT_QUERY, params, mapper);

	}

}
