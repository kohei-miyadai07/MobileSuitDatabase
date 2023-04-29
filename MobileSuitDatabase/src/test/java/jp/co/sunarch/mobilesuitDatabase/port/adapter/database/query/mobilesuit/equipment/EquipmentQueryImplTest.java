package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.mobilesuit.equipment;

import static org.assertj.core.api.Assertions.*;

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

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.equipment.EquipmentQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.equipment.EquipmentModel;

@JdbcTest
@ActiveProfiles("test")
@Import({EquipmentQueryImpl.class, JdbcTemplateEquipmentDao.class})
class EquipmentQueryImplTest {

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
	private EquipmentQueryImpl sut;

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
	class GetEquipmentList {
		@Test
		void 装備データの全件を取得できること() {
			List<EquipmentModel> equipmentList = sut.getEquipmentList();

			List<EquipmentModel> extendList = createEquipmentModelList();
			assertThat(equipmentList)
			.isEqualTo(extendList);
		}
	}

	@Nested
	class GetEquipmentByMsIdAndArmsId {
		@Test
		void モビルスーツIDと武器IDを指定すると紐づいた装備データを取得できること() {
			EquipmentModel equipment = sut.getEquipmentByMsIdAndArmsId("ms1", "arms1");

			EquipmentModel extend = createEquipmentModel(
					"ms1", "テストモビルスーツ1", "arms1", "テストライフル1", 1, "テスト装備1");
			assertThat(equipment)
			.isEqualTo(extend);
		}
	}

	@Nested
	class SearchEquipment {
		@Test
		void 条件を指定すると紐づいた装備データを取得できること() {
			Criteria criteria = EquipmentQuery.Criteria.builder()
					.msName("テストモビルスーツ1")
					.armsName("テストライフル1")
					.build();
			List<EquipmentModel> equipmentList = sut.searchEquipment(criteria);

			List<EquipmentModel> extendList = Collections.singletonList(
					createEquipmentModel("ms1", "テストモビルスーツ1", "arms1", "テストライフル1", 1, "テスト装備1"));
			assertThat(equipmentList)
			.isEqualTo(extendList);
		}
	}

	private Timestamp timestampOf(String strTime) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return new Timestamp(simpleDateFormat.parse(strTime).getTime());
	}

	private List<EquipmentModel> createEquipmentModelList() {
		List<EquipmentModel> list = new ArrayList<>();

		list.add(createEquipmentModel("ms1", "テストモビルスーツ1", "arms1", "テストライフル1", 1, "テスト装備1"));
		list.add(createEquipmentModel("ms2", "テストモビルスーツ2", "arms2", "テストライフル2", 2, "テスト装備2"));
		list.add(createEquipmentModel("ms3", "テストモビルスーツ3", "arms3", "テストライフル3", 3, "テスト装備3"));

		return list;
	}
	private EquipmentModel createEquipmentModel(String msId, String msName, String armsId,
			String armsName, int numberEquipment, String detail) {
		return EquipmentModel.builder()
				.msId(msId)
				.msName(msName)
				.armsId(armsId)
				.armsName(armsName)
				.numberEquipment(String.valueOf(numberEquipment))
				.detail(detail)
				.build();
	}

}
