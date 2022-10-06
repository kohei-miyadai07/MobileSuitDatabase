package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

public class EquipmentSqlCode {

	private EquipmentSqlCode() {}

	public static String SELECT_EQUIPMENT_ARMS_BY_MSID_QUERY = """
			select
			e.ms_id
			,e.arms_id
			,a.arms_name
			,e.number_equipment
			,e.detail
			from
			Equipment e 
			inner join Arms a
			on e.arms_id = a.arms_id
			where
			e.ms_id = :msId
			""";

	public static String SELECT_EQUIPMENT_LIST = """
			select
			e.ms_id
			, m.ms_name
			, e.arms_id
			, a.arms_name 
			, e.number_equipment
			, e.detail
			from
			Equipment e 
			inner join MobileSuit m 
			on e.ms_id = m.ms_id 
			inner join Arms a 
			on e.arms_id = a.arms_id
			order by 
			m.ms_name
			, a.arms_name
			""";
	
	public static String SELECT_EQUIPMENT_QUERY_BASE = """
			select
			e.ms_id
			, m.ms_name
			, e.arms_id
			, a.arms_name 
			, e.number_equipment
			, e.detail
			from
			Equipment e 
			inner join MobileSuit m 
			on e.ms_id = m.ms_id 
			inner join Arms a 
			on e.arms_id = a.arms_id
			""";

}
