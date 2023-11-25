package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import static org.assertj.core.api.Assertions.*;

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

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery.Criteria;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@JdbcTest
@ActiveProfiles("test")
@Import({ArmsQueryImpl.class, JdbcArmsDao.class})
class ArmsQueryImplTest {

	private final String INSERT_ARMS = """
			insert 
			into Arms 
			values (?, ?, ?);
			""";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ArmsQueryImpl sut;

	@BeforeEach
	void seUp() {
		jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1");
		jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2");
		jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3");
		
	}

	@Nested
	class GetArmsList {
		@Test
		void テーブル全件のデータが取得されること() throws Exception {
			List<ArmsModel> armsModelList = sut.getArmsList();

			List<ArmsModel> extendList = createArmsModelList();
			assertThat(armsModelList)
			.isEqualTo(extendList);
		}
	}

	@Nested
	class GetArmsById {
		@Test
		void 武器IDを指定すると紐づいた武器データが取得できること() throws Exception {
			ArmsModel arms = sut.getArmsById("arms1");

			ArmsModel extend = createArmsModel(1);
			assertThat(arms)
			.isEqualTo(extend);
		}
	}

	@Nested
	class SearchArms {
		@Test
		void 条件を指定すると紐づいた武器データが取得できること() throws Exception {
			Criteria criteria = ArmsQuery.Criteria
					.builder()
					.armsName("テストライフル1")
					.build();
			List<ArmsModel> armsList = sut.searchArms(criteria);

			List<ArmsModel> extendList = Collections.singletonList(createArmsModel(1));
			assertThat(armsList)
			.isEqualTo(extendList);
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
				.build();
	}

}
