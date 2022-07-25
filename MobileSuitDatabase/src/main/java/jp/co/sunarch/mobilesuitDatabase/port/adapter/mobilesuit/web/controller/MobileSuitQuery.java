package jp.co.sunarch.mobilesuitDatabase.port.adapter.mobilesuit.web.controller;

import java.util.List;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.mobilesuit.model.MobileSuitModel;

public interface MobileSuitQuery {
	public List<MobileSuitModel> findAll();

}
