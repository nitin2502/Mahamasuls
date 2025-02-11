package Login;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ApplicantForm {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// Manage browser settings
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get("https://mahamahsul-pune.mahamining.com/login"); 
	}

	@Test
	public void testLogin() throws InterruptedException {

		WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		usernameField.sendKeys("ruthe.tillman");
		WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordField.sendKeys("%5CA9%q8PDNK6$");

		WebElement captchaField = driver.findElement(By.xpath("//input[@placeholder='Captcha']"));
		captchaField.click();
		Thread.sleep(5000);
		// Click Login Button
		WebElement loginButton = driver.findElement(By.xpath("//span[normalize-space()='LOGIN']"));
		loginButton.click();
		Thread.sleep(2000);

		// Verify login success (Assumption: Dashboard appears after login)
		String expectedUrl = "https://mahamahsul-pune.mahamining.com/application-dashboard"; 
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login Failed!");
	}
	@Test (priority=1) 
	public void testfillapplication() throws InterruptedException 
	{
		driver.findElement(By.xpath("//span[normalize-space()='Application']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='/tribal-to-tribal-land-sale']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@maxlength='10']")).sendKeys("6589639999");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Verify']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@maxlength='5']")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('https://mahamahasul-api.mahamining.com/MahaMahasul/api/LoginOtp/Get-OTP-By-Mobile?MobileNo=6589639999');");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[normalize-space()='Verify OTP']")).click();
		Thread.sleep(3000);
	}
	@Test(priority=2) 
	public void tribalapplicatin() throws InterruptedException 
	{  JavascriptExecutor js;
	js = (JavascriptExecutor) driver;
	driver.findElement(By.xpath("//input[@formcontrolname='applicantName']")).sendKeys("Ganesh kale");

	driver.findElement(By.xpath("//input[@formcontrolname='m_ApplicantName']")).sendKeys("गणेश काळे");

	driver.findElement(By.id("mat-mdc-checkbox-1-input")).sendKeys("गणेश काळे");

	driver.findElement(By.xpath("//input[@formcontrolname='emailID']")).sendKeys("ganesh045@gmail.com");

	driver.findElement(By.xpath("//*[@id=\"mat-select-value-1\"]/span")).click();

	driver.findElement(By.xpath("//span[@class='mdc-list-item__primary-text'][normalize-space()='Amravati']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-select-value-3\"]/span")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[normalize-space()='Bhatkuli']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-select-value-5\"]/span")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[normalize-space()='Advi']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-select-value-7\"]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[@class='mdc-list-item__primary-text'][normalize-space()='Aadhar Card']")).click();
	Thread.sleep(1000);
	WebElement identityCardField = driver.findElement(By.xpath("//input[@formcontrolname='identityCardNo']"));
	identityCardField.sendKeys("659874123654");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@type='file'][1]")).sendKeys("C:\\Users\\niting\\eclipse-workspace\\Mahamasuls\\Image\\adharcrd.jpg");
	Thread.sleep(1000);

	//LandDetails
	driver.findElement(By.xpath("//input[@formcontrolname='surveyNo']")).sendKeys("65/45A");

	driver.findElement(By.xpath("//input[@formcontrolname='totalareainHector']")).sendKeys("10");

	driver.findElement(By.xpath("//input[@formcontrolname='totalareainR']")).sendKeys("25");

	driver.findElement(By.xpath("//*[@id=\"mat-select-value-11\"]/span")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[@class='mdc-list-item__primary-text'][normalize-space()='Bhor']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-select-value-13\"]/span")).click();
	Thread.sleep(1000);

	driver.findElement(By.xpath("//*[@id=\"mat-option-264\"]/span")).click();
	Thread.sleep(1000);

	driver.findElement(By.xpath("//input[@formcontrolname='buyerName']")).sendKeys("plastic ltd");

	driver.findElement(By.xpath("//input[@formcontrolname='m_BuyerName']")).sendKeys("प्लास्टिक लिमिटेड");

	// Scroll to "Save and Next" button before clicking
	WebElement saveNextButton = driver.findElement(By.xpath("//span[normalize-space()='Save and Next']"));
	js.executeScript("arguments[0].scrollIntoView(true);", saveNextButton);
	Thread.sleep(1000);
	saveNextButton.click();
	Thread.sleep(2000);

	}
	@Test(priority=3) 
	public void Uploaddocument() throws InterruptedException 

	{ 
		JavascriptExecutor js;
		js = (JavascriptExecutor)driver; 
		      Thread.sleep(2000);	
		        driver.findElement(By.xpath("//input[@type='file'][1]")).sendKeys("C:\\Users\\niting\\Downloads\\Adhrcard.jpg");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div[1]/form/div/div[4]/div/button[1]/span[2]")).click();
				Thread.sleep(1000);
				// land holder application 
				driver.findElement(By.xpath("//input[@type='file'][1]")).sendKeys("C:\\Users\\niting\\Downloads\\istockphoto-485959062-612x612.jpg");
				Thread.sleep(2000);
				WebElement submit=driver.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div[7]/div/button/span[2]"));

				js.executeScript("arguments[0].scrollIntoView(true);", submit);
				Thread.sleep(2000);
				submit.click();
	}      

	//	@AfterClass            
	public void teardown() {
		driver.quit();
	}
}
