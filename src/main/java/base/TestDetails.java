package base;

import org.testng.ITestResult;

import util.TestType;

public class TestDetails {
	private ITestResult testResult;

	public TestDetails bridge(ITestResult result) {
		testResult = result;
		return this;
	}

	public TestDetails name(String value) {
		testResult.setAttribute("name", value);
		return this;
	}

	public TestDetails description(String value) {
		testResult.setAttribute("description", value);
		return this;
	}

	public TestDetails device(String value) {
		testResult.setAttribute("device", value);
		return this;
	}

	public TestDetails category(TestType type) {
		String value;
		switch (type) {
		case Negative:
			value = "Negative";
			break;
		case Positive:
			value = "Positive";
			break;
		case Functional:
			value = "Functional";
			break;
		case Regression:
			value = "Regression";
			break;
		case Smoke:
			value = "Smoke";
			break;
		default:
			value = "Uncategorised";
			break;
		}
		testResult.setAttribute("category", value);
		return this;
	}
}
