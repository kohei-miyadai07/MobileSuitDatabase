package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArmsRecodeService {

	private final ArmsRepository armsRepository;

	public String registArms(Arms arms) {
		String message = null;

		int result = armsRepository.save(arms);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

	public String updateArms(Arms arms) {
		String message = null;

		int result = armsRepository.save(arms);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}

		return message;
	}
}
