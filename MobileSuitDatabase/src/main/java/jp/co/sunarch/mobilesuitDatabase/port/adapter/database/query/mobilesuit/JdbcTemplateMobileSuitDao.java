package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.entity.MobileSuitIdEntity;
import lombok.RequiredArgsConstructor;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery.Criteria;

@Component
@RequiredArgsConstructor
public class JdbcTemplateMobileSuitDao {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<MobileSuitEntity> selectMobileSuitList() {

		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);
		
		return namedParameterJdbcTemplate.query(MobileSuitSqlCode.SELECT_MOBILESUIT_LIST_QUERY, params, mapper);
	}

	public MobileSuitEntity selectMobileSuitById(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		RowMapper<MobileSuitEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);
		
		return namedParameterJdbcTemplate.queryForObject(MobileSuitSqlCode.SELECT_MOBILESUIT_QUERY_BY_ID, params, mapper);
	}

	public MobileSuitIdEntity selectMobileSuitIdById(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		RowMapper<MobileSuitIdEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitIdEntity>(MobileSuitIdEntity.class);

		return namedParameterJdbcTemplate.queryForObject(MobileSuitSqlCode.SELECT_MOBILESUIT_ID_QUERY_BY_ID, params, mapper);
	}

	public List<MobileSuitEntity> selectMobileSuitByCriteria(Criteria criteria) {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(MobileSuitSqlCode.SELECT_MOBILESULT_QUERY_BASE);

		MapSqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);

		boolean andFlg = false;

		if(criteria.getModelNumber() != null && criteria.getModelNumber() != "") {
			if(!andFlg) {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("model_number = :modelNumber");
			params.addValue("modelNumber", criteria.getModelNumber());
			andFlg = true;
		}

		if(criteria.getMsName() != null && criteria.getMsName() != "") {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("ms_name = :msName");
			params.addValue("msName", criteria.getMsName());
			andFlg = true;
		}

		if(!criteria.getHeadHeightFrom().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("head_height >= :headHeightFrom");
			params.addValue("headHeightFrom", criteria.getHeadHeightFrom());
			andFlg = true;
		}
		
		if(!criteria.getHeadHeightTo().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("head_height <= :headHeightTo");
			params.addValue("headHeightTo", criteria.getHeadHeightTo());
			andFlg = true;
		}

		if(!criteria.getOverallHeightFrom().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("overall_height >= :overallHeightFrom");
			params.addValue("overallHeightFrom", criteria.getOverallHeightFrom());
			andFlg = true;
		}
		
		if(!criteria.getOverallHeightTo().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("overall_height <= :overallHeightTo");
			params.addValue("overallHeightTo", criteria.getOverallHeightTo());
			andFlg = true;
		}

		if(!criteria.getWeightFrom().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("weight >= :weightFrom");
			params.addValue("weightFrom", criteria.getWeightFrom());
			andFlg = true;
		}
		
		if(!criteria.getWeightTo().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("weight <= :weightTo");
			params.addValue("weightTo", criteria.getWeightTo());
			andFlg = true;
		}

		if(!criteria.getTotalWeightFrom().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("total_weight >= :totalWeightFrom");
			params.addValue("totalWeightFrom", criteria.getTotalWeightFrom());
			andFlg = true;
		}
		
		if(!criteria.getTotalWeightTo().equals(new BigDecimal("0"))) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("total_weight <= :totalWeightTo");
			params.addValue("totalWeightTo", criteria.getTotalWeightTo());
			andFlg = true;
		}

		if(criteria.getPowerSource() != null && criteria.getPowerSource() != "") {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("power_source = :powerSource");
			params.addValue("powerSource", criteria.getPowerSource());
			andFlg = true;
		}
		
		if(criteria.getMaterial() != null && criteria.getMaterial() != "") {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("material = material");
			params.addValue("material", criteria.getMaterial());
			andFlg = true;
		}

		if(criteria.getEffectiveSensorRadiusFrom() > 0) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("effective_sensor_radius >= :effectiveSensorRadiusFrom");
			params.addValue("effectiveSensorRadiusFrom", criteria.getEffectiveSensorRadiusFrom());
			andFlg = true;
		}
		
		if(criteria.getEffectiveSensorRadiusTo() > 0) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("effective_sensor_radius <= :effectiveSensorRadiusTo");
			params.addValue("effectiveSensorRadiusTo", criteria.getEffectiveSensorRadiusTo());
			andFlg = true;
		}

		if(criteria.getGeneratorOutputFrom() > 0) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("generator_output >= :generatorOutputFrom");
			params.addValue("generatorOutputFrom", criteria.getGeneratorOutputFrom());
			andFlg = true;
		}
		
		if(criteria.getGeneratorOutputTo() > 0) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("generator_output <= :generatorOutputTo");
			params.addValue("generatorOutputTo", criteria.getGeneratorOutputTo());
			andFlg = true;
		}

		if(criteria.getTotalThrustersOutputFrom() > 0) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("total_thrusters_output <= :totalThrustersOutputFrom");
			params.addValue("totalThrustersOutputFrom", criteria.getTotalThrustersOutputFrom());
			andFlg = true;
		}
		
		if(criteria.getTotalThrustersOutputTo() > 0) {
			if(andFlg) {
				sqlBuilder.append(" and ");
			} else {
				sqlBuilder.append(" where ");
			}
			sqlBuilder.append("total_thrusters_output >= :totalThrustersOutputTo");
			params.addValue("totalThrustersOutputTo", criteria.getTotalThrustersOutputTo());
			andFlg = true;
		}

		return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, mapper);
	}

}
