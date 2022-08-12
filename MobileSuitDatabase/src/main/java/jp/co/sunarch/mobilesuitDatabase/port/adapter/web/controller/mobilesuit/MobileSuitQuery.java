package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitResult;

public interface MobileSuitQuery {
	
	public List<MobileSuitResult> getMobileSuitList();

}
