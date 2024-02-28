package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import static org.assertj.core.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;

@JdbcTest
@ActiveProfiles("test")
@Import(DomaAutoConfiguration.class)
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

	@BeforeEach
	void seUp() {
		jdbcTemplate.update(INSERT_ARMS, "arms1", "テストライフル1", "テスト1", Timestamp.valueOf("2019-05-01 01:02:03"), Timestamp.valueOf("2019-05-01 01:02:03"), 1);
		jdbcTemplate.update(INSERT_ARMS, "arms2", "テストライフル2", "テスト2", Timestamp.valueOf("2019-05-01 01:02:03"), Timestamp.valueOf("2019-05-01 01:02:03"), 1);
		jdbcTemplate.update(INSERT_ARMS, "arms3", "テストライフル3", "テスト3", Timestamp.valueOf("2019-05-01 01:02:03"), Timestamp.valueOf("2019-05-01 01:02:03"), 1);
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
