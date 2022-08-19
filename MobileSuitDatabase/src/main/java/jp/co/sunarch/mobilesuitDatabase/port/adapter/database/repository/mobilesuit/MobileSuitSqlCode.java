package jp.co.sunarch.mobilesuitDatabase.port.adapter.database.repository.mobilesuit;

public class MobileSuitSqlCode {

	private MobileSuitSqlCode() {}

	public static String INSERT_MOBILESUIT = """
			insert 
			into MobileSuit 
			values ( 
			:msId
			,:modelNumber
			,:msName
			,:msUrl
			,:headHeight
			,:overallHeight
			,:weight
			,:totalWeight
			,:powerSource
			,:material
			,:effectiveSensorRadius
			,:generatorOutput
			,:totalThrustersOutput
			,:msOverview
			,:action
			,:insertDate
			,:updateDate
			,:version
			);
			""";

}
