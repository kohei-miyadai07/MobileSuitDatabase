package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentArmsModel;

@JdbcTest
@ActiveProfiles("test")
@Import({MobileSuitQueryImpl.class, JdbcMobileSuitDao.class})
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
			values (?, ?, ?);
			""";

	private final String INSERT_EQUIPMENT = """
			insert 
			into Equipment 
			values (?, ?, ?, ?)
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

		jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
		jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
		jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");

		jdbcTemplate.update(INSERT_EQUIPMENT, "ms1", "arms1", 1, "テスト装備1");
		jdbcTemplate.update(INSERT_EQUIPMENT, "ms2", "arms2", 2, "テスト装備2");
		jdbcTemplate.update(INSERT_EQUIPMENT, "ms3", "arms3", 3, "テスト装備3");

	}

	@Nested
	class GetMobileSuitList {
		@Test
		void モビルスーツのデータを全件取得できること() {
			List<MobileSuitModel> msList = sut.getMobileSuitList();

			List<MobileSuitModel> extendList = createMobileSuitModelList();
			assertThat(msList)
			.isEqualTo(extendList);
		}
	}

	@Nested
	class GetMobileSuitDetail {
		@Test
		void モビルスーツのIDを指定すると紐づいたモビルスーツの詳細情報を取得できること() {
			MobileSuitDetailModel msDetail = sut.getMobileSuitDetail("ms1");

			MobileSuitModel mobileSuit = createMoblieSuitModel(1);
			MobileSuitDetailModel extend = createMobileSuitDetailModel(
					mobileSuit,
					Collections.singletonList(
							createEquipmentArmsModel(
									"ms1",
									"arms1",
									"テストライフル1",
									1, 
									"テスト装備1")));
			assertThat(msDetail)
			.isEqualTo(extend);
		}
	}

	@Nested
	class GetMobileSuitById {
		@Test
		void モビルスーツIDを指定すると紐づいたモビルスーツのデータを取得できること() {
			MobileSuitModel mobileSuit = sut.getMobileSuitById("ms1");

			MobileSuitModel extend = createMoblieSuitModel(1);
			assertThat(mobileSuit)
			.isEqualTo(extend);
		}
	}

	@Nested
	class searchMobileSuit {
		@Test
		void 条件を指定すると紐づいたモビルスーツのデータを取得できること() {
			Criteria criteria = MobileSuitQuery.Criteria.builder()
					.modelNumber("msNum1")
					.headHeightFrom(new BigDecimal(0))
					.headHeightTo(new BigDecimal(0))
					.overallHeightFrom(new BigDecimal(0))
					.overallHeightTo(new BigDecimal(0))
					.weightFrom(new BigDecimal(0))
					.weightTo(new BigDecimal(0))
					.totalWeightFrom(new BigDecimal(0))
					.totalWeightTo(new BigDecimal(0))
					.effectiveSensorRadiusFrom(0L)
					.effectiveSensorRadiusTo(0L)
					.generatorOutputFrom(0L)
					.generatorOutputTo(0L)
					.totalThrustersOutputFrom(0L)
					.totalThrustersOutputTo(0L)
					.build();
			List<MobileSuitModel> msList = sut.searchMobileSuit(criteria);

			List<MobileSuitModel> extendList = Collections.singletonList(createMoblieSuitModel(1));
			assertThat(msList)
			.isEqualTo(extendList);
		}
	}

	private Timestamp timestampOf(String strTime) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return new Timestamp(simpleDateFormat.parse(strTime).getTime());
	}

	private List<MobileSuitModel> createMobileSuitModelList() {
		List<MobileSuitModel> list = new ArrayList<>();

		list.add(createMoblieSuitModel(1));
		list.add(createMoblieSuitModel(2));
		list.add(createMoblieSuitModel(3));

		return list;
	}

	private MobileSuitModel createMoblieSuitModel(int seq) {
		return MobileSuitModel.builder()
				.msId("ms" + seq)
				.modelNumber("msNum" + seq)
				.msName("テストモビルスーツ" + seq)
				.msUrl("/ms/url" + seq)
				.headHeight("1.00")
				.overallHeight("10.00")
				.weight("1.00")
				.totalWeight("10.00")
				.powerSource("テストパワーソース" + seq)
				.material("テストマテリアル" + seq)
				.effectiveSensorRadius(String.valueOf(100L))
				.generatorOutput(String.valueOf(200L))
				.totalThrustersOutput(String.valueOf(300L))
				.msOverview("テスト説明" + seq)
				.action("テスト活躍" + seq)
				.insertDate("2023/04/02 10:00:00")
				.updateDate("2023/04/02 10:00:00")
				.version(String.valueOf(1))
				.build();
	}

	private MobileSuitDetailModel createMobileSuitDetailModel(MobileSuitModel mobileSuit, 
			List<EquipmentArmsModel> equipmentList) {
		return MobileSuitDetailModel.builder()
				.msId(mobileSuit.getMsId())
				.modelNumber(mobileSuit.getModelNumber())
				.msName(mobileSuit.getMsName())
				.msUrl(mobileSuit.getMsUrl())
				.headHeight(mobileSuit.getHeadHeight() + "m")
				.overallHeight(mobileSuit.getOverallHeight() + "m")
				.weight(mobileSuit.getWeight() + "t")
				.totalWeight(mobileSuit.getTotalWeight() + "t")
				.powerSource(mobileSuit.getPowerSource())
				.material(mobileSuit.getMaterial())
				.effectiveSensorRadius(mobileSuit.getEffectiveSensorRadius() + "m")
				.generatorOutput(mobileSuit.getGeneratorOutput() + "kW")
				.totalThrustersOutput(mobileSuit.getTotalThrustersOutput() + "kg")
				.msOverview(mobileSuit.getMsOverview())
				.action(mobileSuit.getAction())
				.insertDate(mobileSuit.getInsertDate())
				.updateDate(mobileSuit.getUpdateDate())
				.version(mobileSuit.getVersion())
				.equipmentArmsResultList(equipmentList)
				.build();
	}

	private EquipmentArmsModel createEquipmentArmsModel(String msId, String armsId, String armsName,
			int numberEquipment, String detail) {
		return EquipmentArmsModel.builder()
				.msId(msId)
				.armsId(armsId)
				.armsName(armsName)
				.numberEquipment(String.valueOf(numberEquipment))
				.detail(detail)
				.build();
	}

}
