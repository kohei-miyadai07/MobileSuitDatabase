package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.from.mobilesuit.MobileSuitIdFrom;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitDetailResult;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitResult;

public interface MobileSuitQuery {
	
	public List<MobileSuitResult> getMobileSuitList();

	public MobileSuitDetailResult getMobileSuitDetail(MobileSuitIdFrom msIdFrom);

}
