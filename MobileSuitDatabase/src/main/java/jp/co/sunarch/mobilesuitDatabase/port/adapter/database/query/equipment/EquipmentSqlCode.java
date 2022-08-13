package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.equipment;

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

}
