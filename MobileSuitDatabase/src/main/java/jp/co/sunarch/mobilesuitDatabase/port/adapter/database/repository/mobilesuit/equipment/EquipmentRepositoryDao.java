package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment.entity.EquipmentEntity;
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

	public List<EquipmentEntity> selectByArmsId(String armsId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("armsId", armsId);
		RowMapper<EquipmentEntity> mapper = new BeanPropertyRowMapper<EquipmentEntity>(EquipmentEntity.class);

		return namedParameterJdbcTemplate.query(EquipmentSqlCode.SELECT_EQUIPMENT_BY_ARMSID, params, mapper);
	}

	public EquipmentEntity selectByMsIdAndArmsId(String msId, String armsId) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", msId)
				.addValue("armsId", armsId);
		RowMapper<EquipmentEntity> mapper = new BeanPropertyRowMapper<EquipmentEntity>(EquipmentEntity.class);

		try {
			return namedParameterJdbcTemplate.queryForObject(
					EquipmentSqlCode.SELECT_EQUIPMENT_BY_MSID_AND_ARMSID,
					params,
					mapper);
		} catch (Exception e) {
			return null;
		}

	}

	public int insert(EquipmentEntity equipmentEntity) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", equipmentEntity.getMsId())
				.addValue("armsId", equipmentEntity.getArmsId())
				.addValue("numberEquipment", equipmentEntity.getNumberEquipment())
				.addValue("detail", equipmentEntity.getDetail());

		return namedParameterJdbcTemplate.update(EquipmentSqlCode.INSERT_EQUIPMENT, params);
	}

	public int deleteByMsId(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);

		return namedParameterJdbcTemplate.update(EquipmentSqlCode.DELETE_EQUIPMENT_BY_MSID, params);
	}

	public int deleteByArmsId(String armsId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("armsId", armsId);

		return namedParameterJdbcTemplate.update(EquipmentSqlCode.DELETE_EQUIPMENT_BY_ARMSID, params);
	}
}
