package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit;

import lombok.Data;

@Data
public class MobileSuitSearchForm {
	private String modelNumber;
	private String msName;
	private String headHeightFrom;
	private String headHeightTo;
	private String overallHeightFrom;
	private String overallHeightTo;
	private String weightFrom;
	private String weightTo;
	private String totalWeightFrom;
	private String totalWeightTo;
	private String powerSource;
	private String material;
	private String effectiveSensorRadiusFrom;
	private String effectiveSensorRadiusTo;
	private String generatorOutputFrom;
	private String generatorOutputTo;
	private String totalThrustersOutputFrom;
	private String totalThrustersOutputTo;
}
