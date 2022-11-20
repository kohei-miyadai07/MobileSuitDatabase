package jp.co.sunarch.mobilesuitDatabase.application.usecase.arms;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.RegistArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistArmsUseCase {

	private final ArmsRecodeService armsRecodeService;

	public void execute(RegistArmsCommand command) {
		ArmsId armsId = ArmsId.of(RandomStringUtils.randomAlphanumeric(8));
		Arms arms = Arms.create(
				armsId
				,command.getArmsName()
				,command.getDetail());

		armsRecodeService.registArms(arms);
	}
}
