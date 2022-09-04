package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.entity.MobileSuitEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MobileSuitRepositoryDao {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public MobileSuitEntity selectMobileSuitById(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		RowMapper<MobileSuitEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);

		try {
		    return namedParameterJdbcTemplate.queryForObject(MobileSuitSqlCode.SELECT_MOBILESUIT_QUERY_BY_ID, params, mapper);
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public int insert(MobileSuitEntity msEntity) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", msEntity.getMsId())
				.addValue("modelNumber", msEntity.getModelNumber())
				.addValue("msName", msEntity.getMsName())
				.addValue("msUrl", msEntity.getMsUrl())
				.addValue("headHeight", msEntity.getHeadHeight())
				.addValue("overallHeight", msEntity.getOverallHeight())
				.addValue("weight", msEntity.getWeight())
				.addValue("totalWeight", msEntity.getTotalWeight())
				.addValue("powerSource", msEntity.getPowerSource())
				.addValue("material", msEntity.getMaterial())
				.addValue("effectiveSensorRadius", msEntity.getEffectiveSensorRadius())
				.addValue("generatorOutput", msEntity.getGeneratorOutput())
				.addValue("totalThrustersOutput", msEntity.getTotalThrustersOutput())
				.addValue("msOverview", msEntity.getMsOverview())
				.addValue("action", msEntity.getAction())
				.addValue("insertDate", msEntity.getInsertDate())
				.addValue("updateDate", msEntity.getUpdateDate())
				.addValue("version", msEntity.getVersion());

		return namedParameterJdbcTemplate.update(MobileSuitSqlCode.INSERT_MOBILESUIT, params);
	}

	public int update(MobileSuitEntity msEntity) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", msEntity.getMsId())
				.addValue("modelNumber", msEntity.getModelNumber())
				.addValue("msName", msEntity.getMsName())
				.addValue("msUrl", msEntity.getMsUrl())
				.addValue("headHeight", msEntity.getHeadHeight())
				.addValue("overallHeight", msEntity.getOverallHeight())
				.addValue("weight", msEntity.getWeight())
				.addValue("totalWeight", msEntity.getTotalWeight())
				.addValue("powerSource", msEntity.getPowerSource())
				.addValue("material", msEntity.getMaterial())
				.addValue("effectiveSensorRadius", msEntity.getEffectiveSensorRadius())
				.addValue("generatorOutput", msEntity.getGeneratorOutput())
				.addValue("totalThrustersOutput", msEntity.getTotalThrustersOutput())
				.addValue("msOverview", msEntity.getMsOverview())
				.addValue("action", msEntity.getAction())
				.addValue("insertDate", msEntity.getInsertDate())
				.addValue("updateDate", msEntity.getUpdateDate())
				.addValue("version", msEntity.getVersion());

		return namedParameterJdbcTemplate.update(MobileSuitSqlCode.UPDATE_MOBILESUIT, params);
	}

	public int deleteById(String msId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);

		return namedParameterJdbcTemplate.update(MobileSuitSqlCode.DELETE_MOBILESUIT_BY_ID, params);
	}

}
