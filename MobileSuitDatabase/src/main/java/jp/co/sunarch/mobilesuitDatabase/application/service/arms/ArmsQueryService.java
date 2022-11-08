package jp.co.sunarch.mobilesuitDatabase.application.service.arms;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.arms.ArmsRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;
import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.ArmsId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArmsQueryService {

	private final ArmsRepository armsRepository;

	public Arms getArmsById(ArmsId armsId) {
		return armsRepository.getArmsById(armsId.getValue());
	}
}
