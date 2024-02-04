package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.arms.ArmsCountModel;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms.entity.ArmsEntity;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.arms.ArmsModel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArmsQueryImpl implements ArmsQuery {

	private final JdbcArmsDao jdbcArmsDao;

	@Override
	public List<ArmsModel> getArmsList() {
		List<ArmsEntity> armsEntities = jdbcArmsDao.selectAll();
		List<ArmsModel> armsModels = armsEntities.stream().map(l -> toModel(l)).toList();
		
		return armsModels;
	}

	@Override
	public Optional<ArmsModel> getArmsById(String armsId) {
		Optional<ArmsModel> armsModelOpt = Optional.empty();

		Optional<ArmsEntity> armsEntityOpt = jdbcArmsDao.selectById(armsId);
		if (armsEntityOpt.isPresent()) {
			armsModelOpt = Optional.of(toModel(armsEntityOpt.get()));
		}

		return armsModelOpt;
	}

	@Override
	public List<ArmsModel> searchArms(Criteria criteria) {
		List<ArmsEntity> armsEntities = jdbcArmsDao.selectByCriteria(criteria);
		List<ArmsModel> armsModels = armsEntities.stream().map(l -> toModel(l)).toList();

		return armsModels;
	}

	private ArmsModel toModel(ArmsEntity entity) {
		ArmsModel model = ArmsModel.builder()
				.armsId(entity.getArmsId())
				.armsName(entity.getArmsName())
				.detail(entity.getDetail())
				.insertDate(entity.getInsertDate().toInstant())
				.updateDate(entity.getUpdateDate().toInstant())
				.version(entity.getVersion())
				.build();

		return model;
	}

	@Override
	public ArmsCountModel getArmsCount() {
		return new ArmsCountModel(jdbcArmsDao.selectAllCount());
	}
}
