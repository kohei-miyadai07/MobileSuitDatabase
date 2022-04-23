package jp.co.sunarch.mobilesuitDatabase.model.form;

import lombok.Data;

@Data
public class MobileSuitRegistForm {
	private String msId;
	private String modelNumber;
	private String msName;
	private String msUrl;
	private String headHeight;
	private String weight;
	private String totalWeight;
	private String powerSource;
	private String material;
	private String generatorOutput;
	private String totalThrustersOutput;
	private String msOverview;
	private String action;

}
