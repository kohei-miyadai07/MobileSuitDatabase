package jp.co.sunarch.mobilesuitDatabase.application.usecase.arms;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.DeleteArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteArmsUseCase {

	private final ArmsQueryService armsQueryService;
	private final ArmsRecodeService armsRecodeService;

	public void execute(DeleteArmsCommand command) {
		Arms arms = armsQueryService.getArmsById(command.getArmsId());

		armsRecodeService.deleteArms(arms);

	}
}
