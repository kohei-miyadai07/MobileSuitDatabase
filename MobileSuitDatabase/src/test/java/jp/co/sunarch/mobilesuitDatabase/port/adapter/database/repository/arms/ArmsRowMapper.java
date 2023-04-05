package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

public class ArmsRowMapper implements RowMapper<Arms> {

	@Override
	public Arms mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Arms.create(
				ArmsId.of(rs.getString("arms_id")),
				rs.getString("arms_name"), 
				rs.getString("detail"));
	}

}
