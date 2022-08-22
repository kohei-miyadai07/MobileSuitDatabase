package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.EditMobileSuitResult;

public interface MobileSuitQuery {
	
	public List<MobileSuitForm> getMobileSuitList();

	public MobileSuitDetailForm getMobileSuitDetail(String msId);

	public EditMobileSuitResult getMobileSuitId(String msId);

}
