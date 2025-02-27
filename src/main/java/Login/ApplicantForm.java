package Login;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class ApplicantForm {
	WebDriver driver;
	 // before run change village city each time 
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// Manage browser settings
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://mahamahsul-pune.mahamining.com/login"); 
	}   

	@Test
	public void testLogin() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		usernameField.sendKeys("9325843629/A");
		WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordField.sendKeys("Aa@932584");
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
	{     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath("//span[normalize-space()='Application']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[@href='/tribal-to-tribal-land-sale']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@maxlength='10']")).sendKeys("6589639999");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[normalize-space()='Verify']")).click();
	Thread.sleep(2000);
		// Store the current window handle
	String originalWindow = driver.getWindowHandle();

	// Open a new window
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.open('https://mahamahasul-api.mahamining.com/MahaMahasul/api/LoginOtp/Get-OTP-By-Mobile?MobileNo=6589639999');");
	Thread.sleep(2000);

	// Switch to the new window
			for (String windowHandle : driver.getWindowHandles()) {
			    if (!originalWindow.contentEquals(windowHandle)) {
			        driver.switchTo().window(windowHandle);
			        break;
			    }
			}

			// Close the new window
			driver.close();
			Thread.sleep(3000);
			// Switch back to the original window
			driver.switchTo().window(originalWindow);
		driver.findElement(By.xpath("//input[@maxlength='5']")).click();
	Thread.sleep(3000);
	// Switch back to the main window
	driver.findElement(By.xpath("//span[normalize-space()='Verify OTP']")).click();
	Thread.sleep(2000);
	}
	@Test(priority=2) 
	public void tribalapplicatin() throws InterruptedException 
	{  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	JavascriptExecutor js;
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
	   // Generate a random survey number
    Random rand = new Random();
    int surveyNumber = rand.nextInt(100) + 1; // Generates a number between 1 and 100
    String surveyNumberStr = "607/" + surveyNumber + "BDD";

    // Use the random survey number
    driver.findElement(By.xpath("//input[@formcontrolname='surveyNo']")).sendKeys(surveyNumberStr);

	driver.findElement(By.xpath("//input[@formcontrolname='totalareainHector']")).sendKeys("10");

	driver.findElement(By.xpath("//input[@formcontrolname='totalareainR']")).sendKeys("25");

	driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-36\"]/mat-label")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-option-87\"]/span")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-mdc-form-field-label-38\"]/mat-label")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"mat-option-252\"]/span")).click();
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
	public void Uploaddocument() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);

		// Ensure the element is visible and enabled before interacting with it
		

		WebElement fileInput = driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-tribal-law-applications/mat-card/mat-card-content/mat-stepper/div/div[2]/div[2]/div[1]/form/div/div[3]/div/input"));
		fileInput.sendKeys("C:\\Users\\niting\\Downloads\\Adhrcard.jpg");
		Thread.sleep(2000);

		WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div[1]/form/div/div[4]/div/button[1]/span[2]")));
		actions.moveToElement(firstButton).click().perform();
		Thread.sleep(1000);
		
	//	WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='fileupload-button'][1]")));
	//	js.executeScript("arguments[0].click();", uploadButton);
	//	Thread.sleep(2000);

		fileInput = driver.findElement(By.xpath("/html/body/app-root/app-secure/div/div[2]/div/app-tribal-law-applications/mat-card/mat-card-content/mat-stepper/div/div[2]/div[2]/div[5]/div/table/tbody/tr[1]/td[3]/div/input"));
		fileInput.sendKeys("C:\\Users\\niting\\Downloads\\istockphoto-485959062-612x612.jpg");
		Thread.sleep(2000);

		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div[7]/div/button/span[2]")));
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		Thread.sleep(2000);
			actions.moveToElement(submit).click().perform();
			WebElement dn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-mdc-dialog-1\"]/div/div/app-confirmation-dialog-t1/mat-dialog-actions/div/button/span[2]")));
			js.executeScript("arguments[0].click();", dn);
			Thread.sleep(3000);
	}


		@AfterClass            
	public void teardown() {
		driver.quit();
		
	}
}
