package jp.co.sunarch.mobilesuitDatabase.application.usecase.arms;

import java.time.Instant;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.arms.UpdateArmsCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.arms.ArmsRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.util.exception.MobileSuitDataBaseConflictException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateArmsUseCase {

	private final ArmsQueryService armsQueryService;
	private final ArmsRecodeService armsRecodeService;

	public void execute (UpdateArmsCommand command) {
		// 更新対象のArmsを取得
		Arms before = armsQueryService.getArmsById(command.getArmsId());

		// 更新様のArmsを作成
		Arms arms = new Arms();
		arms.setArmsId(command.getArmsId());
		arms.setArmsName(command.getArmsName());
		arms.setDetail(command.getDetail());
		arms.setInsertDate(before.getInsertDate());
		arms.setUpdateDate(Instant.now());
		arms.setVersion(command.getVersion());

		if (before.getVersion() != arms.getVersion()) {
			throw new MobileSuitDataBaseConflictException("すでに別のユーザーによって更新された可能性があります。最新情報をご確認ください。");
		}

		armsRecodeService.updateArms(arms);
	}
}
