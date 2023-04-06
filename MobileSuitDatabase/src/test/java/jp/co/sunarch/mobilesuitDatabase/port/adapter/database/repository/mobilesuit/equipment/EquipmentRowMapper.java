package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

public class EquipmentRowMapper implements RowMapper<Equipment> {

	@Override
	public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Equipment.create(
				MobileSuitId.of(rs.getString("ms_id")),
				ArmsId.of(rs.getString("arms_id")),
				Integer.valueOf(rs.getInt("number_equipment")),
				rs.getString("detail"));
	}

}
