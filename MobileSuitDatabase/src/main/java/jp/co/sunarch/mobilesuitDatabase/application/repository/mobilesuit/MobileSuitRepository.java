package jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;

public interface MobileSuitRepository {

	MobileSuit getMobileSuitById(String msId);

	int save(MobileSuit mobileSuit);

}
