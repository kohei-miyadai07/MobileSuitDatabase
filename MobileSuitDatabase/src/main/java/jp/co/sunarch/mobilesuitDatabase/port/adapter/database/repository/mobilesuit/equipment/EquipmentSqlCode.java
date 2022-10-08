package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

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

	public static String SELECT_EQUIPMENT_BY_ARMSID = """
			select
			ms_id
			, arms_id
			, number_equipment
			, detail 
			from
			Equipment 
			where
			arms_id = :armsId;
			""";

	public static String DELETE_EQUIPMENT_BY_MSID = """
			delete 
			from
			Equipment 
			where
			ms_id = :msId
			""";

	public static String DELETE_EQUIPMENT_BY_ARMSID = """
			delete 
			from
			Equipment 
			where
			arms_id = :armsId;
			""";

}
