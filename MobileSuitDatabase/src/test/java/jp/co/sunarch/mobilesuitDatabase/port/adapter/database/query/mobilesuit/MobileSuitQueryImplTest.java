package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MobileSuitQueryImplTest {

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

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MobileSuitQueryImpl sut;

	@BeforeEach
	void setUp() throws Exception {
		jdbcTemplate.update(
				INSERT_MOBILESUIT,
				"ms1",
				"msNum1",
				"テストモビルスーツ1",
				"/ms/url1",
				1.00,
				10.00,
				1.00,
				10.00,
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
				1.00,
				10.00,
				1.00,
				10.00,
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
				1.00,
				10.00,
				1.00,
				10.00,
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

		jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", timestampOf("2023/04/02 10:00:00"),
				timestampOf("2023/04/02 10:00:00"), 1);
		jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", timestampOf("2023/04/02 10:00:00"),
				timestampOf("2023/04/02 10:00:00"), 1);
		jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", timestampOf("2023/04/02 10:00:00"),
				timestampOf("2023/04/02 10:00:00"), 1);

		jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1", timestampOf("2023/04/02 10:00:00"),
				timestampOf("2023/04/02 10:00:00"), 1);
		jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2", timestampOf("2023/04/02 10:00:00"),
				timestampOf("2023/04/02 10:00:00"), 1);
		jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3", timestampOf("2023/04/02 10:00:00"),
				timestampOf("2023/04/02 10:00:00"), 1);

	}

	@Nested
	class GetMobileSuitList {
		@Test
		void モビルスーツのデータを全件取得できること() {
			List<MobileSuitModel> msList = sut.getMobileSuitList();

			assertThat(msList).containsExactly(
					createMoblieSuitModel(1),
					createMoblieSuitModel(2),
					createMoblieSuitModel(3));
		}
	}

	@Nested
	class GetMobileSuitDetail {
		@Test
		void モビルスーツのIDを指定すると紐づいたモビルスーツの詳細情報を取得できること() {
			MobileSuitDetailModel msDetail = sut.getMobileSuitDetail("ms1");

			assertThat(msDetail.getMsId()).isEqualTo("ms1");
			assertThat(msDetail.getModelNumber()).isEqualTo("msNum1");
			assertThat(msDetail.getMsName()).isEqualTo("テストモビルスーツ1");
			assertThat(msDetail.getMsUrl()).isEqualTo("/ms/url1");
			assertEquals(0, msDetail.getHeadHeight().compareTo(new BigDecimal(1).setScale(2, RoundingMode.DOWN)));
			assertEquals(0, msDetail.getOverallHeight().compareTo(new BigDecimal(10).setScale(2, RoundingMode.DOWN)));
			assertEquals(0, msDetail.getWeight().compareTo(new BigDecimal(1).setScale(2, RoundingMode.DOWN)));
			assertEquals(0, msDetail.getTotalWeight().compareTo(new BigDecimal(10).setScale(2, RoundingMode.DOWN)));
			assertThat(msDetail.getPowerSource()).isEqualTo("テストパワーソース1");
			assertThat(msDetail.getMaterial()).isEqualTo("テストマテリアル1");
			assertThat(msDetail.getEffectiveSensorRadius()).isEqualTo(100L);
			assertThat(msDetail.getGeneratorOutput()).isEqualTo(200L);
			assertThat(msDetail.getTotalThrustersOutput()).isEqualTo(300L);
			assertThat(msDetail.getMsOverview()).isEqualTo("テスト説明1");
			assertThat(msDetail.getAction()).isEqualTo("テスト活躍1");
			assertEquals(0, msDetail.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, msDetail.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(msDetail.getVersion()).isEqualTo(1);
		}
	}

	@Nested
	class GetMobileSuitById {
		@Test
		void モビルスーツIDを指定すると紐づいたモビルスーツのデータを取得できること() {
			MobileSuitModel mobileSuit = sut.getMobileSuitById("ms1");

			assertThat(mobileSuit.getMsId()).isEqualTo("ms1");
			assertThat(mobileSuit.getModelNumber()).isEqualTo("msNum1");
			assertThat(mobileSuit.getMsName()).isEqualTo("テストモビルスーツ1");
			assertThat(mobileSuit.getMsUrl()).isEqualTo("/ms/url1");
			assertEquals(0, mobileSuit.getHeadHeight().compareTo(new BigDecimal(1).setScale(2, RoundingMode.DOWN)));
			assertEquals(0, mobileSuit.getOverallHeight().compareTo(new BigDecimal(10).setScale(2, RoundingMode.DOWN)));
			assertEquals(0, mobileSuit.getWeight().compareTo(new BigDecimal(1).setScale(2, RoundingMode.DOWN)));
			assertEquals(0, mobileSuit.getTotalWeight().compareTo(new BigDecimal(10).setScale(2, RoundingMode.DOWN)));
			assertThat(mobileSuit.getPowerSource()).isEqualTo("テストパワーソース1");
			assertThat(mobileSuit.getMaterial()).isEqualTo("テストマテリアル1");
			assertThat(mobileSuit.getEffectiveSensorRadius()).isEqualTo(100L);
			assertThat(mobileSuit.getGeneratorOutput()).isEqualTo(200L);
			assertThat(mobileSuit.getTotalThrustersOutput()).isEqualTo(300L);
			assertThat(mobileSuit.getMsOverview()).isEqualTo("テスト説明1");
			assertThat(mobileSuit.getAction()).isEqualTo("テスト活躍1");
			assertEquals(0, mobileSuit.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, mobileSuit.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
		}
	}

	@Nested
	class searchMobileSuit {
		@Test
		void 条件を指定すると紐づいたモビルスーツのデータを取得できること() {
			Criteria criteria = MobileSuitQuery.Criteria.builder()
					.modelNumber("msNum1")
					.headHeightFrom(new BigDecimal(0).setScale(2, RoundingMode.DOWN))
					.headHeightTo(new BigDecimal(1).setScale(2, RoundingMode.DOWN))
					.overallHeightFrom(new BigDecimal(9).setScale(2, RoundingMode.DOWN))
					.overallHeightTo(new BigDecimal(11).setScale(2, RoundingMode.DOWN))
					.weightFrom(new BigDecimal(0).setScale(2, RoundingMode.DOWN))
					.weightTo(new BigDecimal(2).setScale(2, RoundingMode.DOWN))
					.totalWeightFrom(new BigDecimal(9).setScale(2, RoundingMode.DOWN))
					.totalWeightTo(new BigDecimal(11).setScale(2, RoundingMode.DOWN))
					.effectiveSensorRadiusFrom(99L)
					.effectiveSensorRadiusTo(101L)
					.generatorOutputFrom(119L)
					.generatorOutputTo(201L)
					.totalThrustersOutputFrom(299L)
					.totalThrustersOutputTo(301L)
					.build();
			List<MobileSuitModel> msList = sut.searchMobileSuit(criteria);

			assertThat(msList).containsExactly(createMoblieSuitModel(1));
		}
	}

	private Timestamp timestampOf(String strTime) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return new Timestamp(simpleDateFormat.parse(strTime).getTime());
	}

	private MobileSuitModel createMoblieSuitModel(int seq) {
		return MobileSuitModel.builder()
				.msId("ms" + seq)
				.modelNumber("msNum" + seq)
				.msName("テストモビルスーツ" + seq)
				.msUrl("/ms/url" + seq)
				.headHeight(new BigDecimal(1).setScale(2, RoundingMode.DOWN))
				.overallHeight(new BigDecimal(10).setScale(2, RoundingMode.DOWN))
				.weight(new BigDecimal(1).setScale(2, RoundingMode.DOWN))
				.totalWeight(new BigDecimal(10).setScale(2, RoundingMode.DOWN))
				.powerSource("テストパワーソース" + seq)
				.material("テストマテリアル" + seq)
				.effectiveSensorRadius(100L)
				.generatorOutput(200L)
				.totalThrustersOutput(300L)
				.msOverview("テスト説明" + seq)
				.action("テスト活躍" + seq)
				.insertDate(Instant.parse("2023-04-02T01:00:00Z"))
				.updateDate(Instant.parse("2023-04-02T01:00:00Z"))
				.version(1)
				.build();
	}
}
