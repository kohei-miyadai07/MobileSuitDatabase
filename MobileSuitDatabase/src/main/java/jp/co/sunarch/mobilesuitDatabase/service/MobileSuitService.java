package jp.co.sunarch.mobilesuitDatabase.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.dao.MobileSuitDao;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitsEntity;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitsResult;

@Service
public class MobileSuitService {
	
	private MobileSuitDao mobilesuitDao;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public MobileSuitService(MobileSuitDao mobilesuitDao) {
		this.mobilesuitDao = mobilesuitDao;
	}
	
	public List<MobileSuitsResult> getMobileSuits() {
		
		List<MobileSuitsEntity> entityList = mobilesuitDao.searchMobileSuits();
		
		List <MobileSuitsResult> mobilesuitsResultList = new ArrayList<>();
		for (MobileSuitsEntity entity : entityList) {
			MobileSuitsResult mobilesuitsResult = MobileSuitsResult.builder()
					.msId(entity.getMsId())
					.modelNumber(entity.getModelNumber())
					.msName(entity.getMsName())
					.insertDate(sdf.format(entity.getInsertDate()))
					.updateDate(sdf.format(entity.getUpdateDate()))
					.build();
			mobilesuitsResultList.add(mobilesuitsResult);
		}
		return mobilesuitsResultList;
	}

}
