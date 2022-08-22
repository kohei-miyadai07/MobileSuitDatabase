package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.from.mobilesuit.MobileSuitDetailFrom;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitDetailForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitForm;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.EditMobileSuitResult;

public interface MobileSuitQuery {
	
	public List<MobileSuitForm> getMobileSuitList();

	public MobileSuitDetailForm getMobileSuitDetail(MobileSuitDetailFrom msIdFrom);

	public EditMobileSuitResult getMobileSuitId(String msId);

}
