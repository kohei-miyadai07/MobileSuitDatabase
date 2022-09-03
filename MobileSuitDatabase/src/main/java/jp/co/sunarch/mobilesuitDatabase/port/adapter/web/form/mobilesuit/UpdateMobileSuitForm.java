package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.Data;

@Data
public class UpdateMobileSuitForm {
	private String msId;
	private String modelNumber;
	private String msName;
	private String msUrl;
	private String headHeight;
	private String overallHeight;
	private String weight;
	private String totalWeight;
	private String powerSource;
	private String material;
	private String effectiveSensorRadius;
	private String generatorOutput;
	private String totalThrustersOutput;
	private String msOverview;
	private String action;

	public static UpdateMobileSuitForm ModelToForm(MobileSuitModel model) {
		UpdateMobileSuitForm form = new UpdateMobileSuitForm();
		form.setMsId(model.getMsId());
		form.setModelNumber(model.getModelNumber());
		form.setMsName(model.getMsName());
		form.setMsUrl(model.getMsUrl());
		form.setHeadHeight(model.getHeadHeight());
		form.setOverallHeight(model.getOverallHeight());
		form.setWeight(model.getWeight());
		form.setTotalWeight(model.getTotalWeight());
		form.setPowerSource(model.getPowerSource());
		form.setMaterial(model.getMaterial());
		form.setEffectiveSensorRadius(model.getEffectiveSensorRadius());
		form.setGeneratorOutput(model.getGeneratorOutput());
		form.setTotalThrustersOutput(model.getTotalThrustersOutput());
		form.setMsOverview(model.getMsOverview());
		form.setAction(model.getAction());

		return form;
	}
}
