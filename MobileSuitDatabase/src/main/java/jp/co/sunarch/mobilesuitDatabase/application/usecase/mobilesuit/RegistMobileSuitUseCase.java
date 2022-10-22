package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.RegistMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitRecodeService;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistMobileSuitUseCase {
	
	private final MobileSuitRecodeService mobileSuitRecodeService;
	
	public String execute(RegistMobileSuitCommand command) {

		MobileSuitId msId = MobileSuitId.of(RandomStringUtils.randomAlphanumeric(8));
		String msUrl = "lib/images/" + command.getMsMultipartFile().getOriginalFilename();

		MobileSuit mobileSuit = MobileSuit.create(
				msId
				,command.getModelNumber()
				,command.getMsName()
				,msUrl
				,command.getHeadHeight()
				,command.getOverallHeight()
				,command.getWeight()
				,command.getTotalWeight()
				,command.getPowerSource()
				,command.getMaterial()
				,command.getEffectiveSensorRadius()
				,command.getGeneratorOutput()
				,command.getTotalThrustersOutput()
				,command.getMsOverview()
				,command.getAction()
				,LocalDateTime.now()
				,LocalDateTime.now()
				,"1");

		mobileSuitRecodeService.uploadImageFile(command.getMsMultipartFile());
		return mobileSuitRecodeService.registMobileSuit(mobileSuit);
	}

}
