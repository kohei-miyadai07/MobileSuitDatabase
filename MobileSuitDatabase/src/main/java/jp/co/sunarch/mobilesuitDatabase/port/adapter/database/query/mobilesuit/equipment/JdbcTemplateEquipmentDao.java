package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment.entity.EquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery.Criteria;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JdbcTemplateEquipmentDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//	public List<EquipmentArmsEntity> selectEquipmentArmsByMsId(String msId) {
//		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
//		RowMapper<EquipmentArmsEntity> mapper = 
//				new BeanPropertyRowMapper<EquipmentArmsEntity>(EquipmentArmsEntity.class);
//
//		return namedParameterJdbcTemplate.query(EquipmentSqlCode.SELECT_EQUIPMENT_ARMS_BY_MSID_QUERY, params, mapper);
//	}

	public List<EquipmentEntity> selectEquipmentList() {
		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<EquipmentEntity> mapper = 
				new BeanPropertyRowMapper<EquipmentEntity>(EquipmentEntity.class);

		return namedParameterJdbcTemplate.query(EquipmentSqlCode.SELECT_EQUIPMENT_LIST, params, mapper);
	}

	public EquipmentEntity selectEquipmentByMsIdAndArmsId(String msId, String armsId) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", msId)
				.addValue("armsId", armsId);
		RowMapper<EquipmentEntity> mapper =
				new BeanPropertyRowMapper<EquipmentEntity>(EquipmentEntity.class);

		return namedParameterJdbcTemplate.queryForObject(EquipmentSqlCode.SELECT_EQUIPMENT_QUERY_BY_MSID_AND_ARMSID, params, mapper);
	}

	public List<EquipmentEntity> selectEquipmentByCriteria(Criteria criteria) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(EquipmentSqlCode.SELECT_EQUIPMENT_QUERY_BASE);

		MapSqlParameterSource params = new MapSqlParameterSource();
		RowMapper<EquipmentEntity> mapper =
				new BeanPropertyRowMapper<EquipmentEntity>(EquipmentEntity.class);

		boolean andFlg = false;

		if(criteria.getMsName() != null && criteria.getMsName() != "") {
			if(!andFlg) {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("m.ms_name = :msName");
			params.addValue("msName", criteria.getMsName());
			andFlg = true;
		}

		if(criteria.getArmsName() != null && criteria.getArmsName() != "") {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("a.arms_name = :armsName");
			params.addValue("armsName", criteria.getArmsName());
			andFlg = true;
		}

		return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, mapper);
	}

}
