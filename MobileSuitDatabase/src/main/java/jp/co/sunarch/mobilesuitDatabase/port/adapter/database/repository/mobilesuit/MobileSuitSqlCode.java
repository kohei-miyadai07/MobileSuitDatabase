package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

public class MobileSuitSqlCode {

	private MobileSuitSqlCode() {}

	public static String INSERT_MOBILESUIT = """
			insert 
			into MobileSuit 
			values ( 
			:msId
			,:modelNumber
			,:msName
			,:msUrl
			,:headHeight
			,:overallHeight
			,:weight
			,:totalWeight
			,:powerSource
			,:material
			,:effectiveSensorRadius
			,:generatorOutput
			,:totalThrustersOutput
			,:msOverview
			,:action
			,:insertDate
			,:updateDate
			,:version
			);
			""";

	public static String UPDATE_MOBILESUIT = """
			update MobileSuit 
			set
			ms_id = :msId
			, model_number = :modelNumber
			, ms_name = :msName
			, ms_url = :msUrl
			, head_height = :headHeight
			, overall_height = :overallHeight
			, weight = :weight
			, total_weight = :totalWeight
			, power_source = :powerSource
			, material = :material
			, effective_sensor_radius = :effectiveSensorRadius
			, generator_output = :generatorOutput
			, total_thrusters_output = :totalThrustersOutput
			, ms_overview = :msOverview
			, action = :action
			, insert_date = :insertDate
			, update_date = :updateDate
			, version = :version 
			where
			ms_id = :msId
			""";

	public static String DELETE_MOBILESUIT_BY_ID = """
			delete 
			from
			MobileSuit 
			where
			ms_id = :msId
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

}
