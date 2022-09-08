package base;

import static util.extent.ExtentTestManager.getTest;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.model.Media;

import io.cucumber.java.Scenario;

public class Screenshot {

	Screenshot(WebDriver driver, String prefix, boolean replace) {
		this.prefix = prefix;
		this.driver = driver;
		this.replace = replace;
		initDir();
	}

	private String prefix;
	private WebDriver driver;
	private String dir;
	private boolean replace;
	private int fNumberG;
	private int scenarionStep = 1;

	private void initDir() {
		String path = System.getProperty("user.dir") + "\\target\\screenshots\\" + prefix;
		new File(path).mkdirs();
		dir = path;
	}

	private void captureRaw(int n) {
		if (replace) {
			fNumberG++;
			String fName = "SS_" + Integer.toString(fNumberG);
			File img = new File(dir + "\\" + fName + ".png");

			File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			try {
				FileHandler.copy(ss, img);
				System.out.println("[Screenshot] " + fName + " captured");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("[Screenshot] Couldn't capture");
			}

		} else {
			int fNumber = n + 1;
			String fName = "SS_" + Integer.toString(fNumber);
			File img = new File(dir + "\\" + fName + ".png");

			if (img.exists()) {
				captureRaw(fNumber);
			} else {
				File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				try {
					FileHandler.copy(ss, img);
					System.out.println("[Screenshot] " + fName + " captured");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("[Screenshot] Couldn't capture");
				}
			}
		}

	}

	public void capture() {
		captureRaw(0);
	}

	public void scenarioStep(Scenario scenario) {
		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			byte[] ssContent = FileUtils.readFileToByteArray(ss);
			scenario.attach(ssContent, "image/png", "Step #" + scenarionStep++);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Media ngStep() {
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
		return getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);
	}
}
