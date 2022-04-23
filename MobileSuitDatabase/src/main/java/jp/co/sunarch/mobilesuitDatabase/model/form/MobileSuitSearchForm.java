package jp.co.sunarch.mobilesuitDatabase.model.form;

import lombok.Data;

@Data
public class MobileSuitSearchForm {
	private String msName;
	private String modelNumber;
	private String headHeightFrom;
	private String headHeightTo;
	private String weightFrom;
	private String weightTo;
	private String totalWeightFrom;
	private String totalWeightTo;
	private String powerSource;
	private String material;
	private String generatorOutputFrom;
	private String generatorOutputTo;
	private String totalThrustersOutputFrom;
	private String totalThrustersOutputTo;
}
