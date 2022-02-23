package jp.co.sunarch.mobilesuitDatabase.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitDetailEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitsEntity;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MobileSuitDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	final String SEARCH_MOBILESUITS_QUERY = """
			select
			   ms_id
			  ,model_number
			  ,ms_name
			  ,insert_date
			  ,update_date
			 from
			  MobileSuit
			 order by
			   ms_name
			  ,ms_id
			""";
	
	final String SEARCH_MOBILESUIT_DETAIL_QUERY = """
			select
               * 
             from
              MobileSuit m 
             where
              m.ms_id = :msId
			""";
	
	final String SEARCH_MOBILESUIT_EQUIPMENT_QUERY = """
			select
			   e.equipment_id
			  ,e.ms_id
			  ,e.armed_id
			  ,e.number_equipment
              ,a.armed_name
              ,a.armed_explanation
             from
              Equipment e 
              inner join Armed a
              on e.armed_id = a.armed_id
             where
              e.ms_id = :msId
			""";
	
	public List<MobileSuitsEntity> searchMobileSuits() {

		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitsEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitsEntity>(MobileSuitsEntity.class);

		return namedParameterJdbcTemplate.query(SEARCH_MOBILESUITS_QUERY, params, mapper);
	}
	
	public MobileSuitDetailEntity searchMobileSuitDetail(String id) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", id);
		RowMapper<MobileSuitDetailEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitDetailEntity>(MobileSuitDetailEntity.class);
		
		return namedParameterJdbcTemplate.queryForObject(
				SEARCH_MOBILESUIT_DETAIL_QUERY, params, mapper);
	}
	
	public List<MobileSuitEquipmentEntity> searchMobileSuitEquipmentList(String id) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", id);
		RowMapper<MobileSuitEquipmentEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEquipmentEntity>(MobileSuitEquipmentEntity.class);
		
		return namedParameterJdbcTemplate.query(SEARCH_MOBILESUIT_EQUIPMENT_QUERY, params, mapper);
	}

}
