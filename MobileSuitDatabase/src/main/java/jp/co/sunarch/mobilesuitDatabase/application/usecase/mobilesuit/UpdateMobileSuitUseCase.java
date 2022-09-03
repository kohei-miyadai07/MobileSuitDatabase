package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.UpdateMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitQueryService;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateMobileSuitUseCase {
	
	private final MobileSuitQueryService mobileSuitQueryService;
	private final MobileSuitRecodeService mobileSuitRecodeService;

	public String execute(UpdateMobileSuitCommand command) {
		// 更新対象のMobileSuitを取得
		MobileSuit mobileSuit = mobileSuitQueryService.getMobileSuitById(command.getMsId());

		// 更新対象のMobileSuitに更新項目をセット
		mobileSuit.setModelNumber(command.getModelNumber());
		mobileSuit.setMsName(command.getMsName());
		mobileSuit.setMsUrl(command.getMsUrl());
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
		mobileSuit.setUpdateDate(LocalDateTime.now());
		mobileSuit.setVersion(String.valueOf(Integer.parseInt(mobileSuit.getVersion()) + 1));

		return mobileSuitRecodeService.UpdateMobileSuit(mobileSuit);
	}

}
