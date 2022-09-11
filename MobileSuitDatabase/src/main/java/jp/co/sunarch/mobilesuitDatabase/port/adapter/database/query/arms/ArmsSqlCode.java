package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

public class ArmsSqlCode {

	public static String SELECT_ARMS_LIST = """
			select
			arms_id
			, arms_name
			, detail 
			from
			Arms 
			order by
			arms_name
			, arms_id
			""";

	public static String SELECT_ARMS_QUERY_BY_ID = """
			select
			arms_id
			, arms_name
			, detail 
			from
			Arms 
			where
			arms_id = :armsId;
			""";

	public static String SELECT_ARMS_QUERY_BASE = """
			select
			arms_id
			, arms_name
			, detail 
			from
			Arms
			""";

}
