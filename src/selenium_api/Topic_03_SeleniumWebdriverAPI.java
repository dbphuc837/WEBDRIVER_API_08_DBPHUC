package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_SeleniumWebdriverAPI {
	WebDriver driver;

	By emailBox = By.xpath("//input[@id='mail']");
	By passwordBox = By.xpath("//input[@id='password']");
	By under18Radio = By.xpath("//input[@id='under_18']");
	By radioDisabled = By.xpath("//input[@id='radio-disabled']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By bioTextArea = By.xpath("//textarea[@id='bio']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestDevelop = By.xpath("//input[@id='development']");
	By interestDisabled = By.xpath("//input[@id='check-disbaled']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	By buttonEnabled = By.xpath("//button[@id='button-enabled']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_CheckIsDisplayed() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if (isElementDisplayed(emailBox)) {
			driver.findElement(emailBox).sendKeys("Automation Testing");
		}

		if (isElementDisplayed(under18Radio)) {
			driver.findElement(under18Radio).click();
		}

		if (isElementDisplayed(educationTextArea)) {
			driver.findElement(educationTextArea).sendKeys("Automation Testing");
		}

		Thread.sleep(3000);
	}

	@Test
	public void TC_02_CheckEnabledDisabled() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(emailBox));
		Assert.assertTrue(isElementEnabled(under18Radio));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(jobRole01));
		Assert.assertTrue(isElementEnabled(interestDevelop));
		Assert.assertTrue(isElementEnabled(slider01));
		Assert.assertTrue(isElementEnabled(buttonEnabled));
		
		Assert.assertFalse(isElementEnabled(passwordBox));
		Assert.assertFalse(isElementEnabled(radioDisabled));
		Assert.assertFalse(isElementEnabled(bioTextArea));
		Assert.assertFalse(isElementEnabled(jobRole02));
		Assert.assertFalse(isElementEnabled(interestDisabled));
		Assert.assertFalse(isElementEnabled(slider02));
		Assert.assertFalse(isElementEnabled(buttonDisabled));

	}

	@Test
	public void TC_03_CheckIsSelected() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		driver.findElement(under18Radio).click();
		driver.findElement(interestDevelop).click();
		Assert.assertTrue(isElementSelected(under18Radio));
		Assert.assertTrue(isElementSelected(interestDevelop));

		driver.findElement(interestDevelop).click();
		Assert.assertFalse(isElementSelected(interestDevelop));

		Thread.sleep(3000);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element: " + by + "is enabled");
			return true;
		} else {
			System.out.println("Element: " + by + "is disabled");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element: " + by + "is selected");
			return true;
		} else {
			System.out.println("Element: " + by + "is deselected");
			return false;
		}
	}
}