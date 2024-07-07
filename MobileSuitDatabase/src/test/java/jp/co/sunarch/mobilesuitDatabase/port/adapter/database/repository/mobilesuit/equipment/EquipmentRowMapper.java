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
		Equipment equipment = new Equipment();
		equipment.setMsId(MobileSuitId.of(rs.getString("ms_id")));
		equipment.setArmsId(ArmsId.of(rs.getString("arms_id")));
		equipment.setNumberEquipment(Integer.valueOf(rs.getInt("number_equipment")));
		equipment.setDetail(rs.getString("detail"));
		equipment.setInsertDate(rs.getTimestamp("insert_date").toInstant());
		equipment.setUpdateDate(rs.getTimestamp("update_date").toInstant());
		equipment.setVersion(rs.getInt("version"));

		return equipment;
	}
}
