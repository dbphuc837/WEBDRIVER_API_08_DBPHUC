package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Custom_DropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Jquerry() throws Exception {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());

	}

	@Test
	public void TC_02_Angular() throws Exception {

		driver.get("https://material.angular.io/components/select/examples");

		selectCustomDropdownList("//mat-select[@id='mat-select-5']", "//mat-option/span", "Wyoming");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@id='mat-select-5']//span[text()='Wyoming']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectCustomDropdownList(String parentXpath, String childXpath, String valueExpected) throws Exception {
		// Click de mo dropdown list
		WebElement parent = driver.findElement(By.xpath(parentXpath));
		js.executeScript("arguments[0].click();", parent);

		// Wait cho cac item duoc hien thi
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));
		System.out.println("Tat ca phan tu trong dropdown list: " + childList.size());

		// Get text tat ca cac item va kiem tra chung
		for (WebElement childItem : childList) {
			if (childItem.getText().equals(valueExpected)) {
				// Scroll den item can chon
				js.executeScript("arguments[0].scrollIntoView(true);", childItem);
				// Click item
				childItem.click();
				Thread.sleep(2000);
			}
		}

	}

}