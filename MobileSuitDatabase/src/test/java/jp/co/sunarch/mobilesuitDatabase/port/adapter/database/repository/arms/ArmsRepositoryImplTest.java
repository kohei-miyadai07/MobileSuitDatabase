package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import static org.assertj.core.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ArmsRepositoryImplTest {

	private final String INSERT_ARMS = """
			insert
			into Arms
			values (?, ?, ?, ?, ?, ?);
			""";

	private final String SELECT_ARMS_QUERY_BY_ID = """
			select
			arms_id
			, arms_name
			, detail
			, insert_date
			, update_date
			, version
			from
			Arms
			where
			arms_id = ?
			""";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ArmsRepositoryImpl sut;

	@Nested
	@DisplayName("武器データ取得")
	class getArmsById {
		@BeforeEach
		void setUp() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
		}

		@Test
		void 武器IDを指定すると紐づいた武器ドメインモデルを取得できること() {
			Arms arms = sut.getArmsById("arms1");

			Arms extend = new Arms();
			extend.setArmsId(ArmsId.of("arms1"));
			extend.setArmsName("テストライフル1");
			extend.setDetail("テスト1");
			extend.setInsertDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant());
			extend.setUpdateDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant());
			extend.setVersion(1);

			assertThat(arms)
					.isEqualTo(extend);
		}
	}

	@Nested
	@DisplayName("武器データ登録・更新")
	class save {
		@BeforeEach
		void setUp() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
		}

		@Test
		void 対象の武器ドメインモデルが未登録の場合は新規登録されること() {
			Arms arms = new Arms();
			arms.setArmsId(ArmsId.of("arms4"));
			arms.setArmsName("テストライフル4");
			arms.setDetail("テスト4");
			arms.setInsertDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant());
			arms.setUpdateDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant());
			arms.setVersion(1);

			sut.save(arms);

			ArmsRowMapper rowMapper = new ArmsRowMapper();
			Arms extend = jdbcTemplate.queryForObject(SELECT_ARMS_QUERY_BY_ID, rowMapper, "arms4");

			assertThat(arms)
					.isEqualTo(extend);
		}

		@Test
		void 対象の武器ドメインモデルが登録済みの場合は更新されること() {
			Arms arms = new Arms();
			arms.setArmsId(ArmsId.of("arms2"));
			arms.setArmsName("テストライフル2-update");
			arms.setDetail("テスト2-update");
			arms.setInsertDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant());
			arms.setUpdateDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant());
			arms.setVersion(2);
			sut.save(arms);

			ArmsRowMapper rowMapper = new ArmsRowMapper();
			Arms extend = jdbcTemplate.queryForObject(SELECT_ARMS_QUERY_BY_ID, rowMapper, "arms2");

			assertThat(arms)
					.isEqualTo(extend);
		}
	}

	@Nested
	@DisplayName("武器データ削除")
	class DeleteArmsById {
		@BeforeEach
		void setUp() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
		}

		@Test
		void 対象の武器データが削除されていること() {
			sut.deleteArmsById("arms3");

			ArmsRowMapper rowMapper = new ArmsRowMapper();
			Arms extend = new Arms();

			try {
				extend = jdbcTemplate.queryForObject(SELECT_ARMS_QUERY_BY_ID, rowMapper, "arms3");
			} catch (EmptyResultDataAccessException e) {
				extend = null;
			}

			assertThat(extend)
					.isNull();
		}
	}
}
