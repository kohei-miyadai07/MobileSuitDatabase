package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms.entity.DomaArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArmsQueryImpl implements ArmsQuery {

	private final JdbcArmsDao jdbcArmsDao;

	@Override
	public List<ArmsModel> getArmsList() {
		List<DomaArmsEntity> armsEntities = jdbcArmsDao.selectAll();
		List<ArmsModel> armsModels = armsEntities.stream().map(l -> toModel(l)).toList();
		
		return armsModels;
	}

	@Override
	public ArmsModel getArmsById(String armsId) {
		Optional<DomaArmsEntity> armsEntityOpt = jdbcArmsDao.selectById(armsId);

		ArmsModel armsModel = ArmsModel.builder()
				.armsId("")
				.armsName("")
				.detail("")
				.build();

		
		if (armsEntityOpt.isPresent()) {
			armsModel = toModel(armsEntityOpt.get());
		}

		return armsModel;
	}

	@Override
	public List<ArmsModel> searchArms(Criteria criteria) {
		List<DomaArmsEntity> armsEntities = jdbcArmsDao.selectByCriteria(criteria);
		List<ArmsModel> armsModels = armsEntities.stream().map(l -> toModel(l)).toList();

		return armsModels;
	}


	private ArmsModel toModel(DomaArmsEntity entity) {
		ArmsModel armsModel = ArmsModel.builder()
				.armsId(entity.getArmsId())
				.armsName(entity.getArmsName())
				.detail(entity.getDetail())
				.build();

		return armsModel;
	}

}
