package jp.co.sunarch.mobilesuitDatabase.application.usecase.mobilesuit;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.application.command.mobilesuit.RegistMobileSuitCommand;
import jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit.MobileSuitRecodeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistMobileSuitUseCase {
	
	private final MobileSuitRecodeService mobileSuitRecodeService;
	
	public String execute(RegistMobileSuitCommand command) {

		String message = mobileSuitRecodeService.RegistMobileSuit(command);

		return message;
	}

}
