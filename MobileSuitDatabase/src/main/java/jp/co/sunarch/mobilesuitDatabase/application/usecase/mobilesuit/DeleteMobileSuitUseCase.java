package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.DeleteMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteMobileSuitUseCase {

	private final MobileSuitQueryService mobileSuitQueryService;
	private final MobileSuitRecodeService mobileSuitRecodeService;

	public void execute(DeleteMobileSuitCommand command) {
		MobileSuit mobileSuit = mobileSuitQueryService.getMobileSuitById(command.getMsId());

		mobileSuitRecodeService.deleteImageFile(mobileSuit.getMsUrl());
		mobileSuitRecodeService.deleteMobileSuit(mobileSuit);

	}
}
