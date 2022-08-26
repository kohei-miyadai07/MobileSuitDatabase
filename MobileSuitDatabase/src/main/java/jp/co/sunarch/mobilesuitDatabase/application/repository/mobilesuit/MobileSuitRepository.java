package jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit;

import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;

public interface MobileSuitRepository {

	public int RegistMobileSuit(MobileSuit mobileSuit);

	public int UpdateMobileSuit(MobileSuit mobileSuit);

	public MobileSuit GetMobileSuitById(String msId);

	public int save(MobileSuit mobileSuit);

}
