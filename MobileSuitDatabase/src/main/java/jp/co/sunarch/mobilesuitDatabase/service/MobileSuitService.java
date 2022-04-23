package jp.co.sunarch.mobilesuitDatabase.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import jp.co.sunarch.mobilesuitDatabase.dao.MobileSuitDao;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitArmedEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitArmedInfoEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitDetailEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEquipmentDetailEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitEquipmentEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitInfoEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitSearchEntity;
import jp.co.sunarch.mobilesuitDatabase.entity.MobileSuitsEntity;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitArmedEditForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitArmedRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitArmedSearchForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEditForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEquipmentEditForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEquipmentRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEquipmentSearchForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitSearchForm;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitArmedResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitDetailResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitEquipmentResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitsResult;
import jp.co.sunarch.mobilesuitDatabase.vo.MobileSuitArmedInfo;
import jp.co.sunarch.mobilesuitDatabase.vo.MobileSuitEquipment;
import jp.co.sunarch.mobilesuitDatabase.vo.MobileSuitInfo;

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
	
	public List<MobileSuitArmedResult> getMobileSuitArmedList() {
		
		List<MobileSuitArmedEntity> msArmedEntityList = mobilesuitDao.getMobileSuitArmedList();
		
		List<MobileSuitArmedResult> msArmedResultList = new ArrayList<>();
		for (MobileSuitArmedEntity entity : msArmedEntityList) {
			MobileSuitArmedResult msArmedResult = MobileSuitArmedResult.builder()
					.armedId(entity.getArmedId())
					.armedName(entity.getArmedName())
					.armedExplanation(entity.getArmedExplanation())
					.build();
			msArmedResultList.add(msArmedResult);
		}
		return msArmedResultList;
	}
	
	public List<MobileSuitEquipmentResult> getMobileSuitEquipmentList() {
		
		List<MobileSuitEquipmentEntity> msEquipmentEntityList = mobilesuitDao.getMobileSuitEquipmentList();
		
		List<MobileSuitEquipmentResult> msEquipmentResultList = new ArrayList<>();
		for (MobileSuitEquipmentEntity entity : msEquipmentEntityList) {
			MobileSuitEquipmentResult msEquipmentResult = MobileSuitEquipmentResult.builder()
					.equipmentId(entity.getEquipmentId())
					.msName(entity.getMsName())
					.armedName(entity.getArmedName())
					.numberEquipment(String.valueOf(entity.getNumberEquipment()))
					.build();
			msEquipmentResultList.add(msEquipmentResult);
		}
		return msEquipmentResultList;
	}

	public MobileSuitDetailResult getMobileSuitDetail(String msName) {

		MobileSuitDetailEntity mdEntity = mobilesuitDao.searchMobileSuitDetail(msName);

		List<MobileSuitEquipmentDetailEntity> meEntityList = 
				mobilesuitDao.searchMobileSuitEquipmentList(msName);

		List<MobileSuitEquipment> msEquipList = new ArrayList<>();
		for(MobileSuitEquipmentDetailEntity entity :meEntityList) {
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

	public String insertMobileSuit(MobileSuitRegistForm msRegistForm) {
		String message = null;

		MobileSuitEntity msEntity = new MobileSuitEntity(
				RandomStringUtils.randomAlphanumeric(8)
				,msRegistForm.getModelNumber()
				,msRegistForm.getMsName()
				,msRegistForm.getMsUrl()
				,new BigDecimal(msRegistForm.getHeadHeight())
				,new BigDecimal(msRegistForm.getWeight())
				,new BigDecimal(msRegistForm.getTotalWeight())
				,msRegistForm.getPowerSource()
				,msRegistForm.getMaterial()
				,Long.parseLong(msRegistForm.getGeneratorOutput())
				,Long.parseLong(msRegistForm.getTotalThrustersOutput())
				,msRegistForm.getMsOverview()
				,msRegistForm.getAction());

		int result = mobilesuitDao.insertOneMobileSuit(msEntity);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

	public String insertMobileSuitArmed(MobileSuitArmedRegistForm msArmedRegistForm) {
		String message = null;

		MobileSuitArmedEntity msArmedEntity = new MobileSuitArmedEntity(
				RandomStringUtils.randomAlphanumeric(8)
				,msArmedRegistForm.getArmedName()
				,msArmedRegistForm.getArmedExplanation());

		int result = mobilesuitDao.insertOneMobileSuitArmed(msArmedEntity);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

	public MobileSuitEquipmentRegistForm getMSEquipmentInfoList(MobileSuitEquipmentRegistForm msEquipmentRegistForm) {
		List<MobileSuitInfoEntity> msInfoEntityList = mobilesuitDao.searchMobileSuitInfoList();
		List<MobileSuitArmedInfoEntity> msArmedInfoEntityList = mobilesuitDao.searchMobileSuitArmedInfoList();

		List<MobileSuitInfo> msInfoList = msInfoEntityList.stream()
				.map(m -> MobileSuitInfo.builder().msId(m.getMsId()).msName(m.getMsName()).build())
				.collect(Collectors.toList());
		
		List<MobileSuitArmedInfo> msArmedInfoList = msArmedInfoEntityList.stream()
				.map(m -> MobileSuitArmedInfo.builder().armedId(m.getArmedId()).armedName(m.getArmedName()).build())
				.collect(Collectors.toList());
		
		msEquipmentRegistForm.setMsInfoList(msInfoList);
		msEquipmentRegistForm.setMsArmedInfoList(msArmedInfoList);

		return msEquipmentRegistForm;
	}
	
	public String insertMobileSuitEquipment(MobileSuitEquipmentRegistForm msEquipmentRegistForm) {
		String message = null;
		
		MobileSuitEquipmentEntity msEquipmentEntity = new MobileSuitEquipmentEntity();
		msEquipmentEntity.setEquipmentId(RandomStringUtils.randomAlphanumeric(8));
		msEquipmentEntity.setMsId(msEquipmentRegistForm.getMsId());
		msEquipmentEntity.setArmedId(msEquipmentRegistForm.getArmedId());
		msEquipmentEntity.setNumberEquipment(Integer.parseInt(msEquipmentRegistForm.getNumberEquipment()));
		
		int result = mobilesuitDao.insertOneMobileSuitEquipment(msEquipmentEntity);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}
		
		return message;
				
	}
	
	public MobileSuitEditForm getMobileSuit(String msId) {
		
		MobileSuitEntity msEntity = mobilesuitDao.selectOneMobileSuit(msId);
		
		MobileSuitEditForm msEditForm = MobileSuitEditForm.builder()
				.msId(msEntity.getMsId())
				.modelNumber(msEntity.getModelNumber())
				.msName(msEntity.getMsName())
				.msUrl(msEntity.getMsUrl())
				.headHeight(msEntity.getHeadHeight().toString())
				.weight(msEntity.getWeight().toString())
				.totalWeight(msEntity.getTotalWeight().toString())
				.powerSource(msEntity.getPowerSource())
				.material(msEntity.getMaterial())
				.generatorOutput(String.valueOf(msEntity.getGeneratorOutput()))
				.totalThrustersOutput(String.valueOf(msEntity.getTotalThrustersOutput()))
				.msOverview(msEntity.getMsOverview())
				.action(msEntity.getAction())
				.build();
		
		return msEditForm;
	}
	
	public String updateMobileSuit(MobileSuitEditForm msEditForm) {
		String message = null;
		
		MobileSuitEntity msEntity = new MobileSuitEntity(
				msEditForm.getMsId()
				,msEditForm.getModelNumber()
				,msEditForm.getMsName()
				,msEditForm.getMsUrl()
				,new BigDecimal(msEditForm.getHeadHeight())
				,new BigDecimal(msEditForm.getWeight())
				,new BigDecimal(msEditForm.getTotalWeight())
				,msEditForm.getPowerSource()
				,msEditForm.getMaterial()
				,Long.parseLong(msEditForm.getGeneratorOutput())
				,Long.parseLong(msEditForm.getTotalThrustersOutput())
				,msEditForm.getMsOverview()
				,msEditForm.getAction());
		
		int result = mobilesuitDao.updateOneMobileSuit(msEntity);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}
		
		return message;
	}
	
	public MobileSuitArmedEditForm getMobileSuitArmed(String armedId) {
		
		MobileSuitArmedEntity msArmedEntity = mobilesuitDao.selectOneArmed(armedId);
		
		MobileSuitArmedEditForm msArmedEditForm = MobileSuitArmedEditForm.builder()
				.armedId(msArmedEntity.getArmedId())
				.armedName(msArmedEntity.getArmedName())
				.armedExplanation(msArmedEntity.getArmedExplanation())
				.build();
		
		return msArmedEditForm;
	}
	
	public String updateMobileSuitArmed(MobileSuitArmedEditForm msArmedEditForm) {
		String message = null;
		
		MobileSuitArmedEntity msArmedEntity = new MobileSuitArmedEntity(
				msArmedEditForm.getArmedId()
				,msArmedEditForm.getArmedName()
				,msArmedEditForm.getArmedExplanation());
		
		int result = mobilesuitDao.updateOneMobileSuitArmed(msArmedEntity);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}
		
		return message;
	}
	
	public MobileSuitEquipmentEditForm getMobileSuitEquipment(String equipmentId) {
		
		MobileSuitEquipmentEntity msEquipmentEntity = mobilesuitDao.selectOneEquipment(equipmentId);
		
		MobileSuitEquipmentEditForm msEquipmentEditForm = MobileSuitEquipmentEditForm.builder()
				.equipmentId(msEquipmentEntity.getEquipmentId())
				.msId(msEquipmentEntity.getMsId())
				.msName(msEquipmentEntity.getMsName())
				.armedId(msEquipmentEntity.getArmedId())
				.armedName(msEquipmentEntity.getArmedName())
				.numberEquipment(String.valueOf(msEquipmentEntity.getNumberEquipment()))
				.build();
		
		return msEquipmentEditForm;
	}
	
	public String updateMobileSuitEquipment(MobileSuitEquipmentEditForm msEquipmentEditForm) {
		String message = null;
		
		MobileSuitEquipmentEntity msEquipmentEntity = new MobileSuitEquipmentEntity();
		msEquipmentEntity.setEquipmentId(msEquipmentEditForm.getEquipmentId());
		msEquipmentEntity.setNumberEquipment(Integer.parseInt(msEquipmentEditForm.getNumberEquipment()));
		
		int result = mobilesuitDao.updateOneMobileSuitEquipment(msEquipmentEntity);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}
		
		return message;
	}
	
	public String deleteMobileSuit(String msId) {
		String message = null;
		
		int resultEquipment = mobilesuitDao.daleteOneEquipmentForMobileSuit(msId);
		int resultMobileSuit = mobilesuitDao.deleteOneMobileSuit(msId);
		if (resultEquipment == 0 || resultMobileSuit == 0) {
			message = "削除処理に失敗しました。";
		} else {
			message = "削除処理に成功しました。";
		}
		
		return message;
	}
	
	public String deleteArmed(String armedId) {
		String message = null;
		
		mobilesuitDao.deleteOneEquipmentForArmed(armedId);
		int resultArmed = mobilesuitDao.deleteOneArmed(armedId);
		if (resultArmed == 0) {
			message = "削除処理に失敗しました。";
		} else {
			message = "削除処理に成功しました。";
		}
		
		return message;
	}
	
	public String deleteEquipment(String equipmentId) {
		String message = null;
		
		int resultEquipment = mobilesuitDao.deleteOneEquipment(equipmentId);
		if (resultEquipment == 0) {
			message = "削除処理に失敗しました。";
		} else {
			message = "削除処理に成功しました。";
		}
		
		return message;
	}
	
	public MobileSuitSearchForm getSearchMobileSuit() {
		
		MobileSuitSearchForm msSearchForm = new MobileSuitSearchForm();
		
		msSearchForm.setHeadHeightFrom("0");
		msSearchForm.setHeadHeightTo("0");
		msSearchForm.setWeightFrom("0");
		msSearchForm.setWeightTo("0");
		msSearchForm.setTotalWeightFrom("0");
		msSearchForm.setTotalWeightTo("0");
		msSearchForm.setGeneratorOutputFrom("0");
		msSearchForm.setGeneratorOutputTo("0");
		msSearchForm.setTotalThrustersOutputFrom("0");
		msSearchForm.setTotalThrustersOutputTo("0");
		
		return msSearchForm;
	}
	public List<MobileSuitsResult> searchMobileSuit(MobileSuitSearchForm msSearchForm) {
		
		MobileSuitSearchEntity msSearchEntity = MobileSuitSearchEntity.builder()
				.msName(msSearchForm.getMsName())
				.modelNumber(msSearchForm.getModelNumber())
				.headHeightFrom(new BigDecimal((msSearchForm.getHeadHeightFrom() == "" || msSearchForm.getHeadHeightFrom() == null)
						? "0" : msSearchForm.getHeadHeightFrom()))
				.headHeightTo(new BigDecimal((msSearchForm.getHeadHeightTo() == "" || msSearchForm.getHeadHeightTo() == null)
						? "0" : msSearchForm.getHeadHeightTo()))
				.weightFrom(new BigDecimal((msSearchForm.getWeightFrom() == "" || msSearchForm.getWeightFrom() == null)
						? "0" : msSearchForm.getWeightFrom()))
				.weightTo(new BigDecimal((msSearchForm.getWeightTo() == "" || msSearchForm.getWeightTo() == null)
						? "0" : msSearchForm.getWeightTo()))
				.totalWeightFrom(new BigDecimal((msSearchForm.getTotalWeightFrom() == "" || msSearchForm.getTotalWeightFrom() == null)
						? "0" : msSearchForm.getTotalWeightFrom()))
				.totalWeightTo(new BigDecimal((msSearchForm.getTotalWeightTo() == "" || msSearchForm.getTotalWeightTo() == null)
						? "0" : msSearchForm.getTotalWeightTo()))
				.powerSource(msSearchForm.getPowerSource())
				.material(msSearchForm.getMaterial())
				.generatorOutputFrom(Long.parseLong((msSearchForm.getGeneratorOutputFrom() == "" || msSearchForm.getGeneratorOutputFrom() == null)
						? "0" : msSearchForm.getGeneratorOutputFrom()))
				.generatorOutputTo(Long.parseLong((msSearchForm.getGeneratorOutputTo() == "" || msSearchForm.getGeneratorOutputTo() == null)
						? "0" : msSearchForm.getGeneratorOutputTo()))
				.totalThrustersOutputFrom(Long.parseLong((msSearchForm.getTotalThrustersOutputFrom() == "" || msSearchForm.getTotalThrustersOutputFrom() == null)
						? "0" : msSearchForm.getTotalThrustersOutputFrom()))
				.totalThrustersOutputTo(Long.parseLong((msSearchForm.getTotalThrustersOutputTo() == "" || msSearchForm.getTotalThrustersOutputTo() == null)
						? "0" : msSearchForm.getTotalThrustersOutputTo()))
				.build();
		
		List<MobileSuitsEntity> msEntityList = mobilesuitDao.searchesMobileSuit(msSearchEntity);
		
		List <MobileSuitsResult> mobilesuitsResultList = new ArrayList<>();
		for (MobileSuitsEntity entity : msEntityList) {
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
	
	public List<MobileSuitArmedResult> searchMobileSuitArmed(MobileSuitArmedSearchForm msArmedSearchForm) {
		
		List<MobileSuitArmedEntity> msArmedEntityList = mobilesuitDao.searchesMobileSuitArmed(msArmedSearchForm.getArmedName());
		
		List<MobileSuitArmedResult> msArmedResultList = new ArrayList<>();
		for (MobileSuitArmedEntity entity : msArmedEntityList) {
			MobileSuitArmedResult msArmedResult = MobileSuitArmedResult.builder()
					.armedId(entity.getArmedId())
					.armedName(entity.getArmedName())
					.armedExplanation(entity.getArmedExplanation())
					.build();
			msArmedResultList.add(msArmedResult);
		}
		
		return msArmedResultList;
	}
	
	public List<MobileSuitEquipmentResult> searchMobileSuitEquipment(MobileSuitEquipmentSearchForm msEquipmentSearchForm) {
		
		List<MobileSuitEquipmentEntity> msEquipmentEntityList = mobilesuitDao.searchesMobileSuitEquipment(
				msEquipmentSearchForm.getMsName(), msEquipmentSearchForm.getArmedName());
		
		List<MobileSuitEquipmentResult> msEquipmentResultList = new ArrayList<>();
		for (MobileSuitEquipmentEntity entity : msEquipmentEntityList) {
			MobileSuitEquipmentResult msEquipmentResult = MobileSuitEquipmentResult.builder()
					.equipmentId(entity.getEquipmentId())
					.msName(entity.getMsName())
					.armedName(entity.getArmedName())
					.numberEquipment(String.valueOf(entity.getNumberEquipment()))
					.build();
			msEquipmentResultList.add(msEquipmentResult);
		}
		
		return msEquipmentResultList;
	}

}
