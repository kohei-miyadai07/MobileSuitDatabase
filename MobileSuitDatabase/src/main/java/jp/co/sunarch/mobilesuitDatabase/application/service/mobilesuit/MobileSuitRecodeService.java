package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.RegistMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MobileSuitRecodeService {

	private final MobileSuitRepository mobileSuitRepository;
	
	public String RegistMobileSuit(RegistMobileSuitCommand command) {

		String message = null;

		MobileSuitId msId = MobileSuitId.of(RandomStringUtils.randomAlphanumeric(8));

		MobileSuit mobileSuit = MobileSuit.create(
				msId
				,command.getModelNumber()
				,command.getMsName()
				,command.getMsUrl()
				,new BigDecimal(command.getHeadHeight())
				,new BigDecimal(command.getOverallHeight())
				,new BigDecimal(command.getWeight())
				,new BigDecimal(command.getTotalWeight())
				,command.getPowerSource()
				,command.getMaterial()
				,Long.parseLong(command.getEffectiveSensorRadius())
				,Long.parseLong(command.getGeneratorOutput())
				,Long.parseLong(command.getTotalThrustersOutput())
				,command.getMsOverview()
				,command.getAction()
				,LocalDateTime.now()
				,LocalDateTime.now()
				,"1");

		int result = mobileSuitRepository.RegistMobileSuit(mobileSuit);

		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

}
