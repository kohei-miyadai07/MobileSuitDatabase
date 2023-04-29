package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;

@ExtendWith(MockitoExtension.class)
class ArmsQueryServiceTest {
    @InjectMocks
	ArmsQueryService sut;

    @Mock
    ArmsRepository armsRepository;

	@Test
	void 武器IDに紐づく武器ドメインモデルを取得できること() {
		when(armsRepository.getArmsById(any()))
		.thenReturn(Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail"));

		Arms arms = sut.getArmsById(ArmsId.of("arms-test"));

		assertThat(arms)
		.isEqualTo(Arms.create(ArmsId.of("arms-test"), "arms-test-name", "arms-test-detail"));

		verify(armsRepository).getArmsById("arms-test");
		
	}

}
