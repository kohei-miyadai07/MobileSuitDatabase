package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.Data;

@Data
public class UpdateMobileSuitForm {
	private String msId;
	private String modelNumber;
	private String msName;
	private BigDecimal headHeight;
	private BigDecimal overallHeight;
	private BigDecimal weight;
	private BigDecimal totalWeight;
	private String powerSource;
	private String material;
	private Long effectiveSensorRadius;
	private Long generatorOutput;
	private Long totalThrustersOutput;
	private String msOverview;
	private String action;
	private MultipartFile msMultipartFile;

	public static UpdateMobileSuitForm ModelToForm(MobileSuitModel model) {
		UpdateMobileSuitForm form = new UpdateMobileSuitForm();
		form.setMsId(model.getMsId());
		form.setModelNumber(model.getModelNumber());
		form.setMsName(model.getMsName());
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
		form.setMsMultipartFile(null);

		return form;
	}
}
