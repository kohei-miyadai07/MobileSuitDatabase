package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.arms;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

@JdbcTest
@ActiveProfiles("test")
@Import({ArmsRepositoryImpl.class, ArmsConverter.class, ArmsDao.class})
class ArmsRepositoryImplTest {

	private final String INSERT_ARMS = """
			insert 
			into Arms 
			values (?, ?, ?);
			""";

	private final String SELECT_ARMS_QUERY_BY_ID = """
			select
			arms_id
			, arms_name
			, detail 
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
	class getArmsById {
		@BeforeEach
		void setUp() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");
		}

		@Test
		void 武器IDを指定すると紐づいた武器ドメインモデルを取得できること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			Arms arms = sut.getArmsById("arms1");

			Arms extend = Arms.create(
					ArmsId.of("arms1"),
					"テストライフル1",
					"テスト1",
					localDateTime,
					localDateTime,
					Integer.valueOf(1));
			assertThat(arms)
			.isEqualTo(extend);
		}
	}

	@Nested
	class save {
		@BeforeEach
		void setUp() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");
		}

		@Test
		void 対象の武器ドメインモデルが未登録の場合は新規登録されること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			Arms arms = Arms.create(
					ArmsId.of("arms4"),
					"テストライフル4",
					"テスト4",
					localDateTime,
					localDateTime,
					Integer.valueOf(1));
			sut.save(arms);

			ArmsRowMapper rowMapper = new ArmsRowMapper();
			Arms extend = jdbcTemplate.queryForObject(SELECT_ARMS_QUERY_BY_ID, rowMapper, "arms4");

			assertThat(arms)
			.isEqualTo(extend);
		}

		@Test
		void 対象の武器ドメインモデルが登録済みの場合は更新されること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			Arms arms = Arms.create(
					ArmsId.of("arms2"),
					"テストライフル2-update",
					"テスト2-update",
					localDateTime,
					localDateTime,
					Integer.valueOf(2));
			sut.save(arms);

			ArmsRowMapper rowMapper = new ArmsRowMapper();
			Arms extend = jdbcTemplate.queryForObject(SELECT_ARMS_QUERY_BY_ID, rowMapper, "arms2");
	
			assertThat(arms)
			.isEqualTo(extend);
		}
	}

	@Nested
	class DeleteArmsById {
		@BeforeEach
		void setUp() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");
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
