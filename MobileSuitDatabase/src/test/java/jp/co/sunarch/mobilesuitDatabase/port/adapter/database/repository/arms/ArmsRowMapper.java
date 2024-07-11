package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

public class ArmsRowMapper implements RowMapper<Arms> {

	@Override
	public Arms mapRow(ResultSet rs, int rowNum) throws SQLException {
		Arms arms = new Arms();
		arms.setArmsId(ArmsId.of(rs.getString("arms_id")));
		arms.setArmsName(rs.getString("arms_name"));
		arms.setDetail(rs.getString("detail"));
		arms.setInsertDate(rs.getTimestamp("insert_date").toInstant());
		arms.setUpdateDate(rs.getTimestamp("update_date").toInstant());
		arms.setVersion(rs.getInt("version"));

		return arms;
	}
}
