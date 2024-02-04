package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.form.mobilesuit;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RegistMobileSuitForm {
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
}
