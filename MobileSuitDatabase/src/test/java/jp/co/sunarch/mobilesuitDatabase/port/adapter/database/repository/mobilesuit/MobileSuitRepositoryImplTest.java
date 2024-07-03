package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

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

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
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
			values (?, ?, ?, ?, ?, ?);
			""";

	private final String INSERT_EQUIPMENT = """
			insert 
			into Equipment 
			values (?, ?, ?, ?, ?, ?, ?)
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
	@DisplayName("モビルスーツデータ取得")
	class getMobileSuitById {
		@BeforeEach
		void setUp() throws Exception {
			jdbcTemplate.update(
					INSERT_MOBILESUIT,
					"ms1",
					"msNum1",
					"テストモビルスーツ1",
					"/ms/url1",
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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

			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);

			jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);

		}

		@Test
		void モビルスーツIDを指定すると紐づいたモビルスーツドメインモデルが取得できること() {
			MobileSuit mobileSuit = sut.getMobileSuitById("ms1");

			assertThat(mobileSuit.getMsId().getValue()).isEqualTo("ms1");
			assertThat(mobileSuit.getModelNumber()).isEqualTo("msNum1");
			assertThat(mobileSuit.getMsName()).isEqualTo("テストモビルスーツ1");
			assertThat(mobileSuit.getMsUrl()).isEqualTo("/ms/url1");
			assertEquals(0, mobileSuit.getHeadHeight().get().compareTo(new BigDecimal("18.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, mobileSuit.getOverallHeight().get().compareTo(new BigDecimal("18.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, mobileSuit.getWeight().get().compareTo(new BigDecimal("18.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, mobileSuit.getTotalWeight().get().compareTo(new BigDecimal("18.55").setScale(2, RoundingMode.DOWN)));
			assertThat(mobileSuit.getPowerSource().get()).isEqualTo("テストパワーソース1");
			assertThat(mobileSuit.getMaterial().get()).isEqualTo("テストマテリアル1");
			assertThat(mobileSuit.getEffectiveSensorRadius().get()).isEqualTo(100L);
			assertThat(mobileSuit.getGeneratorOutput().get()).isEqualTo(200L);
			assertThat(mobileSuit.getTotalThrustersOutput().get()).isEqualTo(300L);
			assertThat(mobileSuit.getMsOverview().get()).isEqualTo("テスト説明1");
			assertThat(mobileSuit.getAction().get()).isEqualTo("テスト活躍1");
			assertEquals(0, mobileSuit.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, mobileSuit.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(mobileSuit.getVersion()).isEqualTo(1);
		}
	}

	@Nested
	@DisplayName("モビルスーツデータ登録・更新")
	class save {
		@BeforeEach
		void setUp() throws Exception {
			jdbcTemplate.update(
					INSERT_MOBILESUIT,
					"ms1",
					"msNum1",
					"テストモビルスーツ1",
					"/ms/url1",
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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

			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);

			jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
		}

		@Test
		void 対象のモビルスーツドメインモデルが未登録の場合は新規登録されること() {
			MobileSuit mobileSuit = MobileSuit.create(
					MobileSuitId.of("ms4"),
					"msNum4",
					"テストモビルスーツ4",
					"/ms/url4",
					new BigDecimal("19.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("19.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("19.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("19.55").setScale(2, RoundingMode.DOWN),
					"テストパワーソース4",
					"テストマテリアル4",
					100L,
					200L,
					300L,
					"テスト説明4",
					"テスト活躍4",
					Instant.parse("2023-04-02T01:00:00Z"),
					Instant.parse("2023-04-02T01:00:00Z"),
					Integer.valueOf(1));
			sut.save(mobileSuit);

			MobileSuitRowMapper rowMapper = new MobileSuitRowMapper();
			MobileSuit actual = jdbcTemplate.queryForObject(SELECT_MOBILESUIT_QUERY_BY_ID, rowMapper, "ms4");

			assertThat(actual.getMsId().getValue()).isEqualTo("ms4");
			assertThat(actual.getModelNumber()).isEqualTo("msNum4");
			assertThat(actual.getMsName()).isEqualTo("テストモビルスーツ4");
			assertThat(actual.getMsUrl()).isEqualTo("/ms/url4");
			assertEquals(0, actual.getHeadHeight().get().compareTo(new BigDecimal("19.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, actual.getOverallHeight().get().compareTo(new BigDecimal("19.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, actual.getWeight().get().compareTo(new BigDecimal("19.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, actual.getTotalWeight().get().compareTo(new BigDecimal("19.55").setScale(2, RoundingMode.DOWN)));
			assertThat(actual.getPowerSource().get()).isEqualTo("テストパワーソース4");
			assertThat(actual.getMaterial().get()).isEqualTo("テストマテリアル4");
			assertThat(actual.getEffectiveSensorRadius().get()).isEqualTo(100L);
			assertThat(actual.getGeneratorOutput().get()).isEqualTo(200L);
			assertThat(actual.getTotalThrustersOutput().get()).isEqualTo(300L);
			assertThat(actual.getMsOverview().get()).isEqualTo("テスト説明4");
			assertThat(actual.getAction().get()).isEqualTo("テスト活躍4");
			assertEquals(0, actual.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, actual.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(actual.getVersion()).isEqualTo(1);
		}

		@Test
		void 対象のモビルスーツドメインモデルが登録済みの場合は更新されること() {
			MobileSuit mobileSuit = MobileSuit.create(
					MobileSuitId.of("ms2"),
					"msNum5",
					"テストモビルスーツ5",
					"/ms/url5",
					new BigDecimal("20.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("20.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("20.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("20.55").setScale(2, RoundingMode.DOWN),
					"テストパワーソース5",
					"テストマテリアル5",
					100L,
					200L,
					300L,
					"テスト説明5",
					"テスト活躍5",
					Instant.parse("2023-04-02T01:00:00Z"),
					Instant.parse("2023-04-02T01:00:00Z"),
					Integer.valueOf(2));
			sut.save(mobileSuit);

			MobileSuitRowMapper rowMapper = new MobileSuitRowMapper();
			MobileSuit actual = jdbcTemplate.queryForObject(SELECT_MOBILESUIT_QUERY_BY_ID, rowMapper, "ms2");

			assertThat(actual.getMsId().getValue()).isEqualTo("ms2");
			assertThat(actual.getModelNumber()).isEqualTo("msNum5");
			assertThat(actual.getMsName()).isEqualTo("テストモビルスーツ5");
			assertThat(actual.getMsUrl()).isEqualTo("/ms/url5");
			assertEquals(0, actual.getHeadHeight().get().compareTo(new BigDecimal("20.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, actual.getOverallHeight().get().compareTo(new BigDecimal("20.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, actual.getWeight().get().compareTo(new BigDecimal("20.55").setScale(2, RoundingMode.DOWN)));
			assertEquals(0, actual.getTotalWeight().get().compareTo(new BigDecimal("20.55").setScale(2, RoundingMode.DOWN)));
			assertThat(actual.getPowerSource().get()).isEqualTo("テストパワーソース5");
			assertThat(actual.getMaterial().get()).isEqualTo("テストマテリアル5");
			assertThat(actual.getEffectiveSensorRadius().get()).isEqualTo(100L);
			assertThat(actual.getGeneratorOutput().get()).isEqualTo(200L);
			assertThat(actual.getTotalThrustersOutput().get()).isEqualTo(300L);
			assertThat(actual.getMsOverview().get()).isEqualTo("テスト説明5");
			assertThat(actual.getAction().get()).isEqualTo("テスト活躍5");
			assertEquals(0, actual.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, actual.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(actual.getVersion()).isEqualTo(2);
		}
	}

	@Nested
	@DisplayName("モビルスーツデータ削除")
	class deleteMobileSuitById {
		@BeforeEach
		void setUp() throws Exception {
			jdbcTemplate.update(
					INSERT_MOBILESUIT,
					"ms1",
					"msNum1",
					"テストモビルスーツ1",
					"/ms/url1",
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
					new BigDecimal("18.55").setScale(2, RoundingMode.DOWN),
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

			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);

			jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
			jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3",
					timestampOf("2023/04/02 10:00:00"), timestampOf("2023/04/02 10:00:00"), 1);
		}

		@Test
		void 対象のモビルスーツドメインモデルが削除されていること() {
			sut.deleteMobileSuitById("ms3");

			MobileSuitRowMapper rowMapper = new MobileSuitRowMapper();
			MobileSuit actual = new MobileSuit();

			try {
				actual = jdbcTemplate.queryForObject(SELECT_MOBILESUIT_QUERY_BY_ID, rowMapper, "ms3");
			} catch(EmptyResultDataAccessException e) {
				actual = null;
			}

			assertThat(actual)
			.isNull();
		}
	}

	private Timestamp timestampOf(String strTime) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return new Timestamp(simpleDateFormat.parse(strTime).getTime());
	}

}
