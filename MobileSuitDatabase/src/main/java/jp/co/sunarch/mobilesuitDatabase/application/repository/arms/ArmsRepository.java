package jp.co.sunarch.mobilesuitDatabase.application.repository.arms;

import jp.co.sunarch.mobilesuitDatabase.domain.model.arms.Arms;

public interface ArmsRepository {

	Arms getArmsById(String armsId);

	void save(Arms arms);

	void deleteArmsById(String armsId);

}
