package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;

public class MobileSuitRowMapper implements RowMapper<MobileSuit> {

	@Override
	public MobileSuit mapRow(ResultSet rs, int rowNum) throws SQLException {
		MobileSuit mobileSuit = new MobileSuit();
		mobileSuit.setMsId(MobileSuitId.of(rs.getString("ms_id")));
		mobileSuit.setModelNumber(rs.getString("model_number"));
		mobileSuit.setMsName(rs.getString("ms_name"));
		mobileSuit.setMsUrl(rs.getString("ms_url"));
		mobileSuit.setHeadHeight(rs.getBigDecimal("head_height"));
		mobileSuit.setOverallHeight(rs.getBigDecimal("overall_height"));
		mobileSuit.setWeight(rs.getBigDecimal("weight"));
		mobileSuit.setTotalWeight(rs.getBigDecimal("total_weight"));
		mobileSuit.setPowerSource(rs.getString("power_source"));
		mobileSuit.setMaterial(rs.getString("material"));
		mobileSuit.setEffectiveSensorRadius(rs.getLong("effective_sensor_radius"));
		mobileSuit.setGeneratorOutput(rs.getLong("generator_output"));
		mobileSuit.setTotalThrustersOutput(rs.getLong("total_thrusters_output"));
		mobileSuit.setMsOverview(rs.getString("ms_overview"));
		mobileSuit.setAction(rs.getString("action"));
		mobileSuit.setInsertDate(rs.getTimestamp("insert_date").toInstant());
		mobileSuit.setUpdateDate(rs.getTimestamp("update_date").toInstant());
		mobileSuit.setVersion(rs.getInt("version"));

		return mobileSuit;
	}
}
