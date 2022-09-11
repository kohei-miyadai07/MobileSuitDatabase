package jp.co.sunarch.mobilesuitDatabase.application.repository.arms;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;

public interface ArmsRepository {

	Arms getArmsById(String armsId);

	int save(Arms arms);

	int deleteArmsById(String armsId);

}
