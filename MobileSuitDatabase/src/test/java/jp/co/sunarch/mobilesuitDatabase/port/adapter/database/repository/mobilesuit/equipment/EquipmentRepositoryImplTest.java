package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

import static org.assertj.core.api.Assertions.assertThat;

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

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

@JdbcTest
@ActiveProfiles("test")
@Import({EquipmentRepositoryImpl.class, EquipmentDao.class, EquipmentConverter.class})
class EquipmentRepositoryImplTest {

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

	private final String SELECT_EQUIPMENT_BY_MSID_AND_ARMSID = """
			select
			ms_id
			, arms_id
			, number_equipment
			, detail 
			from
			Equipment 
			where
			ms_id = ?
			and
			arms_id = ?;
			""";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EquipmentRepositoryImpl sut;

	@Nested
	class GetEquipmentByMsIdAndArmsId {
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
		void モビルスーツIDと武器IDを指定すると紐づいた装備ドメインモデルを取得できること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			Equipment equipment = sut.getEquipmentByMsIdAndArmsId("ms1", "arms1");

			Equipment extend = Equipment.create(
					MobileSuitId.of("ms1"),
					ArmsId.of("arms1"),
					Integer.valueOf(1),
					"テスト装備1",
					localDateTime,
					localDateTime,
					Integer.valueOf(1));

			assertThat(equipment)
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
		void 対象の装備ドメインモデルが未登録の場合は新規登録されること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			Equipment equipment = Equipment.create(
					MobileSuitId.of("ms1"),
					ArmsId.of("arms2"),
					Integer.valueOf(10),
					"テスト装備1-2",
					localDateTime,
					localDateTime,
					Integer.valueOf(1));
			sut.save(equipment);

			EquipmentRowMapper rowMapper = new EquipmentRowMapper();
			Equipment extend = jdbcTemplate.queryForObject(
					SELECT_EQUIPMENT_BY_MSID_AND_ARMSID, rowMapper, "ms1", "arms2");

			assertThat(equipment)
			.isEqualTo(extend);
		}

		@Test
		void 装備ドメインモデルが登録済みの場合は更新されること() {
			String strDateTime = "2023/04/02 10:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);

			Equipment equipment = Equipment.create(
					MobileSuitId.of("ms1"),
					ArmsId.of("arms1"),
					Integer.valueOf(100),
					"テスト装備1_update",
					localDateTime,
					localDateTime,
					Integer.valueOf(2));
			sut.save(equipment);

			EquipmentRowMapper rowMapper = new EquipmentRowMapper();
			Equipment extend = jdbcTemplate.queryForObject(
					SELECT_EQUIPMENT_BY_MSID_AND_ARMSID, rowMapper, "ms1", "arms1");

			assertThat(equipment)
			.isEqualTo(extend);
		}
	}

	@Nested
	class DeleteEquipmentByMsIdAndArmsId {
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
		void 指定したモビルスーツIDと武器IDに紐づいた装備データが削除されていること() {
			sut.deleteEquipmentByMsIdAndArmsId("ms3", "arms3");

			EquipmentRowMapper rowMapper = new EquipmentRowMapper();
			Equipment extend = new Equipment();

			try {
				extend = jdbcTemplate.queryForObject(SELECT_EQUIPMENT_BY_MSID_AND_ARMSID, rowMapper, "ms3", "arms3");
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
