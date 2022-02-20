package jp.co.sunarch.mobilesuitDatabase.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitsEntity;

@Repository
public class MobileSuitDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MobileSuitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
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
	
	public List<MobileSuitsEntity> searchMobileSuits() {
		RowMapper<MobileSuitsEntity> mapper = 
				new BeanPropertyRowMapper<MobileSuitsEntity>(MobileSuitsEntity.class);
		return jdbcTemplate.query(SEARCH_MOBILESUITS_QUERY, mapper);
		
	}

}
