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

public class Topic_02_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_LoginEmpty() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String EmailErrorMess = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(EmailErrorMess, "This is a required field.");

		String PassErrorMess = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(PassErrorMess, "This is a required field.");

	}

	@Test
	public void TC_02_LoginEmailInvalid() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String EmailErrorMess = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(EmailErrorMess, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginPassLessThanSixChars() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation123@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String PassErrorMess = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(PassErrorMess, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_LoginPassIncorrect() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String LoginErrorMess = driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]"))
				.getText();
		Assert.assertEquals(LoginErrorMess, "Invalid login or password.");

	}

	@Test
	public void TC_05_CreateAccountAndLogOut() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Phuc");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Dinh");
		Random random = new Random();
		int randomNumber = random.nextInt(99999);
		driver.findElement(By.xpath("//input[@id='email_address']"))
				.sendKeys("automation" + randomNumber + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		String LoginSuccessMess = driver
				.findElement(By.xpath("//li[@class='success-msg']//span"))
				.getText();
		Assert.assertEquals(LoginSuccessMess, "Thank you for registering with Main Website Store.");

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}