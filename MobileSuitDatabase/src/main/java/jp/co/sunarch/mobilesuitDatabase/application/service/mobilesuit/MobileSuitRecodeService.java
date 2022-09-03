package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MobileSuitRecodeService {

	private final MobileSuitRepository mobileSuitRepository;
	
	public String RegistMobileSuit(MobileSuit mobileSuit) {
		String message = null;

		int result = mobileSuitRepository.save(mobileSuit);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

	public String UpdateMobileSuit(MobileSuit mobileSuit) {
		String message = null;

		int result = mobileSuitRepository.save(mobileSuit);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}

		return message;
	}

}
