package jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;

public interface MobileSuitRepository {

	public int registMobileSuit(MobileSuit mobileSuit);

	public int updateMobileSuit(MobileSuit mobileSuit);

	public MobileSuit getMobileSuitById(String msId);

	public int save(MobileSuit mobileSuit);

}
