package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.jdbc.query.mobilesuit;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit.MobileSuitQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MobileSuitQueryImpl implements MobileSuitQuery {

	private final MobileSuitQueryDao mobileSuitQueryDao;

	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public List<MobileSuitModel> findAll() {
		List<MobileSuitEntity> msEntityList = mobileSuitQueryDao.getMobileSuits();

		List<MobileSuitModel> msResponseList = msEntityList.stream()
				.map(l -> toResponse(l))
				.collect(Collectors.toList());
		return msResponseList;
	}

	private static MobileSuitModel toResponse(MobileSuitEntity msEntity) {
		MobileSuitModel msResponse = MobileSuitModel.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.insertDate(sdf.format(msEntity.getInsertDate()))
				.updateDate(sdf.format(msEntity.getUpdateDate()))
				.version(msEntity.getVersion())
				.build();

		return msResponse;

	}

}
