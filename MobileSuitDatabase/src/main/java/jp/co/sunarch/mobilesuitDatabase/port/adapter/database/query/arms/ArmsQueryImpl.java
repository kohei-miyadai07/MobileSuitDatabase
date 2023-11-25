package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.query.arms;

import java.text.SimpleDateFormat;
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

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public List<ArmsModel> getArmsList() {
		List<ArmsEntity> armsEntities = jdbcArmsDao.selectAll();
		List<ArmsModel> armsModels = armsEntities.stream().map(l -> toModel(l)).toList();
		
		return armsModels;
	}

	@Override
	public ArmsModel getArmsById(String armsId) {
		Optional<ArmsEntity> armsEntityOpt = jdbcArmsDao.selectById(armsId);

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
		List<ArmsEntity> armsEntities = jdbcArmsDao.selectByCriteria(criteria);
		List<ArmsModel> armsModels = armsEntities.stream().map(l -> toModel(l)).toList();

		return armsModels;
	}


	private ArmsModel toModel(ArmsEntity entity) {
		ArmsModel model = ArmsModel.builder()
				.armsId(entity.getArmsId())
				.armsName(entity.getArmsName())
				.detail(entity.getDetail())
				.insertDate(sdf.format(entity.getInsertDate()))
				.updateDate(sdf.format(entity.getUpdateDate()))
				.version(String.valueOf(entity.getVersion()))
				.build();

		return model;
	}

	@Override
	public ArmsCountModel getArmsCount() {
		return new ArmsCountModel(jdbcArmsDao.selectAllCount());
	}
}
