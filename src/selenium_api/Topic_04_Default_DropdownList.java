package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Default_DropdownList {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_() throws Exception {
		driver.get("http://daominhdam.github.io/basic-form/index.html");

		// Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(jobRole.isMultiple());
		System.out.println("Dropdown status = " + jobRole.isMultiple());

		// Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		jobRole.selectByVisibleText("Automation Tester");
		Thread.sleep(2000);
		// Step 04 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Automation Tester");

		// Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		jobRole.selectByValue("manual");
		Thread.sleep(2000);
		// Step 06 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Tester");

		// Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		jobRole.selectByIndex(3);
		Thread.sleep(2000);
		// Step 08 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");

		// Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		Assert.assertEquals(jobRole.getOptions().size(), 5);
		System.out.println(jobRole.getOptions().size());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999);
	}
}