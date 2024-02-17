package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;

public class MobileSuitRowMapper implements RowMapper<MobileSuit> {

	@Override
	public MobileSuit mapRow(ResultSet rs, int rowNum) throws SQLException {
		return MobileSuit.create(
				MobileSuitId.of(rs.getString("ms_id")),
				rs.getString("model_number"),
				rs.getString("ms_name"),
				rs.getString("ms_url"),
				rs.getBigDecimal("head_height"),
				rs.getBigDecimal("overall_height"),
				rs.getBigDecimal("weight"),
				rs.getBigDecimal("total_weight"),
				rs.getString("power_source"),
				rs.getString("material"),
				rs.getLong("effective_sensor_radius"),
				rs.getLong("generator_output"),
				rs.getLong("total_thrusters_output"),
				rs.getString("ms_overview"),
				rs.getString("action"),
				rs.getTimestamp("insert_date").toInstant(),
				rs.getTimestamp("update_date").toInstant(),
				rs.getInt("version"));
	}
}
