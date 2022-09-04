package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment.entity.EquipmentEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EquipmentRepositoryDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<EquipmentEntity> selectByMsId(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		RowMapper<EquipmentEntity> mapper = 
				new BeanPropertyRowMapper<EquipmentEntity>(EquipmentEntity.class);

		return namedParameterJdbcTemplate.query(EquipmentSqlCode.SELECT_EQUIPMENT_BY_MSID, params, mapper);
	}

	public int deleteByMsId(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);

		return namedParameterJdbcTemplate.update(EquipmentSqlCode.DELETE_EQUIPMENT_BY_MSID, params);
	}
}
