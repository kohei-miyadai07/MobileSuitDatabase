package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RegistMobileSuitForm {
	private String modelNumber;
	private String msName;
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
	private MultipartFile msMultipartFile;
}
