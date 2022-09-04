package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.equipment;

public class EquipmentSqlCode {

	public static String SELECT_EQUIPMENT_BY_MSID = """
			select
			ms_id
			, arms_id
			, number_equipment
			, detail 
			from
			Equipment 
			where
			ms_id = :msId
			""";

	public static String DELETE_EQUIPMENT_BY_MSID = """
			delete 
			from
			Equipment 
			where
			ms_id = :msId
			""";

}
