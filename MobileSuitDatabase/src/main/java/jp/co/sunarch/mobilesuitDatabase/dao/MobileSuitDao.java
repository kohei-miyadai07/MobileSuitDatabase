package jp.co.sunarch.mobilesuitDatabase.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitArmedEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitArmedInfoEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitDetailEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEquipmentDetailEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitInfoEntity;
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
	
	final String SEARCH_MOBILESUIT_DETEIL_EQUIPMENT_QUERY = """
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
	
	final String INSERT_MOBILESUIT_QUERY = """
			insert 
            into MobileSuit 
            values ( 
               :msId
              ,:modelNumber
              ,:msName
              ,:msUrl
              ,:headHeight
              ,:weight
              ,:totalWeight
              ,:powerSource
              ,:material
              ,:generatorOutput
              ,:totalThrustersOutput
              ,:msOverview
              ,:action
              ,CURRENT_TIMESTAMP
              ,CURRENT_TIMESTAMP
            );
			""";
	
	final String INSERT_MOBILESUIT_ARMED_QUERY = """
			insert 
            into Armed 
            values (:armedId, :armedName, :armedExplanation);
			""";
	
	final String SEARCH_MOBILESUIT_INFO_QUERY = """
			select
			   ms_id
			  ,ms_name
			 from
			  MobileSuit
			 order by
			   ms_name
			  ,ms_id
			""";
	
	final String SEARCH_MOBILESUIT_ARMED_INFO_QUERY = """
			select
			   armed_id
			  ,armed_name
			 from
			  Armed
			 order by
			   armed_name
			  ,armed_id
			""";
	
	final String INSERT_MOBILESUIT_EQUIPMENT_QUERY = """
			insert
			into Equipment
			values (:equipmentId, :msId, :armedId, :numberEquipment);
			""";
	
	final String SEARCH_MOBILESUIT_QUERY = """
			select
               * 
             from
              MobileSuit m 
             where
              m.ms_id = :msId
			""";
	
	final String UPDATE_MOBILESUIT_QUERY = """
			update MobileSuit 
            set
             model_number = :modelNumber
             , ms_name = :msName
             , ms_url = :msUrl
             , head_height = :headHeight
             , weight = :weight
             , total_weight = :totalWeight
             , power_source = :powerSource
             , material = :material
             , generator_output = :generatorOutput
             , total_thrusters_output = :totalThrustersOutput
             , ms_overview = :msOverview
             , action = :action
             , update_date = CURRENT_TIMESTAMP 
            where
              ms_id = :msId
			""";
	
	final String SEARCH_MOBILESUIT_ARMED_QUERY = """
			select
			   *
			 from
			  Armed a
			 where
			  a.armed_id = :armedId
			""";
	
	final String UPDATE_MOBILESUIT_ARMED_QUERY = """
			update Armed
			set
			 armed_name = :armedName
			 , armed_explanation = :armedExplanation
			where
			  armed_id = :armedId
			""";
	
	final String SEARCH_MOBILESUIT_EQUIPMENT_QUERY = """
			select
               e.equipment_id
             , e.ms_id
             , m.ms_name
             , e.armed_id
             , a.armed_name
             , e.number_equipment 
             from
              Equipment e 
              inner join MobileSuit m 
                on e.ms_id = m.ms_id 
              inner join Armed a 
                on e.armed_id = a.armed_id 
             where
                e.equipment_id = :equipmentId
			""";
	
	final String UPDATE_MOBILESUIT_EQUIPMENT_QUERY = """
			update Equipment 
            set
             number_equipment = :numberEquipment
            where
             equipment_id = :equipmentId
			""";
	
	final String DELETE_EQUIPMENT_FORMOBILESUIT_QUERY = """
			delete from Equipment where ms_id = :msId
			""";
	
	final String DELETE_MOBILESUIT_QUERY = """
			delete from MobileSuit where ms_id = :msId
			""";
	
	final String DELETE_EQUIPMENT_FORARMED_QUERY = """
			delete from Equipment where armed_id = :armedId
			""";
	
	final String DELETE_ARMED_QUERY = """
			delete from Armed where armed_id = :armedId
			""";
	
	final String DELETE_EQUIPMENT_QUERY = """
			delete from equipment where equipment_id = :equipmentId
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
	
	public List<MobileSuitEquipmentDetailEntity> searchMobileSuitEquipmentList(String id) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", id);
		RowMapper<MobileSuitEquipmentDetailEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEquipmentDetailEntity>(MobileSuitEquipmentDetailEntity.class);
		
		return namedParameterJdbcTemplate.query(SEARCH_MOBILESUIT_DETEIL_EQUIPMENT_QUERY, params, mapper);
	}

	public int insertOneMobileSuit(MobileSuitEntity msEntity) {
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", msEntity.getMsId())
				.addValue("modelNumber", msEntity.getModelNumber())
				.addValue("msName", msEntity.getMsName())
				.addValue("msUrl", msEntity.getMsUrl())
				.addValue("headHeight", msEntity.getHeadHeight())
				.addValue("weight", msEntity.getWeight())
				.addValue("totalWeight", msEntity.getTotalWeight())
				.addValue("powerSource", msEntity.getPowerSource())
				.addValue("material", msEntity.getMaterial())
				.addValue("generatorOutput", msEntity.getGeneratorOutput())
				.addValue("totalThrustersOutput", msEntity.getTotalThrustersOutput())
				.addValue("msOverview", msEntity.getMsOverview())
				.addValue("action", msEntity.getAction());
		
		return namedParameterJdbcTemplate.update(INSERT_MOBILESUIT_QUERY, params);
				
	}
	
	public int insertOneMobileSuitArmed(MobileSuitArmedEntity msArmedEntity) {
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("armedId", msArmedEntity.getArmedId())
				.addValue("armedName", msArmedEntity.getArmedName())
				.addValue("armedExplanation", msArmedEntity.getArmedExplanation());
		
		return namedParameterJdbcTemplate.update(INSERT_MOBILESUIT_ARMED_QUERY, params);
	}
	
	public List<MobileSuitInfoEntity> searchMobileSuitInfoList() {
		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitInfoEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitInfoEntity>(MobileSuitInfoEntity.class);
		
		return namedParameterJdbcTemplate.query(SEARCH_MOBILESUIT_INFO_QUERY, params, mapper);
	}
	
	public List<MobileSuitArmedInfoEntity> searchMobileSuitArmedInfoList() {
		SqlParameterSource params = new MapSqlParameterSource();
		RowMapper<MobileSuitArmedInfoEntity> mapper =
				new BeanPropertyRowMapper<MobileSuitArmedInfoEntity>(MobileSuitArmedInfoEntity.class);
		
		return namedParameterJdbcTemplate.query(SEARCH_MOBILESUIT_ARMED_INFO_QUERY, params, mapper);
	}
	
	public int insertOneMobileSuitEquipment(MobileSuitEquipmentEntity msEquipmentEntity) {
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("equipmentId", msEquipmentEntity.getEquipmentId())
				.addValue("msId", msEquipmentEntity.getMsId())
				.addValue("armedId", msEquipmentEntity.getArmedId())
				.addValue("numberEquipment", msEquipmentEntity.getNumberEquipment());
		
		return namedParameterJdbcTemplate.update(INSERT_MOBILESUIT_EQUIPMENT_QUERY, params);
	}
	
	public MobileSuitEntity selectOneMobileSuit(String msId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		RowMapper<MobileSuitEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitEntity>(MobileSuitEntity.class);
		
		return namedParameterJdbcTemplate.queryForObject(SEARCH_MOBILESUIT_QUERY, params, mapper);
	}
	
	public int updateOneMobileSuit(MobileSuitEntity msEntity) {
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("msId", msEntity.getMsId())
				.addValue("modelNumber", msEntity.getModelNumber())
				.addValue("msName", msEntity.getMsName())
				.addValue("msUrl", msEntity.getMsUrl())
				.addValue("headHeight", msEntity.getHeadHeight())
				.addValue("weight", msEntity.getWeight())
				.addValue("totalWeight", msEntity.getTotalWeight())
				.addValue("powerSource", msEntity.getPowerSource())
				.addValue("material", msEntity.getMaterial())
				.addValue("generatorOutput", msEntity.getGeneratorOutput())
				.addValue("totalThrustersOutput", msEntity.getTotalThrustersOutput())
				.addValue("msOverview", msEntity.getMsOverview())
				.addValue("action", msEntity.getAction());
		
		return namedParameterJdbcTemplate.update(UPDATE_MOBILESUIT_QUERY, params);
	}
	
	public MobileSuitArmedEntity selectOneArmed(String armedId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("armedId", armedId);
		RowMapper<MobileSuitArmedEntity> mapper =
				new BeanPropertyRowMapper<MobileSuitArmedEntity>(MobileSuitArmedEntity.class);
		
		return namedParameterJdbcTemplate.queryForObject(SEARCH_MOBILESUIT_ARMED_QUERY, params, mapper);
	}
	
	public int updateOneMobileSuitArmed(MobileSuitArmedEntity msArmedEntity) {
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("armedId", msArmedEntity.getArmedId())
				.addValue("armedName", msArmedEntity.getArmedName())
				.addValue("armedExplanation", msArmedEntity.getArmedExplanation());
		
		return namedParameterJdbcTemplate.update(UPDATE_MOBILESUIT_ARMED_QUERY, params);
	}
	
	public MobileSuitEquipmentEntity selectOneEquipment(String equipmentId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("equipmentId", equipmentId);
		RowMapper<MobileSuitEquipmentEntity> mapper =
				new BeanPropertyRowMapper<MobileSuitEquipmentEntity>(MobileSuitEquipmentEntity.class);
		
		return namedParameterJdbcTemplate.queryForObject(SEARCH_MOBILESUIT_EQUIPMENT_QUERY, params, mapper);
	}
	
	public int updateOneMobileSuitEquipment(MobileSuitEquipmentEntity msEquipmentEntity) {
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("equipmentId", msEquipmentEntity.getEquipmentId())
				.addValue("numberEquipment", msEquipmentEntity.getNumberEquipment());
		
		return namedParameterJdbcTemplate.update(UPDATE_MOBILESUIT_EQUIPMENT_QUERY, params);
	}
	
	public int daleteOneEquipmentForMobileSuit(String msId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		
		return namedParameterJdbcTemplate.update(DELETE_EQUIPMENT_FORMOBILESUIT_QUERY, params);
	}
	
	public int deleteOneMobileSuit(String msId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("msId", msId);
		
		return namedParameterJdbcTemplate.update(DELETE_MOBILESUIT_QUERY, params);
	}
	
	public int deleteOneEquipmentForArmed(String armedId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("armedId", armedId);
		
		return namedParameterJdbcTemplate.update(DELETE_EQUIPMENT_FORARMED_QUERY, params);
	}
	
	public int deleteOneArmed(String armedId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("armedId", armedId);
		
		return namedParameterJdbcTemplate.update(DELETE_ARMED_QUERY, params);
	}
	
	public int deleteOneEquipment(String equipmentId) {
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("equipmentId", equipmentId);
		
		return namedParameterJdbcTemplate.update(DELETE_EQUIPMENT_QUERY, params);
	}

}
