package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;

public interface MobileSuitQuery {
	public List<MobileSuitModel> findAll();

}
