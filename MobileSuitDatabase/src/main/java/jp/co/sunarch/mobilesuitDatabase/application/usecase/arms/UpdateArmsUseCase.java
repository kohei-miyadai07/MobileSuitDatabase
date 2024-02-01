package jp.co.sunarch.mobilesuitDatabase.application.usecase.arms;

import java.time.Instant;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.UpdateArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateArmsUseCase {

	private final ArmsQueryService armsQueryService;
	private final ArmsRecodeService armsRecodeService;

	public void execute (UpdateArmsCommand command) {
		// 更新対象のArmsを取得
		Arms arms = armsQueryService.getArmsById(command.getArmsId());

		// 更新対象のArmsに更新項目をセット
		arms.setArmsName(command.getArmsName());
		arms.setDetail(command.getDetail());
		arms.setUpdateDate(Instant.now());
		arms.setVersion(arms.getVersion() + 1);

		armsRecodeService.updateArms(arms);
	}
}
