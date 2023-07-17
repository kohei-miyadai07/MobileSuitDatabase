package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;

@JdbcTest
@ActiveProfiles("test")
@Import({MobileSuitRepositoryImpl.class, MobileSuitConverter.class, MobileSuitDao.class})
class MobileSuitRepositoryImplTest {

	private final String INSERT_MOBILESUIT = """
			insert 
			into MobileSuit 
			values ( 
			?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			,?
			);
			""";

	private final String INSERT_ARMS = """
			insert 
			into Arms 
			values (?, ?, ?);
			""";

	private final String INSERT_EQUIPMENT = """
			insert 
			into Equipment 
			values (?, ?, ?, ?)
			""";

	private final String SELECT_MOBILESUIT_QUERY_BY_ID = """
			select
			ms_id
			, model_number
			, ms_name
			, ms_url
			, head_height
			, overall_height
			, weight
			, total_weight
			, power_source
			, material
			, effective_sensor_radius
			, generator_output
			, total_thrusters_output
			, ms_overview
			, action
			, insert_date
			, update_date
			, version 
			from
			MobileSuit 
			where
			ms_id = ?
			""";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MobileSuitRepositoryImpl sut;

	@Nested
	class getMobileSuitById {
		@BeforeEach
		void setUp() throws Exception {
			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms1",
							"msNum1",
							"テストモビルスーツ1",
							"/ms/url1",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース1",
							"テストマテリアル1",
							100L,
							200L,
							300L,
							"テスト説明1",
							"テスト活躍1",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms2",
							"msNum2",
							"テストモビルスーツ2",
							"/ms/url2",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース2",
							"テストマテリアル2",
							100L,
							200L,
							300L,
							"テスト説明2",
							"テスト活躍2",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms3",
							"msNum3",
							"テストモビルスーツ3",
							"/ms/url3",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース3",
							"テストマテリアル3",
							100L,
							200L,
							300L,
							"テスト説明3",
							"テスト活躍3",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");

			jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1");
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2");
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3");

		}

		@Test
		void モビルスーツIDを指定すると紐づいたモビルスーツドメインモデルが取得できること() {
			MobileSuit mobileSuit = sut.getMobileSuitById("ms1");

			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			MobileSuit extend = MobileSuit.create(
					MobileSuitId.of("ms1"),
					"msNum1",
					"テストモビルスーツ1",
					"/ms/url1",
					new BigDecimal("18.55"),
					new BigDecimal("18.55"),
					new BigDecimal("18.55"),
					new BigDecimal("18.55"),
					"テストパワーソース1",
					"テストマテリアル1",
					100L,
					200L,
					300L,
					"テスト説明1",
					"テスト活躍1",
					localDateTime,
					localDateTime,
					Integer.valueOf(1));

			assertThat(mobileSuit)
			.isEqualTo(extend);	
		}
	}

	@Nested
	class save {
		@BeforeEach
		void setUp() throws Exception {
			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms1",
							"msNum1",
							"テストモビルスーツ1",
							"/ms/url1",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース1",
							"テストマテリアル1",
							100L,
							200L,
							300L,
							"テスト説明1",
							"テスト活躍1",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms2",
							"msNum2",
							"テストモビルスーツ2",
							"/ms/url2",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース2",
							"テストマテリアル2",
							100L,
							200L,
							300L,
							"テスト説明2",
							"テスト活躍2",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms3",
							"msNum3",
							"テストモビルスーツ3",
							"/ms/url3",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース3",
							"テストマテリアル3",
							100L,
							200L,
							300L,
							"テスト説明3",
							"テスト活躍3",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");

			jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1");
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2");
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3");
		}

		@Test
		void 対象のモビルスーツドメインモデルが未登録の場合は新規登録されること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			MobileSuit mobileSuit = MobileSuit.create(
					MobileSuitId.of("ms4"),
					"msNum4",
					"テストモビルスーツ4",
					"/ms/url4",
					new BigDecimal("19.55"),
					new BigDecimal("19.55"),
					new BigDecimal("19.55"),
					new BigDecimal("19.55"),
					"テストパワーソース4",
					"テストマテリアル4",
					100L,
					200L,
					300L,
					"テスト説明4",
					"テスト活躍4",
					localDateTime,
					localDateTime,
					Integer.valueOf(1));
			sut.save(mobileSuit);

			MobileSuitRowMapper rowMapper = new MobileSuitRowMapper();
			MobileSuit extend = jdbcTemplate.queryForObject(SELECT_MOBILESUIT_QUERY_BY_ID, rowMapper, "ms4");

			assertThat(mobileSuit)
			.isEqualTo(extend);
		}

		@Test
		void 対象のモビルスーツドメインモデルが登録済みの場合は更新されること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			MobileSuit mobileSuit = MobileSuit.create(
					MobileSuitId.of("ms2"),
					"msNum5",
					"テストモビルスーツ5",
					"/ms/url5",
					new BigDecimal("20.55"),
					new BigDecimal("20.55"),
					new BigDecimal("20.55"),
					new BigDecimal("20.55"),
					"テストパワーソース5",
					"テストマテリアル5",
					100L,
					200L,
					300L,
					"テスト説明5",
					"テスト活躍5",
					localDateTime,
					localDateTime,
					Integer.valueOf(2));
			sut.save(mobileSuit);

			MobileSuitRowMapper rowMapper = new MobileSuitRowMapper();
			MobileSuit extend = jdbcTemplate.queryForObject(SELECT_MOBILESUIT_QUERY_BY_ID, rowMapper, "ms2");

			assertThat(mobileSuit)
			.isEqualTo(extend);
		}
	}

	@Nested
	class deleteMobileSuitById {
		@BeforeEach
		void setUp() throws Exception {
			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms1",
							"msNum1",
							"テストモビルスーツ1",
							"/ms/url1",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース1",
							"テストマテリアル1",
							100L,
							200L,
							300L,
							"テスト説明1",
							"テスト活躍1",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms2",
							"msNum2",
							"テストモビルスーツ2",
							"/ms/url2",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース2",
							"テストマテリアル2",
							100L,
							200L,
							300L,
							"テスト説明2",
							"テスト活躍2",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(
							INSERT_MOBILESUIT,
							"ms3",
							"msNum3",
							"テストモビルスーツ3",
							"/ms/url3",
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							new BigDecimal("18.55"),
							"テストパワーソース3",
							"テストマテリアル3",
							100L,
							200L,
							300L,
							"テスト説明3",
							"テスト活躍3",
							timestampOf("2023/04/02 10:00:00"),
							timestampOf("2023/04/02 10:00:00"),
							1);

			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");

			jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1");
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2");
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3");
		}

		@Test
		void 対象のモビルスーツドメインモデルが削除されていること() {
			sut.deleteMobileSuitById("ms3");

			MobileSuitRowMapper rowMapper = new MobileSuitRowMapper();
			MobileSuit extend = new MobileSuit();

			try {
				extend = jdbcTemplate.queryForObject(SELECT_MOBILESUIT_QUERY_BY_ID, rowMapper, "ms3");
			} catch(EmptyResultDataAccessException e) {
				extend = null;
			}

			assertThat(extend)
			.isNull();
		}
	}

	private Timestamp timestampOf(String strTime) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return new Timestamp(simpleDateFormat.parse(strTime).getTime());
	}

}
