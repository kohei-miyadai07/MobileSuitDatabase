package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms.entity.ArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArmsQueryImpl implements ArmsQuery {

	private final ArmsQueryDao armsQueryDao;

	@Override
	public List<ArmsModel> getArmsList() {
		List<ArmsEntity> armsEntityList = armsQueryDao.selectArmsList();
		List<ArmsModel> armsModelList = armsEntityList.stream().map(l -> toModel(l)).toList();
		
		return armsModelList;
	}

	@Override
	public List<ArmsModel> searchArms(Criteria criteria) {
		List<ArmsEntity> armsEntityList = armsQueryDao.selectArmsByCriteria(criteria);
		List<ArmsModel> armsModelList = armsEntityList.stream().map(l -> toModel(l)).toList();

		return armsModelList;
	}

	private ArmsModel toModel(ArmsEntity entity) {
		ArmsModel armsModel = ArmsModel.builder()
				.armsId(entity.getArmsId())
				.armsName(entity.getArmsName())
				.detail(entity.getDetail())
				.build();

		return armsModel;
	}

}
