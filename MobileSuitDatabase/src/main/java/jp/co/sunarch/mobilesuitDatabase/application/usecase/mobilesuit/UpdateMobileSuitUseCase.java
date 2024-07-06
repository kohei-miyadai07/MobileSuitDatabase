package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit;

import java.time.Instant;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.UpdateMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.util.exception.MobileSuitDataBaseConflictException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateMobileSuitUseCase {
	
	private final MobileSuitQueryService mobileSuitQueryService;
	private final MobileSuitRecodeService mobileSuitRecodeService;

	public void execute(UpdateMobileSuitCommand command) {
		// 更新対象のMobileSuitを取得
		MobileSuit before = mobileSuitQueryService.getMobileSuitById(command.getMsId());

		// 更新用のMobileSuitを作成
		MobileSuit mobileSuit = new MobileSuit();
		mobileSuit.setMsId(command.getMsId());
		mobileSuit.setModelNumber(command.getModelNumber());
		mobileSuit.setMsName(command.getMsName());
		mobileSuit.setMsUrl(before.getMsUrl());
		mobileSuit.setHeadHeight(command.getHeadHeight());
		mobileSuit.setOverallHeight(command.getOverallHeight());
		mobileSuit.setWeight(command.getWeight());
		mobileSuit.setTotalWeight(command.getTotalWeight());
		mobileSuit.setPowerSource(command.getPowerSource());
		mobileSuit.setMaterial(command.getMaterial());
		mobileSuit.setEffectiveSensorRadius(command.getEffectiveSensorRadius());
		mobileSuit.setGeneratorOutput(command.getGeneratorOutput());
		mobileSuit.setTotalThrustersOutput(command.getTotalThrustersOutput());
		mobileSuit.setMsOverview(command.getMsOverview());
		mobileSuit.setAction(command.getAction());
		mobileSuit.setInsertDate(before.getInsertDate());
		mobileSuit.setUpdateDate(Instant.now());
		mobileSuit.setVersion(command.getVersion());

		if(before.getVersion() != mobileSuit.getVersion()) {
			throw new MobileSuitDataBaseConflictException("すでに別のユーザーによって更新された可能性があります。最新情報をご確認ください。");
		}

		if (!command.getMsMultipartFile().isEmpty()) {
			mobileSuitRecodeService.deleteImageFile(mobileSuit.getMsUrl());

			String msUrl = "lib/images/" + command.getMsMultipartFile().getOriginalFilename();
			mobileSuit.setMsUrl(msUrl);
			mobileSuitRecodeService.updateImageFile(command.getMsMultipartFile());
		}

		mobileSuitRecodeService.updateMobileSuit(mobileSuit);
	}
}
