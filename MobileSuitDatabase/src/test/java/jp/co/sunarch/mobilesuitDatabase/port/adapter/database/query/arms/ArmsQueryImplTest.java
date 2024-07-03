package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ArmsQueryImplTest {

	private final String INSERT_ARMS = """
			insert
			into Arms
			values (?, ?, ?, ?, ?, ?);
			""";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ArmsQueryImpl sut;

	@Nested
	@DisplayName("武器一覧取得")
	class GetArmsList {
		@Test
		void テーブル全件のデータが取得されること() throws Exception {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);

			List<ArmsModel> armsModelList = sut.getArmsList();

			List<ArmsModel> extendList = createArmsModelList();
			assertThat(armsModelList)
					.containsExactlyElementsOf(extendList);
		}

		@Test
		void テーブルにデータがない場合は0件であること() {
			List<ArmsModel> armsModelList = sut.getArmsList();

			assertThat(armsModelList.size()).isEqualTo(0);
		}
	}

	@Nested
	@DisplayName("武器取得ID指定")
	class GetArmsById {
		@Test
		void 指定したIDの武器が取得できること() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);

			Optional<ArmsModel> armsModelOpt = sut.getArmsById("arms1");

			assertTrue(armsModelOpt.isPresent());

			ArmsModel armsModel = armsModelOpt.get();
			assertThat(armsModel.getArmsId()).isEqualTo("arms1");
			assertThat(armsModel.getArmsName()).isEqualTo("テストライフル1");
			assertThat(armsModel.getDetail()).isEqualTo("テスト1");
		}

		@Test
		void 存在しないIDを指定した時はデータが存在しないこと() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);

			Optional<ArmsModel> armsModelOpt = sut.getArmsById("arms4");

			assertFalse(armsModelOpt.isPresent());
		}
	}

	@Nested
	@DisplayName("武器検索")
	class SearchArms {
		@Test
		void 武器名指定() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);

			Criteria criteria = ArmsQuery.Criteria.builder().armsName("テストライフル1").build();
			List<ArmsModel> armsModels = sut.searchArms(criteria);

			List<ArmsModel> extendList = List.of(createArmsModel(1));
			assertThat(armsModels).containsExactlyElementsOf(extendList);
		}

		@Test
		void 武器名指定なし() {
			jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);
			jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"),
					Timestamp.valueOf("2019-05-01 01:02:03"), 1);

			Criteria criteria = ArmsQuery.Criteria.builder().build();
			List<ArmsModel> armsModels = sut.searchArms(criteria);

			List<ArmsModel> extendList = createArmsModelList();
			assertThat(armsModels)
					.containsExactlyElementsOf(extendList);
		}
	}

	private List<ArmsModel> createArmsModelList() {
		List<ArmsModel> list = new ArrayList<>();
		list.add(createArmsModel(1));
		list.add(createArmsModel(2));
		list.add(createArmsModel(3));

		return list;
	}

	private ArmsModel createArmsModel(int seq) {
		return ArmsModel.builder()
				.armsId("arms" + seq)
				.armsName("テストライフル" + seq)
				.detail("テスト" + seq)
				.insertDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant())
				.updateDate(Timestamp.valueOf("2019-05-01 01:02:03").toInstant())
				.version(1)
				.build();
	}

}
