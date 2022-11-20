package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArmsRecodeService {

	private final ArmsRepository armsRepository;

	public void registArms(Arms arms) {
		armsRepository.save(arms);
	}

	public void updateArms(Arms arms) {
		armsRepository.save(arms);
	}

	public void deleteArms(Arms arms) {
		armsRepository.deleteArmsById(arms.getArmsId().getValue());
	}

}
