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

	public static String SELECT_EQUIPMENT_BY_MSID_AND_ARMSID = """
			select
			ms_id
			, arms_id
			, number_equipment
			, detail 
			from
			Equipment 
			where
			ms_id = :msId
			and
			arms_id = :armsId;
			""";

	public static String INSERT_EQUIPMENT = """
			insert 
			into Equipment 
			values (:msId, :armsId, :numberEquipment, :detail)
			""";

	public static String UPDATE_EQUIPMENT = """
			update Equipment 
			set
			ms_id = :msId
			, arms_id = :armsId
			, number_equipment = :numberEquipment
			, detail = :detail 
			where
			ms_id = :msId 
			and arms_id = :armsId
			""";

	public static String DELETE_EQUIPMENT_BY_MSID_AND_ARMSID = """
			delete 
			from
			Equipment 
			where
			ms_id = :msId
			and
			arms_id = :armsId
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
