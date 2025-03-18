package ResendFlow;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class ApplicantUpdateApplication {
	WebDriver driver;
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
	{     
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/app-application-dashboard/div/div/div/div[2]/div/table/tbody/tr[3]/td[7]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/app-application-dashboard/div/div/div/div[3]/app-table/div/table/tbody/tr/td[6]/div/button[2]/span[2]/span")).click();
		Thread.sleep(1000);
		//Update & NExt
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
		WebElement de = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cdk-step-content-0-0\"]/form/div[2]/button/span[2]")));
		js.executeScript("arguments[0].click();", de);
		Thread.sleep(3000);
		WebElement updatedoc = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div[7]/div/button/span[2]")));
		js.executeScript("arguments[0].click();", updatedoc);
		driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-confirmation-dialog-t1/mat-dialog-actions/div/button/span[2]")).click();
		Thread.sleep(1000);
	}

	@AfterClass            
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();

	}
}
