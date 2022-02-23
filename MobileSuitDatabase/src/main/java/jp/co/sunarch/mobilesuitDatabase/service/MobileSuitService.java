package jp.co.sunarch.mobilesuitDatabase.service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.dao.MobileSuitDao;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitDetailEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitsEntity;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitDetailResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitsResult;
import jp.co.sunarch.mobilesuitDatabase.vo.MobileSuitEquipment;

@Service
public class MobileSuitService {
	
	private MobileSuitDao mobilesuitDao;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private NumberFormat comFormat = NumberFormat.getNumberInstance();
	
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
	
	public MobileSuitDetailResult getMobileSuitDetail() {

		MobileSuitDetailEntity mdEntity = mobilesuitDao.searchMobileSuitDetail("as00178");
		
		List<MobileSuitEquipmentEntity> meEntityList = 
				mobilesuitDao.searchMobileSuitEquipmentList("as00178");
		
		List<MobileSuitEquipment> msEquipList = new ArrayList<>();
		for(MobileSuitEquipmentEntity entity :meEntityList) {
			MobileSuitEquipment msEquipment = MobileSuitEquipment.builder()
					.equipmentId(entity.getEquipmentId())
					.msId(entity.getMsId())
					.armedId(entity.getArmedId())
					.armedName(entity.getArmedName())
					.armedExplanation(entity.getArmedExplanation())
					.numberEquipment(entity.getNumberEquipment())
					.build();
			msEquipList.add(msEquipment);
		}
		
		MobileSuitDetailResult mobileSuitDetailResult = MobileSuitDetailResult.builder()
				.msId(mdEntity.getMsId())
				.modelNumber(mdEntity.getModelNumber())
				.msName(mdEntity.getMsName())
				.msUrl(mdEntity.getMsUrl())
				.headHeight(String.format("%sm", mdEntity.getHeadHeight().toPlainString()))
				.weight(String.format("%st", mdEntity.getWeight().toString()))
				.totalWeight(String.format("%st", mdEntity.getTotalWeight().toString()))
				.powerSource(mdEntity.getPowerSource())
				.material(mdEntity.getMaterial())
				.generatorOutput(String.format("%skW", 
						comFormat.format(mdEntity.getGeneratorOutput())))
				.totalThrustersOutput(String.format("%skg", 
						comFormat.format(mdEntity.getTotalThrustersOutput())))
				.msOverview(mdEntity.getMsOverview())
				.action(mdEntity.getAction())
				.msEquipmentList(msEquipList)
				.build();
		
		return mobileSuitDetailResult;
				
	}

}
