package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TextBox_TextArea {
	WebDriver driver;

	String customerName, dateOfBirth, address, city, state, pin, mobileNumber, email, password, customerID;
	String newAdd, newCity, newState, newPin, newMobileNumber, newEmail;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		customerName = "Selenium";
		dateOfBirth = "2000-01-01";
		address = "266 Ton Dan";
		city = "Ho Chi Minh";
		state = "Ton Dan";
		pin = "123321";
		mobileNumber = "0374622222";
		email = "selenium" + randomNumber() + "@gmail.com";
		password = "baophuc837";

		newAdd = "384 Hoang Dieu";
		newCity = "Sai Gon";
		newState = "Hoang Dieu";
		newPin = "123456";
		newMobileNumber = "0374101010";
		newEmail = "newseleniumonline" + randomNumber() + "@gmail.com";

	}

	@Test
	public void TC_01_CreateCustomer() {
		// Login with User/Pass
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Verify Homepage display
		String welcomeMess = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(welcomeMess, "Welcome To Manager's Page of Guru99 Bank");

		// Create New Customer data
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(dateOfBirth);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// Get dynamic CustomerID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("customerID:" + customerID);

		// Verify Customer data
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		// Click Edit Customer Link then input CustomerID
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// Verify Customer Name and Address
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address);

		// Edit available fields
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(newAdd);
		
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(newCity);
		
		driver.findElement(By.xpath("//input[@name='state']")).clear();
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(newState);
		
		driver.findElement(By.xpath("//input[@name='pinno']")).clear();
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(newPin);
		
		driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(newMobileNumber);
		
		driver.findElement(By.xpath("//input[@name='emailid']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(newEmail);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// Verify new data after edit
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAdd);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newMobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmail);

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