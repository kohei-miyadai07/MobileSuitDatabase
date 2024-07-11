package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit.equipment;

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

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.equipment.Equipment;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
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
			values (?, ?, ?, ?, ?, ?);
			""";

	private final String INSERT_EQUIPMENT = """
			insert
			into Equipment
			values (?, ?, ?, ?, ?, ?, ?)
			""";

	private final String SELECT_EQUIPMENT_BY_MSID_AND_ARMSID = """
			select
			ms_id
			, arms_id
			, number_equipment
			, detail
			, insert_date
			, update_date
			, version 
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
	@DisplayName("モビルスーツIDと武器IDを元に装備データを取得")
	class GetEquipmentByMsIdAndArmsId {
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

		@Test
		void モビルスーツIDと武器IDを指定すると紐づいた装備ドメインモデルを取得できること() {
			Equipment equipment = sut.getEquipmentByMsIdAndArmsId("ms1", "arms1");

			assertThat(equipment.getMsId().getValue()).isEqualTo("ms1");
			assertThat(equipment.getArmsId().getValue()).isEqualTo("arms1");
			assertThat(equipment.getNumberEquipment().get()).isEqualTo(1);
			assertThat(equipment.getDetail().get()).isEqualTo("テスト装備1");
			assertThat(equipment.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(equipment.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(equipment.getVersion()).isEqualTo(1);
		}
	}

	@Nested
	@DisplayName("装備データ登録・更新")
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

		@Test
		void 対象の装備ドメインモデルが未登録の場合は新規登録されること() {
			Equipment equipment = new Equipment();
			equipment.setMsId(MobileSuitId.of("ms1"));
			equipment.setArmsId(ArmsId.of("arms2"));
			equipment.setNumberEquipment(Integer.valueOf(10));
			equipment.setDetail("テスト装備1-2");
			equipment.setInsertDate(Instant.parse("2023-04-02T01:00:00Z"));
			equipment.setUpdateDate(Instant.parse("2023-04-02T01:00:00Z"));
			equipment.setVersion(Integer.valueOf(1));

			sut.save(equipment);

			EquipmentRowMapper rowMapper = new EquipmentRowMapper();
			Equipment actual = jdbcTemplate.queryForObject(
					SELECT_EQUIPMENT_BY_MSID_AND_ARMSID, rowMapper, "ms1", "arms2");

			assertThat(actual.getMsId().getValue()).isEqualTo("ms1");
			assertThat(actual.getArmsId().getValue()).isEqualTo("arms2");
			assertThat(actual.getNumberEquipment().get()).isEqualTo(10);
			assertEquals(0, actual.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, actual.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(actual.getVersion()).isEqualTo(1);
			
		}

		@Test
		void 装備ドメインモデルが登録済みの場合は更新されること() {
			Equipment equipment = new Equipment();
			equipment.setMsId(MobileSuitId.of("ms1"));
			equipment.setArmsId(ArmsId.of("arms1"));
			equipment.setNumberEquipment(Integer.valueOf(100));
			equipment.setDetail("テスト装備1_update");
			equipment.setInsertDate(Instant.parse("2023-04-02T01:00:00Z"));
			equipment.setUpdateDate(Instant.parse("2023-04-02T01:00:00Z"));
			equipment.setVersion(Integer.valueOf(2));
			sut.save(equipment);

			EquipmentRowMapper rowMapper = new EquipmentRowMapper();
			Equipment actual = jdbcTemplate.queryForObject(
					SELECT_EQUIPMENT_BY_MSID_AND_ARMSID, rowMapper, "ms1", "arms1");

			assertThat(actual.getMsId().getValue()).isEqualTo("ms1");
			assertThat(actual.getArmsId().getValue()).isEqualTo("arms1");
			assertThat(actual.getNumberEquipment().get()).isEqualTo(100);
			assertEquals(0, actual.getInsertDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertEquals(0, actual.getUpdateDate().compareTo(Instant.parse("2023-04-02T01:00:00Z")));
			assertThat(actual.getVersion()).isEqualTo(2);
		}
	}

	@Nested
	@DisplayName("モビルスーツIDと武器IDによる装備データ削除")
	class DeleteEquipmentByMsIdAndArmsId {
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

		@Test
		void 指定したモビルスーツIDと武器IDに紐づいた装備データが削除されていること() {
			sut.deleteEquipmentByMsIdAndArmsId("ms3", "arms3");

			EquipmentRowMapper rowMapper = new EquipmentRowMapper();
			Equipment actual = new Equipment();

			try {
				actual = jdbcTemplate.queryForObject(SELECT_EQUIPMENT_BY_MSID_AND_ARMSID, rowMapper, "ms3", "arms3");
			} catch(EmptyResultDataAccessException e) {
				actual = null;
			}

			assertThat(actual).isNull();
		}
	}

	private Timestamp timestampOf(String strTime) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return new Timestamp(simpleDateFormat.parse(strTime).getTime());
	}
}
