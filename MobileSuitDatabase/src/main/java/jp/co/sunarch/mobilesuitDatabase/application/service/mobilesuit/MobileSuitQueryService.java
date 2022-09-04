package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit;

import org.springframework.stereotype.Component;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MobileSuitQueryService {

	private final MobileSuitRepository mobileSuitRepository;

	public MobileSuit getMobileSuitById(MobileSuitId msId) {
		return mobileSuitRepository.getMobileSuitById(msId.getValue());
	}
}
