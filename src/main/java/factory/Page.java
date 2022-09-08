package factory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.Base;

public class Page extends Base {
	protected static PropworthPage propworthPage;

	protected static void build() {
		initDriver();
		initUtils();
		propworthPage = new PropworthPage(Driver);
	}

	@BeforeMethod
	public void setupDriver() {
		build();
	}

	@AfterMethod
	public void clearField() {
		tearDown();
	}

}
