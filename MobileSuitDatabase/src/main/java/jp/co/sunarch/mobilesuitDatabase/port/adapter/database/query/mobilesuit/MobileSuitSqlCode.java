package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

public class MobileSuitSqlCode {

	private MobileSuitSqlCode() {}

	public static String SELECT_MOBILESUIT_LIST_QUERY = """
			select
			ms_id
			, model_number
			, ms_name
			, ms_url
			, head_height
			, overall_height
			, weight
			, total_weight
			, power_source
			, material
			, effective_sensor_radius
			, generator_output
			, total_thrusters_output
			, ms_overview
			, action
			, insert_date
			, update_date
			, version 
			from
			MobileSuit 
			order by
			ms_name
			, ms_id
			""";
	
	public static String SELECT_MOBILESUIT_QUERY_BY_ID = """
			select
			ms_id
			, model_number
			, ms_name
			, ms_url
			, head_height
			, overall_height
			, weight
			, total_weight
			, power_source
			, material
			, effective_sensor_radius
			, generator_output
			, total_thrusters_output
			, ms_overview
			, action
			, insert_date
			, update_date
			, version 
			from
			MobileSuit 
			where
			ms_id = :msId
			""";
	
	public static String SELECT_MOBILESUIT_ID_QUERY_BY_ID = """
			select
			ms_id
			from
			MobileSuit 
			where
			ms_id = :msId
			""";
	
	public static String SELECT_MOBILESULT_QUERY_BASE = """
			select
			ms_id
			, model_number
			, ms_name
			, ms_url
			, head_height
			, overall_height
			, weight
			, total_weight
			, power_source
			, material
			, effective_sensor_radius
			, generator_output
			, total_thrusters_output
			, ms_overview
			, action
			, insert_date
			, update_date
			, version 
			from
			MobileSuit
			""";

}
