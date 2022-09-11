package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

public class ArmsSqlCode {

	public static String INSERT_ARMS = """
			insert 
			into Arms 
			values (:armsId, :armsName, :detail);
			""";

	public static String SELECT_ARMS_QUERY_BY_ID = """
			select
			arms_id
			, arms_name
			, detail 
			from
			Arms 
			where
			arms_id = :armsId
			""";

}
