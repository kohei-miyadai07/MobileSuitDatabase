package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.equipment;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.equipment.entity.EquipmentArmsEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JdbcTemplateEquipmentDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<EquipmentArmsEntity> selectEquipmentArmsByMsId(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		RowMapper<EquipmentArmsEntity> mapper = 
				new BeanPropertyRowMapper<EquipmentArmsEntity>(EquipmentArmsEntity.class);

		return namedParameterJdbcTemplate.query(EquipmentSqlCode.SELECT_EQUIPMENT_ARMS_BY_MSID_QUERY, params, mapper);
	}

}
